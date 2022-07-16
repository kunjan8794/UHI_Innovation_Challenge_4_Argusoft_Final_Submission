package com.argusoft.abdmhackathon.medicine.service;

import com.argusoft.abdmhackathon.medicine.dto.MedicineList;
import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;

import java.util.List;
import java.util.Map;

public interface MedicinesMasterService {

    String addMedicine(List<MedicinesMasterDto> medicinesMasterDtoList);

    List<MedicinesMasterDto> getMedicineByCodes(List<String> codes);

    Map<String, List<MedicineList>> getMedicineByCode(String codes);
}
