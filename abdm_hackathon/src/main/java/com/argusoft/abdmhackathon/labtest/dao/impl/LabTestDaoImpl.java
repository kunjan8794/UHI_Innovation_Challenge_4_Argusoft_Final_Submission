package com.argusoft.abdmhackathon.labtest.dao.impl;

import com.argusoft.abdmhackathon.labtest.dao.LabTestCustomDao;
import com.argusoft.abdmhackathon.labtest.model.LabTest;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 16/07/2022 11:33 AM
 */
@Repository
public class LabTestDaoImpl implements LabTestCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<LabTest> getAllByCode(String code) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<LabTest> criteria = criteriaBuilder.createQuery(LabTest.class);
        Root<LabTest> root = criteria.from(LabTest.class);
        Predicate condition = criteriaBuilder.equal(root.get(LabTest.Fields.CODE), code);
        criteria.select(root).where(condition);
        List<LabTest> labTests = session.createQuery(criteria).list();
        return labTests;
    }
}
