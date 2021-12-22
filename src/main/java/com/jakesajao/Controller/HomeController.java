package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.ResponseData;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.*;
import com.jakesajao.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    private AttendanceServiceImpl attendanceServiceImpl;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceImpl memberServiceImpl;


    @Autowired
    private UserService userService;
    public HomeController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }
    @ModelAttribute("memberFormDto")
    public MemberCreationDto memberCreationDto() {
        return new MemberCreationDto();
    }
    @ModelAttribute("absenteeFormDto")
    public AbsenteeFormDto absenteeFormDto() {
        return new AbsenteeFormDto();
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/login?logout")
    public String logout(){
        System.out.println("Log out...1");
        return "login";
    }
    @GetMapping("/absentee")
    public String getAbsentee(Model model){
        LoginControllerUtility loginUtility = new LoginControllerUtility();
        User_ user = loginUtility.GetUsername(userRepository);//GetUsername
        String name = user.getFirstName();
        String email = user.getEmail();
        model.addAttribute("firstName",name);

        return "absentee";
    }
    @PostMapping("/absentee")
    public String GetMemberByMonthYear(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto,
                                       BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        //attendanceServiceImpl.SaveMemberAttendance_NewWeek();
         String category = HomeControllerUtility.CategorySet(absenteeFormDto);
        if (HomeControllerUtility.GetMemberByMonthYearValidation(absenteeFormDto,model,category))
            return "absence";
        List<MemberAttend> memberPresentDateList = null;
        LocalDate date = LocalDate.now();
        memberPresentDateList = attendanceRepository.FindMemberAttendanceByCategoryAndDate(
                category,date.minusDays((Integer.parseInt(absenteeFormDto.getWeek())*7)));
        String type = HomeControllerUtility.TypeSet(absenteeFormDto);
        String title = "Report of "+type+" between Week 1 to "+
                absenteeFormDto.getWeek();

        List<MemberAttend>  memberAttendList3 = memberPresentDateList;
        System.out.println("memberPresentDateList: "+memberPresentDateList);
        HttpSession session = request.getSession(true);
        session.setAttribute("memberAttendList3", memberPresentDateList);
        model.addAttribute("success", "Report generated successfully!");
        model.addAttribute("memberAttendList3", memberAttendList3);
        model.addAttribute("title", title);

        return "absentee";
    }

    @GetMapping("members/export/excel")
    public void exportToExcel(HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=absent/present_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MemberAttend> memberList = (List<MemberAttend>) session.getAttribute("memberAttendList3");
        System.out.println(" session memberList1: "+memberList);

        AbsenteeExcelExporter excelExporter = new AbsenteeExcelExporter(memberList);

        excelExporter.export(response);
    }
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        System.out.println("PageNo: "+pageNo);
        LoginControllerUtility loginUtility = new LoginControllerUtility();
        User_ user = loginUtility.GetUsername(userRepository);//GetUsername
        String name = user.getFirstName();
        String email = user.getEmail();
        model.addAttribute("firstName",name);

        Page<Member> page = memberServiceImpl.findPaginated(pageNo, pageSize);
        List<Member>listMembers = page.getContent();

        model.addAttribute("maleTotal", memberServiceImpl.GenderCount("MALE"));
        model.addAttribute("femaleTotal", memberServiceImpl.GenderCount("FEMALE"));
        model.addAttribute("totalTeachers", memberServiceImpl.GenderCount("FEMALE")+
                memberServiceImpl.GenderCount("MALE"));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMembers", listMembers);
        return "index";
    }
    @GetMapping("/details")
    public String getDetails(Model model){
        model.addAttribute("users",memberRepository.findAll());
        return "details";
    }
    @RequestMapping(value= "/member/edit/{memberId}", method = RequestMethod.GET)
    public String editUser(@PathVariable("memberId")Long memberId, Model model){
        Member member = memberRepository.findMemberById(memberId);
        System.out.print("Member: "+member);
        model.addAttribute("member", member);
        return "edituser";
    }
    @PostMapping("/editmember")
    public String UpdateMember(@ModelAttribute("member") MemberCreationDto memberFormDto,BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error","Oops! Member Could not be updated.");
            return "redirect:/";
        }
        System.out.print("memberFormDto.getId(): "+memberFormDto.getId());

        memberServiceImpl.UpdateMember(memberFormDto);
        redirectAttributes.addFlashAttribute("success","Member Updated Successfully!");
        return  "redirect:/";
    }

    @GetMapping(value= "/member/delete/{memberId}")
    public String deleteMember(@PathVariable("memberId")Long memberId,Model model
            ,final RedirectAttributes redirectAttributes){
        System.out.println("memberId: "+memberId);
        Member member = memberServiceImpl.DeleteMember(memberId);
        if (member==null){
            redirectAttributes.addFlashAttribute("success","Empty Member.");
        }
        else {
            System.out.println("Member deleted: " + member);
            redirectAttributes.addFlashAttribute("success", "Member Deleted Successfully!");
        }
        return  "redirect:/";
    }

}
