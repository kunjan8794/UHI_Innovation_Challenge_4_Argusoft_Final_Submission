package com.argusoft.abdmhackathon.labtest.dao;

import com.argusoft.abdmhackathon.labtest.model.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 11:31 AM
 */
public interface LabTestDao extends JpaRepository<LabTest, Integer>, LabTestCustomDao {
}
