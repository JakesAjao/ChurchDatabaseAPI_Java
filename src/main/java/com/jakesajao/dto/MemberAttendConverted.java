package com.jakesajao.dto;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.jakesajao.Repository.AttendanceRepository;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberAttendConverted implements Converter<String,MemberAttend> {
    private AttendanceRepository attendanceRepository;
    @Override
    public MemberAttend convert(String id) {
        System.out.println("Trying to convert id "+id+ "into a drink: ");
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        int parseInt = Integer.parseInt(id);
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();//To be displayed as a list to users
        System.out.println("Attendance List: " + memberAttendList);
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getStatus().equals("Yes")) {
                memberAttend.setPresent(true);
            }
            else{
                memberAttend.setPresent(false);
            }
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(), memberAttend.getFirstName(),
                    memberAttend.getLastName(), memberAttend.getPresent(), memberAttend.getGender(), memberAttend.getCreatedDate());
            memberAttendList2.add(memberAttend1);

        });
        List<MemberAttend> memberAttendLists = memberAttendList2;
        int index = parseInt-1;
        return  memberAttendLists.get(index);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
