package com.argusoft.abdmhackathon.labtest.service;

import com.argusoft.abdmhackathon.labtest.dto.LabTestDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 11:38 AM
 */
public interface LabTestService {

    List<LabTestDto> getLabTestsByCodes(String codes);

    Map<Integer, Map<String, Map<Date, Float>>> getLabReportDateForEachPatient();
}
