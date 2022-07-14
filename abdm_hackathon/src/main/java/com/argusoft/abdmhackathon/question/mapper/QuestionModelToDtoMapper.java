package com.argusoft.abdmhackathon.question.mapper;

import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 6:05 PM
 */
public class QuestionModelToDtoMapper {

    public static QuestionDto convertQuestionModelToDto(QuestionMaster questionMaster) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionMaster.getId());
        questionDto.setType(questionMaster.getType());
        questionDto.setImage(questionDto.getImage());
        return questionDto;
    }
}
