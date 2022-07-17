package com.argusoft.abdmhackathon.question.mapper;

import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import com.argusoft.abdmhackathon.question.model.QuestionOptionMaster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 6:05 PM
 */
public class QuestionModelToDtoMapper {

    public static QuestionDto convertQuestionModelToDto(QuestionMaster questionMaster, String lang) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionMaster.getId());
        questionDto.setType(questionMaster.getType());
        questionDto.setImage(questionMaster.getImage());

        if (lang != null && lang.equals("GU")) {
            questionDto.setQuestion(questionMaster.getQuestionGu());
            questionDto.setTitle(questionMaster.getTitleGu());
            questionDto.setDescription(questionMaster.getDescriptionGu());
            questionDto.setOptions(getGuOptionsForQuestion(questionMaster.getOptions()));
        } else if (lang != null && lang.equals("HN")) {
            questionDto.setQuestion(questionMaster.getQuestionHn());
            questionDto.setTitle(questionMaster.getTitleHn());
            questionDto.setDescription(questionMaster.getDescriptionHn());
            questionDto.setOptions(getHnOptionsForQuestion(questionMaster.getOptions()));
        } else {
            questionDto.setQuestion(questionMaster.getQuestion());
            questionDto.setTitle(questionMaster.getTitle());
            questionDto.setDescription(questionMaster.getDescription());
            questionDto.setOptions(getEnOptionsForQuestion(questionMaster.getOptions()));
        }

        return questionDto;
    }

    private static Map<String, String> getGuOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            for (QuestionOptionMaster master : options) {
                map.put(master.getConstant(), master.getOptionGu());
            }
            return map;
        }
        return null;
    }

    private static Map<String, String> getEnOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            for (QuestionOptionMaster master : options) {
                map.put(master.getConstant(), master.getOption());
            }
            return map;
        }
        return null;
    }

    private static Map<String, String> getHnOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            for (QuestionOptionMaster master : options) {
                map.put(master.getConstant(), master.getOptionHn());
            }
            return map;
        }
        return null;
    }
}
