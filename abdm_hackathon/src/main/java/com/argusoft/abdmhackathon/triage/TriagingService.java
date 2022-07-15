package com.argusoft.abdmhackathon.triage;

import java.util.Map;

public interface TriagingService {

    public Map<String, String> doTriage(Map<Integer, String> mapOfAnswers, Map<String, String> previousClassifications);
}
