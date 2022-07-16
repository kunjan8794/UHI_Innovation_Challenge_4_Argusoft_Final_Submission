package com.argusoft.abdmhackathon.medicine.dao;

import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;

import java.util.List;

public interface MedicinesMasterCustomDao {
    List<MedicinesMaster> getMedicinesbyCodes(List<String> codes);
    List<MedicinesMaster> getAllByCode (String codes);
}
