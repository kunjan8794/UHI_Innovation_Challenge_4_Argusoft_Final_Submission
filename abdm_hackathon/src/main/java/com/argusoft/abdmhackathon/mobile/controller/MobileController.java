package com.argusoft.abdmhackathon.mobile.controller;

import com.argusoft.abdmhackathon.triage.TriagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mobile/")
public class MobileController {

    @Autowired
    TriagingService triagingService;

    @RequestMapping(value = "submit-answers",method = RequestMethod.POST)
    public String submitAnswers(@RequestBody Map<Integer, String> submitData){
        System.out.println(submitData.entrySet());
        return "SUBMITTED";
    }

    @RequestMapping(value = "get-triaging-result",method = RequestMethod.POST)
    public Map<String, String> getTriagingResult(@RequestBody Map<Integer, String> submitData,
                                                 @RequestBody Map<String, String> previousClassifications){
        System.out.println(submitData.entrySet());
        return triagingService.doTriage(submitData, previousClassifications);
    }
}
