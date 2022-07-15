
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
    private Boolean isFirstQuestion;
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

    public Boolean getFirstQuestion() {
        return isFirstQuestion;
    }

    public void setFirstQuestion(Boolean firstQuestion) {
        isFirstQuestion = firstQuestion;
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
        public static final String IS_FIRST_QUESTION = "isFirstQuestion";
        public static final String NEXT_QUESTION_ID = "nextQuestionId";
        public static final String ANSWER="answer";
    }
}
