package com.argusoft.abdmhackathon.medicine.service.impl;

import com.argusoft.abdmhackathon.labtest.model.LabTest;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterCustomDao;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterDao;
import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.mapper.MedicinesMasterMapper;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.medicine.service.MedicinesMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicinesMasterServiceImpl implements MedicinesMasterService {

    @Autowired
    MedicinesMasterDao medicinesMasterDao;

    public String addMedicine(List<MedicinesMasterDto> medicinesMasterDtos){
        medicinesMasterDao.saveAll(MedicinesMasterMapper.convertDtoListToModel(medicinesMasterDtos));
        return "CREATED";
    };

    public  List<MedicinesMasterDto> getMedicineByCodes(List<String> codes){
        return MedicinesMasterMapper.convertModelListToDto(medicinesMasterDao.getMedicinesbyCodes(codes));
    }
    public Map<String, List<String>>  getMedicineByCode(String codes){
        List<String> codeList = Arrays.asList(codes.replace(" ", "").split(","));
        System.out.println(codeList);
        Map<String, List<String>> result = new HashMap<>();
        codeList.forEach(code -> {
            List<MedicinesMaster> medicinesMasterList = medicinesMasterDao.getAllByCode(code);
            System.out.println(medicinesMasterList);
            if (medicinesMasterList.size() > 0) {
                List<String> medicines = medicinesMasterList.stream().map(value -> value.getMedicine()).collect(Collectors.toList());
                result.put(code, medicines);
            }
        });
        return result;
    }
}
