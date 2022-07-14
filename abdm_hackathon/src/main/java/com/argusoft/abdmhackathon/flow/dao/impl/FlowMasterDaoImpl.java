package com.argusoft.abdmhackathon.flow.dao.impl;


import com.argusoft.abdmhackathon.database.common.impl.GenericDaoImpl;
import com.argusoft.abdmhackathon.flow.dao.FlowMasterDao;
import com.argusoft.abdmhackathon.flow.model.FlowMaster;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.concurrent.Flow;

@Repository
public class FlowMasterDaoImpl extends GenericDaoImpl<FlowMaster, Integer> implements FlowMasterDao {


    @Override
    public Integer getFirstQuestionId () {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FlowMaster> criteria = criteriaBuilder.createQuery(FlowMaster.class);
        Root<FlowMaster> root = criteria.from(FlowMaster.class);
        Predicate questionCondition = criteriaBuilder.isNull(root.get(FlowMaster.Fields.PREVIOUS_QUESTION_ID));
        criteria.select(root).where(questionCondition);
        List<FlowMaster> flowMasters = (List<FlowMaster>) session.createQuery(criteria).list();
        return flowMasters.get(0).getQuestionId();
    }
    @Override
    public Integer getFlowByQuestionIDAndAnswer(Integer questionId,String answer){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FlowMaster> criteria = criteriaBuilder.createQuery(FlowMaster.class);
        Root<FlowMaster> root = criteria.from(FlowMaster.class);
        Predicate questionCondition =null;
        if(questionId != null && !answer.equals(null) ){
            // if questionId is there and answer is not null
            questionCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.QUESTION_ID), questionId);
            Predicate answerCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.ANSWER), answer);
            criteria.select(root).where(criteriaBuilder.and(questionCondition, answerCondition));
            FlowMaster flowMaster=(FlowMaster) session.createQuery(criteria);
            return flowMaster.getNextQuestionId();
        } else {
            questionCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.QUESTION_ID), questionId);
        }
        criteria.select(root).where(questionCondition);
        FlowMaster flowMaster=(FlowMaster) session.createQuery(criteria);
        return flowMaster.getNextQuestionId();
    };

}
