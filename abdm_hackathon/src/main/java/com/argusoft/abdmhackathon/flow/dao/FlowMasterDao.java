package com.argusoft.abdmhackathon.flow.dao;

import com.argusoft.abdmhackathon.flow.model.FlowMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowMasterDao extends JpaRepository<FlowMaster, Integer>, FlowMasterCustomDao {

}
