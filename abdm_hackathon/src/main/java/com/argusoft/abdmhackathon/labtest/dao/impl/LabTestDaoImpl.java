package com.argusoft.abdmhackathon.labtest.dao.impl;

import com.argusoft.abdmhackathon.labtest.dao.LabTestCustomDao;
import com.argusoft.abdmhackathon.labtest.dto.LabDataDto;
import com.argusoft.abdmhackathon.labtest.model.LabTest;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
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

    public List<LabDataDto> getAllLabData() {
        String query = "with md as (\n" +
                "\tselect patient_id,  on_date,\n" +
                "\tround(sum(case when parameter_name = 'Hdl Cholesterol' then cast(value as numeric) else 0 end), 2) as hdl,\n" +
                "\tround(sum(case when parameter_name = 'LDL Cholesterol' then cast(value as numeric) else 0 end), 2) as ldl,\n" +
                "\tround(sum(case when parameter_name = 'VLDL Cholesterol' then cast(value as numeric) else 0 end), 2) as vldl,\n" +
                "\tround(sum(case when parameter_name = 'Non HDL Cholesterol' then cast(value as numeric) else 0 end), 2) as non_hdl,\n" +
                "\tround(sum(case when parameter_name = 'Triglycerides' then cast(value as numeric) else 0 end), 2) as tri,\n" +
                "\tround(sum(case when parameter_name = 'Total Cholesterol' then cast(value as numeric) else 0 end), 2) as total\n" +
                "\tfrom lab_data_master\n" +
                "\tgroup by patient_id, on_date\n" +
                ")\n" +
                "select patient_id as \"patientId\", on_date as \"onDate\", round(ldl/hdl, 2) as ratio, vldl, non_hdl as \"nonHdl\", tri, total  from md";
        NativeQuery nativeQuery = getSession().createNativeQuery(query);
        nativeQuery.addScalar("patientId", StandardBasicTypes.INTEGER);
        nativeQuery.addScalar("OnDate", StandardBasicTypes.DATE);
        nativeQuery.addScalar("ratio", StandardBasicTypes.FLOAT);
        nativeQuery.addScalar("vldl", StandardBasicTypes.FLOAT);
        nativeQuery.addScalar("nonHdl", StandardBasicTypes.FLOAT);
        nativeQuery.addScalar("tri", StandardBasicTypes.FLOAT);
        nativeQuery.addScalar("total", StandardBasicTypes.FLOAT);
        return nativeQuery.setResultTransformer(new AliasToBeanResultTransformer(LabDataDto.class)).list();
    }
}
