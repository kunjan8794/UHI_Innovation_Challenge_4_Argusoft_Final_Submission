package com.argusoft.abdmhackathon.triage;

import com.argusoft.abdmhackathon.triage.dto.TriagingResultsDto;

import java.util.List;
import java.util.Map;

public interface TriagingService {

    public Map<String, String> doTriage(Map<Integer, String> mapOfAnswers, Map<String, String> previousClassifications);

    public List<TriagingResultsDto> doAllTriage(Map<Integer, String> mapOfAnswers, Map<String, String> previousClassifications,String preferredLanguage);
}
