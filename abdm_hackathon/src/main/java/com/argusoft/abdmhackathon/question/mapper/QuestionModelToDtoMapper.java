package com.argusoft.abdmhackathon.question.mapper;

import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import com.argusoft.abdmhackathon.question.model.QuestionOptionMaster;

import java.util.List;

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
        questionDto.setQuestion(questionMaster.getQuestion());
        questionDto.setDescription(questionMaster.getDescription());
        questionDto.setTitle(questionMaster.getTitle());

        if (lang != null && lang.equals("GU")) {
            questionDto.setQuestion(questionMaster.getQuestionGu());
            questionDto.setOptions(getGuOptionsForQuestion(questionMaster.getOptions()));
        } else if (lang != null && lang.equals("HN")) {
            questionDto.setQuestion(questionMaster.getQuestionHn());
            questionDto.setOptions(getHnOptionsForQuestion(questionMaster.getOptions()));
        } else {
            questionDto.setQuestion(questionMaster.getQuestion());
            questionDto.setOptions(getEnOptionsForQuestion(questionMaster.getOptions()));
        }

        return questionDto;
    }

    private static String[] getGuOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            String[] array = new String[options.size()];
            int i = 0;
            for (QuestionOptionMaster master : options) {
                array[i] = master.getOptionGu();
                i++;
            }
            return array;
        }
        return null;
    }

    private static String[] getEnOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            String[] array = new String[options.size()];
            int i = 0;
            for (QuestionOptionMaster master : options) {
                array[i] = master.getOption();
                i++;
            }
            return array;
        }
        return null;
    }

    private static String[] getHnOptionsForQuestion(List<QuestionOptionMaster> options) {
        if (options != null && !options.isEmpty()) {
            String[] array = new String[options.size()];
            int i = 0;
            for (QuestionOptionMaster master : options) {
                array[i] = master.getOptionHn();
                i++;
            }
            return array;
        }
        return null;
    }
}
