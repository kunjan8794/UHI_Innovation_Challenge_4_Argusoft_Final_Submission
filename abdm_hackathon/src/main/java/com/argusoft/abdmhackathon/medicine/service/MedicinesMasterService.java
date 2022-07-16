package com.argusoft.abdmhackathon.medicine.service;

import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;

import java.util.List;

public interface MedicinesMasterService {

    String addMedicine(List<MedicinesMasterDto> medicinesMasterDtoList);

    List<MedicinesMasterDto> getMedicineByCodes(List<String> codes);
}
