package com.argusoft.abdmhackathon.mobile.controller;

import com.argusoft.abdmhackathon.labtest.dto.LabTestDto;
import com.argusoft.abdmhackathon.medicine.dto.MedicineList;
import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.medicine.service.MedicinesMasterService;
import com.argusoft.abdmhackathon.labtest.service.LabTestService;
import com.argusoft.abdmhackathon.mobile.dto.TriagingAndFormDataDto;
import com.argusoft.abdmhackathon.triage.TriagingService;
import com.argusoft.abdmhackathon.triage.dto.TriagingResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mobile/")
public class MobileController {

    @Autowired
    TriagingService triagingService;

    @Autowired
    MedicinesMasterService medicinesMasterService;

    @Autowired
    LabTestService labTestService;

    @RequestMapping(value = "/lab-tests-by-codes", method = RequestMethod.GET)
    public List<LabTestDto> getLabTestsByCodes(@RequestParam String codes) {
        return labTestService.getLabTestsByCodes(codes);
    }

    @RequestMapping(value = "submit-answers", method = RequestMethod.POST)
    public String submitAnswers(@RequestBody Map<Integer, String> submitData) {
        System.out.println(submitData.entrySet());
        return "SUBMITTED";
    }

    @RequestMapping(value = "get-triaging-result", method = RequestMethod.POST)
    public Map<String, String> getTriagingResult(@RequestBody TriagingAndFormDataDto dto) {
        System.out.println(dto.getData().entrySet());
        return triagingService.doTriage(dto.getData(), dto.getClassification());
    }

    @RequestMapping(value = "get-all-triaging-result", method = RequestMethod.POST)
    public List<TriagingResultsDto> getAllTriagingResult(@RequestBody TriagingAndFormDataDto dto) {
        System.out.println(dto.getData().entrySet());
        return triagingService.doAllTriage(dto.getData(), dto.getClassification(),dto.getPreferredLanguage());
    }

    @RequestMapping(value = "add-medicines", method = RequestMethod.POST)
    public String addMedicine(@RequestBody List<MedicinesMasterDto> medicinesMasterDtos) {
        System.out.println(medicinesMasterDtos);
        return medicinesMasterService.addMedicine(medicinesMasterDtos);
    }
    @RequestMapping(value = "get-medicines-by-code", method = RequestMethod.POST)
    public List<MedicinesMasterDto> getMedicinesObejetsByCodes(@RequestBody List<String> codes) {
        System.out.println(codes);
        return medicinesMasterService.getMedicineByCodes(codes);
    }
    @RequestMapping(value = "medicines-by-code", method = RequestMethod.POST)
    public List<MedicineList>  getMedicinesByCodes(@RequestParam String codes) {
        System.out.println(codes);
        return medicinesMasterService.getMedicineByCode(codes);
    }

    @RequestMapping(value = "get-lab-data", method = RequestMethod.GET)
    public Map<Integer, Map<String, Map<Date, Float>>> getLabData() {
        return labTestService.getLabReportDateForEachPatient();
    }
}
