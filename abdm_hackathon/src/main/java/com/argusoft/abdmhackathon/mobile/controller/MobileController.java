package com.argusoft.abdmhackathon.mobile.controller;

import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.medicine.service.MedicinesMasterService;
import com.argusoft.abdmhackathon.mobile.dto.TriagingAndFormDataDto;
import com.argusoft.abdmhackathon.triage.TriagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mobile/")
public class MobileController {

    @Autowired
    TriagingService triagingService;

    @Autowired
    MedicinesMasterService medicinesMasterService;

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
    @RequestMapping(value = "add-medicines", method = RequestMethod.POST)
    public String addMedicine(@RequestBody List<MedicinesMasterDto> medicinesMasterDtos) {
        System.out.println(medicinesMasterDtos);
        return medicinesMasterService.addMedicine(medicinesMasterDtos);
    }
    @RequestMapping(value = "get-medicines-by-code", method = RequestMethod.POST)
    public List<MedicinesMasterDto> getMedicinesByCodes(@RequestBody List<String> codes) {
        System.out.println(codes);
        return medicinesMasterService.getMedicineByCodes(codes);
    }
}
