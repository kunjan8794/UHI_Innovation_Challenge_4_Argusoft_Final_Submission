package com.argusoft.abdmhackathon.question.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines methods for QuestionOptionMaster
 *
 * @author prateek
 * @since 14/07/22 6:54 PM
 */
@Entity
public class QuestionOptionMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer queId;
    private String constant;
    private String option;
    private String optionHn;
    private String optionGu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQueId() {
        return queId;
    }

    public void setQueId(Integer queId) {
        this.queId = queId;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOptionHn() {
        return optionHn;
    }

    public void setOptionHn(String optionHn) {
        this.optionHn = optionHn;
    }

    public String getOptionGu() {
        return optionGu;
    }

    public void setOptionGu(String optionGu) {
        this.optionGu = optionGu;
    }
}
