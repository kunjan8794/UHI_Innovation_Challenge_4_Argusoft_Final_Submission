package com.argusoft.abdmhackathon.medicine.dao;

import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinesMasterDao extends JpaRepository<MedicinesMaster, Integer>, MedicinesMasterCustomDao {
}
