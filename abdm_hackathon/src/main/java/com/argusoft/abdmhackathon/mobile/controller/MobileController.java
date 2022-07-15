package com.argusoft.abdmhackathon.mobile.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mobile/")
public class MobileController {

    @RequestMapping(value = "submitAnswers",method = RequestMethod.POST)
    public String submitAnswers(@RequestBody Map<Integer, String> submitData){
        System.out.println(submitData.entrySet());
        return "SUBMITTED";
    }
}
