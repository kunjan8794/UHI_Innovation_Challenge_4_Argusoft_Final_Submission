package com.argusoft.abdmhackathon.question.dto;

import lombok.Data;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 6:01 PM
 */
@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private String image;
    private String question;
    private String type;
    private String[] options;

}
