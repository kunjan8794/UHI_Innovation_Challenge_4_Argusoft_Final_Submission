package com.argusoft.abdmhackathon.medicine.service.impl;

import com.argusoft.abdmhackathon.labtest.model.LabTest;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterCustomDao;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterDao;
import com.argusoft.abdmhackathon.medicine.dto.MedicineList;
import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.mapper.MedicinesMasterMapper;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.medicine.service.MedicinesMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import org.yaml.snakeyaml.util.ArrayUtils;

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
    public List<MedicineList> getMedicineByCode(String codes){
        List<String> codeList = Arrays.asList(codes.replace(" ", "").split(","));
        System.out.println(codeList);
        Gson gson = new Gson();
        List<MedicineList> listOfAllMedicines= new LinkedList<>();
        for (String code : codeList) {
            List<MedicinesMaster> medicinesMasterList = medicinesMasterDao.getAllByCode(code);
            if (medicinesMasterList.size() > 0) {
                for (MedicinesMaster medicine : medicinesMasterList) {
                    MedicineList[] medicineLists = gson.fromJson(medicine.getMedicine(), MedicineList[].class);
                    List<MedicineList> lists = Arrays.asList(medicineLists);
                    listOfAllMedicines.addAll(lists);
                }
            }
            /*List<String> medicines = medicinesMasterList.stream().map(value -> value.getMedicine()).collect(Collectors.toList());*/
            /*result.put(code, listOfAllMedicines);*/
        };
        return listOfAllMedicines;
    }
}
