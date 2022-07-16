package com.argusoft.abdmhackathon.labtest.service;

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
    Map<String, List<String>> getLabTestsByCodes(String codes);
}
