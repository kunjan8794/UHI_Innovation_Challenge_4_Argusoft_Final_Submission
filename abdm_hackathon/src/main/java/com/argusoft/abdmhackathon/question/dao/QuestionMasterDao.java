package com.argusoft.abdmhackathon.question.dao;

import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Defines methods for QuestionMasterDao
 *
 * @author prateek
 * @since 14/07/22 4:17 PM
 */
public interface QuestionMasterDao extends JpaRepository<QuestionMaster, Integer>, QuestionMasterCustomDao {

}
