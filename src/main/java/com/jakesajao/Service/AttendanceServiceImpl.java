package com.jakesajao.Service;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Model.Percentage;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberAttend;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance Save(AttendanceCreationDto attendanceCreationDto) {
        Attendance attendance = new Attendance(attendanceCreationDto.getMember(), attendanceCreationDto.getPresent()
               ,LocalDate.now());

        System.out.println("Save attendance as : " + attendance);
        return attendanceRepository.save(attendance);
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
        if (memberAttend.isWeek1() == true) {
            percentage.setWeek1(true);
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.isWeek2() == true) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek2(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.isWeek3() == true) {
            PERCENTAGE = PERCENTAGE + mark;
            percentage.setWeek3(true);
            percentage.setPERCENTAGE(PERCENTAGE);
        }
        if (memberAttend.isWeek4() == true) {
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
        LocalDate todayDate = LocalDate.now();
        int response = 0;
        int matchDate = eventDate.compareTo(todayDate);
        if (matchDate == 0) {
            System.out.println("Date matches!: matchDate: "+matchDate);
            response = attendanceRepository.updateAttendanceById(present, eventDate, id);
            System.out.println("Update Attendance response succeeded! response: "+response);
            return response;//1
        } else {
            response = -1;
            System.out.println("Date matches oops: matchDate: "+matchDate);
            System.out.println("Update Attendance response failed. ");

            return response;
      }
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
            if (weekOfMonth==1){
                memberAttend.setWeek1(true);
                PERCENTAGE[0] = PERCENTAGE[0] + mark;
            }
            else if (weekOfMonth==2) {
                memberAttend.setWeek2(true);
                PERCENTAGE[0] = PERCENTAGE[0] + mark;
            }
            else if (weekOfMonth==3) {
                memberAttend.setWeek3(true);
                PERCENTAGE[0] = PERCENTAGE[0] + mark;
            }
            else if (weekOfMonth==4) {
                memberAttend.setWeek4(true);
                PERCENTAGE[0] = PERCENTAGE[0] + mark;
            }
            memberAttend.setPercentage(PERCENTAGE[0]);
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
                    memberAttend.getLastName(), memberAttend.getPresent1(),memberAttend.isWeek1(),memberAttend.isWeek2(),
                    memberAttend.isWeek3(),memberAttend.isWeek4(),memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
            MemberAttend memberAttend2 = memberAttendMap.get(memberAttend.getFirstName());
            if (memberAttend2!=null){
                //memberAttend already exists.
                if (memberAttend1.isWeek1()==true) {
                    memberAttend2.setWeek1(true);
                    PERCENTAGE[0] = PERCENTAGE[0] + mark;
                }
                if (memberAttend1.isWeek2()==true) {
                    PERCENTAGE[0] = PERCENTAGE[0] + mark;
                    memberAttend2.setWeek2(true);
                }
                if(memberAttend1.isWeek3()==true) {
                    PERCENTAGE[0] = PERCENTAGE[0] + mark;
                    memberAttend2.setWeek3(true);
                }
                if (memberAttend1.isWeek4()==true) {
                    memberAttend2.setWeek4(true);
                    PERCENTAGE[0] = PERCENTAGE[0] + mark;
                }
                memberAttend2.setPercentage(PERCENTAGE[0]);
                MemberAttend memberAttendUpdate = new MemberAttend(memberAttend2.getId(), memberAttend2.getTitle(), memberAttend2.getFirstName(),
                        memberAttend2.getLastName(), memberAttend2.getPresent1(), memberAttend2.isWeek1(), memberAttend2.isWeek2(),
                        memberAttend2.isWeek3(), memberAttend2.isWeek4(), memberAttend2.getPercentage(), memberAttend2.getGender(), memberAttend2.getCreatedDate());
                System.out.println("memberAttendUpdate: "+memberAttendUpdate);
                memberAttendMap.computeIfPresent(memberAttend.getFirstName(), (key, val) -> memberAttendUpdate);
            }
            else{
                memberAttendMap.put(memberAttend.getFirstName(),memberAttend1);
            }

            // memberAttendMap.put(memberAttend.getFirstName(),memberAttend1);
//            memberAttend.setPercentage(PERCENTAGE);
//            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
//                    memberAttend.getLastName(), memberAttend.getPresent(),memberAttend.isWeek1(),memberAttend.isWeek2(),
//                    memberAttend.isWeek3(),memberAttend.isWeek4(),memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
            // memberAttendList2.add(memberAttend1);
            PERCENTAGE[0] =0;
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



}
