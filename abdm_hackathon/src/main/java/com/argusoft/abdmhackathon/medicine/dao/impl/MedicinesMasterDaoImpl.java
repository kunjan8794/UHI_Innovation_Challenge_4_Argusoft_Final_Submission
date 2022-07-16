package com.argusoft.abdmhackathon.medicine.dao.impl;

import com.argusoft.abdmhackathon.database.dao.GenericRepositoryImpl;
import com.argusoft.abdmhackathon.medicine.dao.MedicinesMasterCustomDao;
import com.argusoft.abdmhackathon.medicine.model.MedicinesMaster;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MedicinesMasterDaoImpl extends GenericRepositoryImpl implements MedicinesMasterCustomDao {

    @Override
    public List<MedicinesMaster> getMedicinesbyCodes(List<String> codes){
      Session session = getSession();
      CriteriaBuilder cb= session.getCriteriaBuilder();
      CriteriaQuery<MedicinesMaster> cq = cb.createQuery(MedicinesMaster.class);
      Root<MedicinesMaster> root = cq.from(MedicinesMaster.class);
      cq.select(root);
      cq.where(cb.in(root.get(MedicinesMaster.Fields.CODE)).value(codes));
      return session.createQuery(cq).list();
  }
    @Override
    public List<MedicinesMaster> getAllByCode(String code){
        Session session = getSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery<MedicinesMaster> cq = cb.createQuery(MedicinesMaster.class);
        Root<MedicinesMaster> root = cq.from(MedicinesMaster.class);
        cq.select(root);
        cq.where(cb.equal(root.get(MedicinesMaster.Fields.CODE),code));
        return session.createQuery(cq).list();
    }
}
