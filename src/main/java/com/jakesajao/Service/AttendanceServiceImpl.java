package com.jakesajao.Service;

import com.jakesajao.Model.*;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberAttend;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    int mark =25;
    @Override
    public Attendance Save(AttendanceCreationDto attendanceCreationDto) {
        Attendance attendance = new Attendance(attendanceCreationDto.getMember(), attendanceCreationDto.getPresent()
               ,LocalDate.now());

        System.out.println("Save attendance as : " + attendance);
        return (Attendance) attendanceRepository.save(attendance);
    }
    public void SaveMemberAttendance_NewWeek(){
        LocalDate currentDate = LocalDate.now();
        Optional<LocalDate> dateAlreadyExist = attendanceRepository.ValidateCurrentExistingDate(currentDate);
        if (dateAlreadyExist.isEmpty()) {
            List<MemberAttend> memberAttendList = attendanceRepository.findMemberQuery();
            memberAttendList.forEach(memberAttend -> {
                Optional<Member> memberObj = memberRepository.findById(memberAttend.getId());
                Attendance attend = new Attendance();
                attend.setMember(memberObj.get());
                attend.setPresent("No");
                attend.setCreatedDate(currentDate);
                attendanceRepository.save(attend);
                System.out.println("Attendance saved successfully! ");
            });

        }
    }
    public Percentage ProcessBooleanPercentage(MemberAttend memberAttend,int PERCENTAGE, int mark) {
        Percentage percentage = new Percentage();
        if (memberAttend.getWeek1() == true) {
            percentage.setWeek1(true);
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.getWeek2() == true) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek2(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.getWeek3() == true) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek3(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.getWeek4() == true) {
            percentage.setWeek4(true);
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        return percentage;
    }
    public static Percentage ProcessPercentage(int weekOfMonth,int PERCENTAGE, int mark){
        Percentage percentage = new Percentage();
        if (weekOfMonth==1){
            percentage.setWeek1(true);
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        else if (weekOfMonth==2) {
            //memberAttend.setWeek2(true);
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek2(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        else if (weekOfMonth==3) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek3(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        else if (weekOfMonth==4) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek4(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        return percentage;
    }
    public int UpdateMemberAttendance_NewWeek(String present,LocalDate eventDate,Long id) {
        //Display Saturday dates alone
        LocalDate todayDate = LocalDate.now();
        int response = 0;
        int matchDate = eventDate.compareTo(todayDate);
        //if (matchDate == 0) {
            System.out.println(" present: "+present);
            response = attendanceRepository.updateAttendanceById(present,eventDate, id,todayDate);
            System.out.println("Update Attendance response succeeded! response: "+response);
            return response;//1
       /* } else {
            response = -1;
            System.out.println(" present 2: "+present);
            System.out.println("Date matches oops: matchDate: "+matchDate);
            System.out.println("Update Attendance response failed. ");

            return response;
      }*/
    }
    public List ProcessChart(final int mark, List<MemberAttend> _memberAttendList){
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        List<MemberAttend> memberAttendList = _memberAttendList;

        Map<String,MemberAttend> memberAttendMap = new HashMap<>();
        final int[] PERCENTAGE = {0};
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getPresent().equals("Yes")) {
                memberAttend.setPresent1(true);
            }
            else{
                memberAttend.setPresent1(false);
            }
            int weekOfMonth = processWeekOfMonth(memberAttend.getCreatedDate());
            MemberAttend memberAttendSwitch = AttendanceControllerUtility.WeekOfMonthSwitch(weekOfMonth,memberAttend,mark);

            MemberAttend memberAttend1 = new MemberAttend(memberAttendSwitch.getId(),memberAttendSwitch.getTitle(), memberAttendSwitch.getFirstName(),
                    memberAttendSwitch.getLastName(), memberAttendSwitch.getPresent1(),memberAttendSwitch.getWeek1(),memberAttendSwitch.getWeek2(),
                    memberAttendSwitch.getWeek3(),memberAttendSwitch.getWeek4(),memberAttendSwitch.getPercentage(),memberAttendSwitch.getGender(),memberAttendSwitch.getCreatedDate());
            MemberAttend memberAttend2 = memberAttendMap.get(memberAttendSwitch.getFirstName());
            if (memberAttend2!=null){
                //memberAttend already exists.
                int percentCarryForward = memberAttendSwitch.getPercentage();
                System.out.println("percentCarryForward: "+percentCarryForward);
                int percent = 0;
                if (memberAttend1.getWeek1()==true) {
                    memberAttend2.setWeek1(true);
                    percent = percentCarryForward + 25;
                }
                if (memberAttend1.getWeek2()==true) {
                    percent = percentCarryForward + 25;
                    memberAttend2.setWeek2(true);
                }
                if(memberAttend1.getWeek3()==true) {
                    percent = percentCarryForward+ 25;
                    memberAttend2.setWeek3(true);
                }
                if (memberAttend1.getWeek4()==true) {
                    memberAttend2.setWeek4(true);
                    percent = percentCarryForward + 25;
                }
                memberAttend2.setPercentage(percent);
                MemberAttend memberAttendUpdate = new MemberAttend(memberAttend2.getId(), memberAttend2.getTitle(), memberAttend2.getFirstName(),
                        memberAttend2.getLastName(), memberAttend2.getPresent1(), memberAttend2.getWeek1(), memberAttend2.getWeek2(),
                        memberAttend2.getWeek3(), memberAttend2.getWeek4(), memberAttend2.getPercentage(), memberAttend2.getGender(), memberAttend2.getCreatedDate());
                System.out.println("memberAttendUpdate: "+memberAttendUpdate);
                memberAttendMap.computeIfPresent(memberAttend.getFirstName(), (key, val) -> memberAttendUpdate);

            }
            else if (memberAttend2==null){
                memberAttend1.setPercentage(25);
                //System.out.println("memberAttend1==null: "+memberAttend1);
                memberAttendMap.put(memberAttend.getFirstName(),memberAttend1);
            }
            //PERCENTAGE[0] =0;
        });
        List memberAttendList3 = memberAttendMap.values()
                .stream()
                .collect(Collectors.toList());

        System.out.println("memberAttendList3 List: " + memberAttendList3);
        return memberAttendList3;
    }
    private int processWeekOfMonth(LocalDate dates){
        LocalDate date = dates;
        System.out.println("Week of the month: "+date.get(WeekFields.ISO.weekOfMonth()));
        return date.get(WeekFields.ISO.weekOfMonth());
    }
//    public void UpdateUser(MemberAttend memberAttend){
//        if (memberAttend==null){
//            System.out.println("memberAttend is empty.");
//            return;
//        }
//        memberAttend.setFirstName(author.getFirstName());
//        author.setLastName(author.getLastName());
//        authorRepository.save(author);
//    }
}
