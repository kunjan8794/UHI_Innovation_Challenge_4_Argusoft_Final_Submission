package com.argusoft.abdmhackathon.triage.impl;

import com.argusoft.abdmhackathon.triage.TriagingService;

import java.util.Map;

public class TriagingServiceImpl implements TriagingService {

    @Override
    public Map<String, String> doTriage(Map<Integer, String> mapOfAnswers) {
        return null;
    }

    private static void forSeverePneumonia(Map<Integer, String> mapOfAnswers) {
        //IF STRIDER IN CALM CHILD = YES
        //OR
        //IF OXYGEN SATURATION < 90%
    }

    private static void forPneumonia(Map<Integer, String> mapOfAnswers) {
        //IF STRIDER IN CALM CHILD = YES
        //OR
        //IF OXYGEN SATURATION < 90%
    }
}
