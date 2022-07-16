package com.argusoft.abdmhackathon.labtest.service.impl;

import com.argusoft.abdmhackathon.labtest.dao.LabTestDao;
import com.argusoft.abdmhackathon.labtest.model.LabTest;
import com.argusoft.abdmhackathon.labtest.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
