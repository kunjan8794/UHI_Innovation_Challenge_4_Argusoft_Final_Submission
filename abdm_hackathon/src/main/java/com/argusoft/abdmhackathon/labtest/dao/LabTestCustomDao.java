package com.argusoft.abdmhackathon.labtest.dao;

import com.argusoft.abdmhackathon.labtest.model.LabTest;

import java.util.List;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 11:32 AM
 */
public interface LabTestCustomDao {

    List<LabTest> getAllByCode(String code);
}
