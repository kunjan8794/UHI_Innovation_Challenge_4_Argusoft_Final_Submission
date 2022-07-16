package com.argusoft.abdmhackathon.medicine.service.impl;

import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterCustomDao;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterDao;
import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.mapper.MedicinesMasterMapper;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.medicine.service.MedicinesMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
