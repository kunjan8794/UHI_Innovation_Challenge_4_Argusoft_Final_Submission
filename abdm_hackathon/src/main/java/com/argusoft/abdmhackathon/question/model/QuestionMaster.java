package com.argusoft.abdmhackathon.question.model;

import javax.persistence.*;
import java.util.List;

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
    private String titleGu;
    private String titleHn;
    private String description;
    private String descriptionGu;
    private String descriptionHn;
    private String image;
    private String question;
    private String questionGu;
    private String questionHn;
    private String type;
    @OneToMany(targetEntity = QuestionOptionMaster.class, mappedBy = "queId", fetch = FetchType.EAGER)
    private List<QuestionOptionMaster> options;

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

    public String getTitleGu() {
        return titleGu;
    }

    public void setTitleGu(String titleGu) {
        this.titleGu = titleGu;
    }

    public String getTitleHn() {
        return titleHn;
    }

    public void setTitleHn(String titleHn) {
        this.titleHn = titleHn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionGu() {
        return descriptionGu;
    }

    public void setDescriptionGu(String descriptionGu) {
        this.descriptionGu = descriptionGu;
    }

    public String getDescriptionHn() {
        return descriptionHn;
    }

    public void setDescriptionHn(String descriptionHn) {
        this.descriptionHn = descriptionHn;
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

    public String getQuestionGu() {
        return questionGu;
    }

    public void setQuestionGu(String questionGu) {
        this.questionGu = questionGu;
    }

    public String getQuestionHn() {
        return questionHn;
    }

    public void setQuestionHn(String questionHn) {
        this.questionHn = questionHn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestionOptionMaster> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionMaster> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuestionMaster{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", question='" + question + '\'' +
                ", questionGu='" + questionGu + '\'' +
                ", questionHn='" + questionHn + '\'' +
                ", type='" + type + '\'' +
                ", options=" + options +
                '}';
    }
}
