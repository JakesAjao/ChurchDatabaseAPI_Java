package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.ResponseData;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.AbsenteeExcelExporter;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
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
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    private AttendanceServiceImpl attendanceServiceImpl;

    @Autowired
    private UserService userService;
    public HomeController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
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

        return "absentee";
    }
    @PostMapping("/absentee")
    public String GetMemberByMonthYear(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto,
                                       BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        //attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        System.out.println("Absentee absenteeFormDto: " + absenteeFormDto);
        if (absenteeFormDto==null){
            System.out.println("absenteeFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("response", "Oops! No record.");
            model.addAttribute("success", true);
            return "absentee";
        }
        String category = null;
        ResponseData rspData  = new ResponseData();
        LocalDate date = LocalDate.now();
        if (absenteeFormDto.getCategory().equals("2"))
            category = "No";
        else if (absenteeFormDto.getCategory().equals("1"))
            category = "Yes";
        if (absenteeFormDto.getCategory().equals("Select the category:")){
        System.out.println("absentee memberAttendList3: ");
        model.addAttribute("error", "The Category Selected is invalid.");
        model.addAttribute("memberAttendList3", null);
//            redirectAttributes.addFlashAttribute("error", "The Category selected is invalid.");
        return "absentee";
        }
        else if (absenteeFormDto.getWeek().equals("Select from week:")){
            System.out.println("absentee memberAttendList3: ");
            model.addAttribute("error", "The Week Selected is invalid.");
            model.addAttribute("memberAttendList3", null);
//            redirectAttributes.addFlashAttribute("error", "The Week selected is invalid.");
            return "absentee";
        }

        List<MemberAttend> memberPresentDateList = attendanceRepository.FindMemberAttendanceByCategoryAndDate(
                category,date.minusDays((Integer.parseInt(absenteeFormDto.getWeek())*7)));

        List<MemberAttend>  memberAttendList3 = memberPresentDateList;

        HttpSession session = request.getSession(true);
        session.setAttribute("memberList", memberAttendList3);
        model.addAttribute("success", "Report generated successfully!");

       // redirectAttributes.addFlashAttribute("success", "Absentee/Present records generated successfully!");

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

        List<MemberAttend> memberList = (List<MemberAttend>) session.getAttribute("memberList");
        System.out.println(" session memberList1: "+memberList);
//        if (memberList==null)
//            return "/absentee";
        AbsenteeExcelExporter excelExporter = new AbsenteeExcelExporter(memberList);

        excelExporter.export(response);
    }


}
