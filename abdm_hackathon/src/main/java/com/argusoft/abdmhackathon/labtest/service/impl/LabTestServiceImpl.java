package com.argusoft.abdmhackathon.labtest.service.impl;

import com.argusoft.abdmhackathon.labtest.dao.LabTestDao;
import com.argusoft.abdmhackathon.labtest.dto.LabDataDto;
import com.argusoft.abdmhackathon.labtest.model.LabTest;
import com.argusoft.abdmhackathon.labtest.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 11:39 AM
 */
@Service
@Transactional
public class LabTestServiceImpl implements LabTestService {

    @Autowired
    private LabTestDao labTestDao;

    @Override
    public Map<String, List<String>> getLabTestsByCodes(String codes) {
        List<String> codeList = Arrays.asList(codes.replace(" ", "").split(","));
        Map<String, List<String>> result = new HashMap<>();
        codeList.forEach(code -> {
            List<LabTest> labTestList = labTestDao.getAllByCode(code);
            if (labTestList.size() > 0) {
                List<String> tests = labTestList.stream().map(value -> value.getTest()).collect(Collectors.toList());
                result.put(code, tests);
            }
        });
        return result;
    }

    public Map<Integer, Map<String, Map<Date, Float>>> getLabReportDateForEachPatient() {
        Map<Date, Float> dateWiseData;
        Map<String, Map<Date, Float>> parameterWiseDate;
        Map<Integer, Map<String, Map<Date, Float>>> patientData = new HashMap<>();

        List<LabDataDto> all = labTestDao.getAllLabData();
        for (LabDataDto labData : all) {

            parameterWiseDate = patientData.get(labData.getPatientId());
            if (parameterWiseDate == null) {
                parameterWiseDate = new HashMap<>();
            }

            dateWiseData = parameterWiseDate.get("LDL/HDL Ratio");
            if (dateWiseData == null) {
                dateWiseData = new HashMap<>();
            }
            dateWiseData.put(labData.getOnDate(), labData.getRatio());
            parameterWiseDate.put("LDL/HDL Ratio", dateWiseData);

            dateWiseData = parameterWiseDate.get("VLDL Cholesterol");
            if (dateWiseData == null) {
                dateWiseData = new HashMap<>();
            }
            dateWiseData.put(labData.getOnDate(), labData.getVldl());
            parameterWiseDate.put("VLDL Cholesterol", dateWiseData);

            dateWiseData = parameterWiseDate.get("Non HDL Cholesterol");
            if (dateWiseData == null) {
                dateWiseData = new HashMap<>();
            }
            dateWiseData.put(labData.getOnDate(), labData.getNonHdl());
            parameterWiseDate.put("Non HDL Cholesterol", dateWiseData);

            dateWiseData = parameterWiseDate.get("Triglycerides");
            if (dateWiseData == null) {
                dateWiseData = new HashMap<>();
            }
            dateWiseData.put(labData.getOnDate(), labData.getTri());
            parameterWiseDate.put("Triglycerides", dateWiseData);

            dateWiseData = parameterWiseDate.get("Total Cholesterol");
            if (dateWiseData == null) {
                dateWiseData = new HashMap<>();
            }
            dateWiseData.put(labData.getOnDate(), labData.getTotal());
            parameterWiseDate.put("Total Cholesterol", dateWiseData);

            patientData.put(labData.getPatientId(), parameterWiseDate);
        }

        return patientData;
    }
}
