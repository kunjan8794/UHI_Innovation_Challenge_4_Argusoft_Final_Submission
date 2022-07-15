package com.argusoft.abdmhackathon.triage.impl;

import com.argusoft.abdmhackathon.triage.TriagingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Transactional
public class TriagingServiceImpl implements TriagingService {

    private static String SEVERE_PNEUMONIA = "Severe Pneumonia";
    private static String SEVERE_PNEUMONIA_DESC = "The child seems to have STRIDORS & the " +
            "Oxygen saturation levels are below 90%. There is a very high possibility of the child having severe Pneumonitis.";
    private static String PNEUMONIA = "Pneumonia";
    private static String PNEUMONIA_DESC = "The child has chest indrawing. We suggest checking exposure of HIV or conduct Inhaled broncholar trial.";
    private static String PNEUMONIA_DESC_WHEEZING = "";

    @Override
    public Map<String, String> doTriage(Map<Integer, String> mapOfAnswers) {
        Map<String, String> results = new LinkedHashMap<>();
        checkForSeverePneumonia(mapOfAnswers, results);
        checkForPneumonia(mapOfAnswers, results);
        removeMultipleClassifications(results);
        return results;
    }

    private static void checkForSeverePneumonia(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
        //IF STRIDER IN CALM CHILD = YES
        //OR
        //IF OXYGEN SATURATION < 90%
        String foundResult = null;
        String ifForStriderInCalmChild = mapOfAnswers.get(9);
        String oxygenSaturation = mapOfAnswers.get(12);
        if (ifForStriderInCalmChild != null && oxygenSaturation != null && (ifForStriderInCalmChild.equals("YES") || oxygenSaturation.equals("LT90"))) {
            results.put(SEVERE_PNEUMONIA, SEVERE_PNEUMONIA_DESC);
        }
    }

    private static void checkForPneumonia(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
        //IF CHEST INDRAWING  = YES
        //AND
        //IF CONFIRMED HIV INFECTION OR HIV EXPOSED
        // AND
        // IF CHEST INDRAWING (POST INHALED BRONCHODILATOR TRIAL) OR INHALE BRONCHODILATOR TRIAL NOT FEASIBLE/AVAILABLE

        //WHEEZING AND RECURRENT WHEEZE
        //OR
        //COUGH FOR HOW LONG?  >=14
        //OR
        //DIFFICULTY BREATHING FOR HOW LONG? >=14
        String foundResult = null;

        String chestIndrawing = mapOfAnswers.get(7);
        String wheezing = mapOfAnswers.get(10);
        String recurrentWheezing = mapOfAnswers.get(11);
        String coughHowLong = mapOfAnswers.get(4);
        String difficultyInBreathing = mapOfAnswers.get(14);

        if (chestIndrawing != null && chestIndrawing.equals("YES")) {
            results.put(PNEUMONIA, PNEUMONIA_DESC);
        }

        if (wheezing != null && recurrentWheezing != null && wheezing.equals("YES") && recurrentWheezing.equals("YES"))
            results.put(PNEUMONIA, PNEUMONIA_DESC);
        if (coughHowLong != null && coughHowLong.equals("GTE14"))
            results.put(PNEUMONIA, PNEUMONIA_DESC);
        if (difficultyInBreathing != null && difficultyInBreathing.equals("GTE14"))
            results.put(PNEUMONIA, PNEUMONIA_DESC);


        //WHEEZING = YES
        //AND
        //CHEST INDRAWING AND NOT HIV EXPOSED
        //OR
        //FAST BREATHING

        //WHEEZING
        //AND
        //FAST BREATHING OR  CHEST INDRAWING
        //AND
        //INHALE BRONCHODILATOR TRIAL NOT FEASIBLE/AVAILABLE

        //CHEST INDRAWING
        //AND
        //NOT HIV EXPOSED/INFECTED

        // FAST BREATHING
    }

    private static void forCoughOrCold(Map<Integer, String> mapOfAnswers) {
        //WHEEZING

        //(COUGH =YES OR  DIFFICULTY BREATHING = YES)
        //AND
        //(NO FAST BREATHING AND NO CHEST INDRAWING)
    }

    private static void forDiarrhoeaWithSevereDehydration(Map<Integer, String> mapOfAnswers) {
        //DIARRHOEA
        //AND
        //TWO SIGNS OR MORE OF ANY OF THE FOLLOWING:
        // LETHARGIC OR UNCONSCIOUS / SUNKEN EYES /SKIN PINCH GOES BACK VERY SLOWLY
        //AND
        //     ORAL FLUID TEST =
        // COMPLETELY UNABLE TO DRINK
        // OR
        // VOMITS IMMEDIATELY/EVERYTHING
        // OR
        // DRINKS POORLY
    }

    private static void forDiarrhoeaWithSomeDehydration(Map<Integer, String> mapOfAnswers) {
        //DIARRHOEA
        //AND
        //TWO SIGNS OR MORE OF ANY OF THE FOLLOWING:
        // LETHARGIC OR UNCONSCIOUS OR RESTLESS and IRRITABLE  / SUNKEN EYES /SKIN PINCH GOES BACK VERY SLOWLY/SKIN PINCH GOES BACK SLOWLY
        //OR
        //     ORAL FLUID TEST =
        // DRINK EAGERLY,THIRSTILY
        // OR
        // DRINKS POORLY
        // OR
        //  COMPLETELY UNABLE TO DRINK
    }

    private static void forDiarrhoeaWithNoDehydration(Map<Integer, String> mapOfAnswers) {
        //DIARRHOEA
        //AND
        //NOT ENOUGH SIGNS TO CLASSIFY AS SOME DEHYDRATION
        //OR
        //SEVERE DEHYDRATION
    }

    private static void forVerySevereFebrileDisease(Map<Integer, String> mapOfAnswers) {
        //DANGER SIGN
        //AND/OR
        //STIFF NECK
    }

    private static void forMalaria(Map<Integer, String> mapOfAnswers) {
        //MALARIA STATUS UNKNOWN/UNAVAILABLE/INVALID/NOT FEASIBLE
        //OR
        //HIGH MALARIA RISK
        //OR
        //LOW MALARIA RISK
        //AND
        //NO OBVIOUS  CAUSE OF FEVER

        //MALARIA POSITIVE
    }

    private static void forSevereComplicatedMeasles(Map<Integer, String> mapOfAnswers) {
        //MEASLES NOW
        //OR
        //MEASLES IN LAST 3 MONTHS
        //OR
        //ANY DANGER SIGN
        //OR
        //CLOUDING OF THE  CORNEA
        //OR
        //MOUTH SORES or MOUTH ULCERS- DEEP AND EXTENSIVE
        //AND
        //ONE OR MORE OF THE FOLLOWING:
        // COUGH/RUNNY NOSE/RED EYES
    }

    private static void forMeaslesWithEyeOrMouthComplication(Map<Integer, String> mapOfAnswers) {
        //MEASLES NOW
        //OR
        //MEASLES IN LAST 3 MONTHS
        // OR
        //PUS DRAINING FROM THE EYE
        //AND/OR
        //MOUTH SORES or MOUTH  ULCERS- NOT DEEP AND EXTENSIVE
        //AND
        //ONE OR MORE OF THE FOLLOWING:
        // COUGH/RUNNY NOSE/RED EYES
    }

    private static void removeMultipleClassifications(Map<String, String> results) {
        if (results.containsKey(SEVERE_PNEUMONIA) && results.containsKey(PNEUMONIA))
            results.remove(SEVERE_PNEUMONIA);
    }
}
