package com.argusoft.abdmhackathon.mobile.dto;

import java.util.Map;

/**
 * Defines methods for TriagingAndFormDataDto
 *
 * @author prateek
 * @since 15/07/22 5:12 PM
 */
public class TriagingAndFormDataDto {

    private Map<Integer, String> data;
    private Map<String, String> classification;

    public Map<Integer, String> getData() {
        return data;
    }

    public void setData(Map<Integer, String> data) {
        this.data = data;
    }

    public Map<String, String> getClassification() {
        return classification;
    }

    public void setClassification(Map<String, String> classification) {
        this.classification = classification;
    }
}
