package com.argusoft.abdmhackathon.triage.dto;

import java.util.List;

public class TriagingResultsDto {

    private String disease;
    private List<String> symptoms;
    private List<String> suggestions;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
