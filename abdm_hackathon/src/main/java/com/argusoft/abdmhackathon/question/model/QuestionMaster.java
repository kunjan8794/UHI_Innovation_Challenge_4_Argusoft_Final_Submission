package com.argusoft.abdmhackathon.question.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines methods for QuestionMaster
 *
 * @author prateek
 * @since 14/07/22 4:24 PM
 */
@Entity
public class QuestionMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String question;
    private String questionHn;
    private String questionGu;
    private String type;
    private String options;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionHn() {
        return questionHn;
    }

    public void setQuestionHn(String questionHn) {
        this.questionHn = questionHn;
    }

    public String getQuestionGu() {
        return questionGu;
    }

    public void setQuestionGu(String questionGu) {
        this.questionGu = questionGu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
