package com.argusoft.abdmhackathon.medicine.mapper;

import com.argusoft.abdmhackathon.medicine.dto.MedicinesMasterDto;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;

import java.util.LinkedList;
import java.util.List;

public class MedicinesMasterMapper {
    public static List<MedicinesMaster> convertDtoListToModel(List<MedicinesMasterDto> medicinesMasterDtoList) {
        List<MedicinesMaster> medicinesMasterList = new LinkedList<>();
        for (MedicinesMasterDto medicinesMasterDto : medicinesMasterDtoList) {
            medicinesMasterList.add(convertMedicineDtoToModel(medicinesMasterDto));
        }
        return medicinesMasterList;
    }

    public static List<MedicinesMasterDto> convertModelListToDto(List<MedicinesMaster> medicinesMasterList) {
        List<MedicinesMasterDto> medicinesMasterDtos = new LinkedList<>();
        for (MedicinesMaster medicinesMaster : medicinesMasterList) {
            medicinesMasterDtos.add(convertMedicineModelToDto(medicinesMaster));
        }
        return medicinesMasterDtos;
    }
    public static MedicinesMaster convertMedicineDtoToModel(MedicinesMasterDto medicinesMasterDto) {
        MedicinesMaster medicinesMaster = new MedicinesMaster();
        medicinesMaster.setCode(medicinesMasterDto.getCode());
        medicinesMaster.setMedicine(medicinesMasterDto.getMedicine());
        return medicinesMaster;
    }

    public static MedicinesMasterDto convertMedicineModelToDto(MedicinesMaster medicinesMaster) {
        MedicinesMasterDto medicinesMasterDto = new MedicinesMasterDto();
        medicinesMasterDto.setId(medicinesMaster.getId());
        medicinesMasterDto.setCode(medicinesMaster.getCode());
        medicinesMasterDto.setMedicine(medicinesMaster.getMedicine());
        return medicinesMasterDto;
    }
}
