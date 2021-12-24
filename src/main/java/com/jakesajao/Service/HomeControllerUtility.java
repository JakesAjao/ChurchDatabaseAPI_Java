package com.jakesajao.Service;

import com.jakesajao.Model.ResponseData;
import com.jakesajao.dto.AbsenteeFormDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.time.LocalDate;

public class HomeControllerUtility {
    public static boolean GetMemberByMonthYearValidation(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto, Model model,
                                                         String category) {
        System.out.println("Absentee absenteeFormDto: " + absenteeFormDto);
        if (absenteeFormDto == null) {
            System.out.println("absenteeFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("response", "Oops! No record.");
            model.addAttribute("success", true);
            return true;
        }
        //ResponseData rspData = new ResponseData();
        if (absenteeFormDto.getCategory().equals("Select the category:")) {
            System.out.println("absentee memberAttendList3: ");
            model.addAttribute("error", "The Category Selected is invalid.");
            model.addAttribute("memberAttendList3", null);
//            redirectAttributes.addFlashAttribute("error", "The Category selected is invalid.");
            return true;
        } else if (absenteeFormDto.getWeek().equals("Select from week:")) {
            System.out.println("absentee memberAttendList3: ");
            model.addAttribute("error", "The Week Selected is invalid.");
            model.addAttribute("memberAttendList3", null);
//            redirectAttributes.addFlashAttribute("error", "The Week selected is invalid.");
            return true;
        }
        return false;
    }
    public static String CategorySet(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto){
        String category = null;
        if (absenteeFormDto.getCategory().equals("2"))
            category = "No";
        else if (absenteeFormDto.getCategory().equals("1"))
            category = "Yes";
        return category;
    }
    public static String TypeSet(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto){
        String type = null;
        if (absenteeFormDto.getCategory().equals("2"))
            type = "Absentee";
        else if (absenteeFormDto.getCategory().equals("1"))
            type = "Presentee";
        return type;
    }
}
