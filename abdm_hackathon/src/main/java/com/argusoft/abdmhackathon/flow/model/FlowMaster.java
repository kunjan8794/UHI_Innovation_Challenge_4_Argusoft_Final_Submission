
package com.argusoft.abdmhackathon.flow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author namrata
 */
@Entity
public class FlowMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer questionId;
    private Integer previousQuestionId;
    private Integer nextQuestionId;
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getPreviousQuestionId() {
        return previousQuestionId;
    }

    public void setPreviousQuestionId(Integer previousQuestionId) {
        this.previousQuestionId = previousQuestionId;
    }

    public Integer getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(Integer nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public static class Fields {

        public static final String QUESTION_ID = "questionId";
        public static final String PREVIOUS_QUESTION_ID = "previousQuestionId";
        public static final String NEXT_QUESTION_ID = "nextQuestionId";
        public static final String ANSWER="answer";
    }
}
