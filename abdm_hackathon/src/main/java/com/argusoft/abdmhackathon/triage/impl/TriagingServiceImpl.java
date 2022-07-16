package com.argusoft.abdmhackathon.triage.impl;

import com.argusoft.abdmhackathon.question.dao.QuestionMasterDao;
import com.argusoft.abdmhackathon.triage.TriagingService;
import com.argusoft.abdmhackathon.triage.dto.TriagingResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TriagingServiceImpl implements TriagingService {



    private static String SEVERE_PNEUMONIA = "Severe Pneumonia";
    private static String SEVERE_PNEUMONIA_ALL = "Very Severe Pneumonia";

    private static String SEVERE_PNEUMONIA_STRIDOR_DESC = "The child seems to have STRIDORS.";
    private static String SEVERE_PNEUMONIA_OXY_SAT_DESC = "The child has Oxygen saturation level below 90%.";

    private static String SEVERE_PNEUMONIA_DESC = "There is a very high possibility of the child having severe Pneumonitis.";
    private static String PNEUMONIA = "Probable Pneumonia";
    private static String PNEUMONIA_CHEST_INDRAWING_DESC = "The child has chest indrawing.";

    private static String PNEUMONIA_DESC = "We suggest checking exposure of HIV \nor\n conduct Inhaled broncholar trial.";
    private static String PNEUMONIA_DESC_WHEEZING = "The child has recurrent wheezing.";
    private static String PNEUMONIA_COUGH_GTE14_DESC = "The child has prolonged coughing. It can be a probable case of Pneumonia.";
    private static String PNEUMONIA_DIFFICULTY_BREATHING_DESC = "The child faces difficulty while breathing.";

    private static String COUGHORCOLD = "Cough Or Cold";
    private static String COUGHORCOLD_DESC = "";
    private static String DIARRHOEA_SEVERE_DEHYDRATION = "Diarrhoea with Severe Dehydration";
    private static String ORAL_FLUID_TEST_RECOMMENDATION_FOR_SEVERE_DEHYDRATION_DESC = "We recommend you to do an Oral Fluid Test as this can be Diarrhoea with Severe Dehydration.";
    private static String DIARRHOEA_SOME_DEHYDRATION = "Diarrhoea with Some Dehydration";
    private static String ORAL_FLUID_TEST_RECOMMENDATION_FOR_SOME_DEHYDRATION_DESC = "We recommend you to do an Oral Fluid Test as this can be Diarrhoea with Some Dehydration.";
    private static String POSSIBLE_BONE_INFECTION = "Possible Bone/Joint Infection";
    private static String POSSIBLE_BONE_INFECTION_DESC = "";
    private static String FEVER = "Fever ";
    private static String FEVER_DESC = "Fever";
    private static String POSSIBLE_URINE_INFECTION = "Possible Urine Infection ";
    private static String POSSIBLE_URINE_INFECTION_DESC = "Fever ";
    private static String SEVERE_COMPLICATED_MEASLES = "Severe Complicated Measles";
    private static String SEVERE_COMPLICATED_MEASLES_DESC = "Severe Complicated Measles";
    private static String MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION = "Measles with Eye or Mouth Complication";
    private static String MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION_DESC = "Measles with Eye or Mouth Complication";

    @Autowired
    QuestionMasterDao questionMasterDao;
    @Override
    public List<TriagingResultsDto> doAllTriage(Map<Integer, String> mapOfAnswers, Map<String, String> previousClassifications,String preferredLanguage) {
        List<TriagingResultsDto> results = new LinkedList<>();
        checkForCoughSymptoms(mapOfAnswers, results,preferredLanguage);
        checkForDiarrhoea(mapOfAnswers, results,preferredLanguage);
//        checkForFeverSymptoms(mapOfAnswers, results,preferredLanguage);
//        checkForMeaslesSymptoms(mapOfAnswers, results,preferredLanguage);
        return results;
    }

    @Override
    public Map<String, String> doTriage(Map<Integer, String> mapOfAnswers, Map<String, String> previousClassifications) {
        Map<String, String> results = new LinkedHashMap<>();
        checkForSeverePneumonia(mapOfAnswers, results);
        checkForPneumonia(mapOfAnswers, results);
        removeMultipleClassifications(results);
        removePreviousClassifications(results, previousClassifications);
        checkForCoughOrCold(mapOfAnswers, results);
        checkForDiarrhoeaWithSevereDehydration(mapOfAnswers, results);
        checkForDiarrhoeaWithSomeDehydration(mapOfAnswers, results);
        return results;
    }

    public void checkForCoughSymptoms(Map<Integer, String> mapOfAnswers, List<TriagingResultsDto> results, String preferredLanguage) {
        String symptoms = mapOfAnswers.get(23);
        TriagingResultsDto pneumoniaResult= new TriagingResultsDto();
        TriagingResultsDto severePneumoniaResult= new TriagingResultsDto();
        List<String> pneumoniaSuggestions=new ArrayList<>();
        List<String> pneumoniaSymptoms=new ArrayList<>();
        List<String> severePneumoniaSuggestions=new ArrayList<>();
        List<String> severePneumoniaSymptoms=new ArrayList<>();

        if (symptoms != null) {

            if (symptoms.contains("STRIDOR_IN_CHILD")) {
                severePneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23, "STRIDOR_IN_CHILD", preferredLanguage));
                severePneumoniaSuggestions.add(SEVERE_PNEUMONIA_STRIDOR_DESC);
            }

            if (symptoms.contains("OXYGEN_SATURATION_LT90")) {
                severePneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23, "OXYGEN_SATURATION_LT90", preferredLanguage));
                severePneumoniaSuggestions.add(SEVERE_PNEUMONIA_OXY_SAT_DESC);
            }

            if (symptoms.contains("STRIDOR_IN_CHILD") || symptoms.contains("OXYGEN_SATURATION_LT90")) {
                severePneumoniaSuggestions.add(SEVERE_PNEUMONIA_DESC);
            }

            if (severePneumoniaSymptoms.size() > 0) {
                severePneumoniaResult.setDisease(SEVERE_PNEUMONIA);
                severePneumoniaResult.setSymptoms(severePneumoniaSymptoms);
                severePneumoniaResult.setSuggestions(severePneumoniaSuggestions);
                results.add(severePneumoniaResult);
            } else {
                if (symptoms.contains("COUGH_GT14")) {
                    pneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23,"COUGH_GT14",preferredLanguage));
                    pneumoniaSuggestions.add(PNEUMONIA_COUGH_GTE14_DESC);
                }


                if (symptoms.contains("CHEST_INDRAWING")) {
                    pneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23,"CHEST_INDRAWING",preferredLanguage));
                    pneumoniaSuggestions.add(PNEUMONIA_CHEST_INDRAWING_DESC);
                }

                if (symptoms.contains("RECURRENT_WHEEZING")) {
                    pneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23, "RECURRENT_WHEEZING", preferredLanguage));
                    pneumoniaSuggestions.add(PNEUMONIA_DESC_WHEEZING);
                }

                if (symptoms.contains("DIFFICULTY_BREATHING_GT14")) {
                    pneumoniaSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(23, "DIFFICULTY_BREATHING_GT14", preferredLanguage));
                    pneumoniaSuggestions.add(PNEUMONIA_DIFFICULTY_BREATHING_DESC);
                }

                if (symptoms.contains("CHEST_INDRAWING") || symptoms.contains("RECURRENT_WHEEZING") || symptoms.contains("DIFFICULTY_BREATHING_GT14")) {
                    pneumoniaSuggestions.add(PNEUMONIA_DESC);
                }

                if (pneumoniaSymptoms.size() > 0) {
                    pneumoniaResult.setDisease(PNEUMONIA);
                    pneumoniaResult.setSymptoms(pneumoniaSymptoms);
                    pneumoniaResult.setSuggestions(pneumoniaSuggestions);
                    results.add(pneumoniaResult);
                }
            }
        }
    }

    public void checkForDiarrhoea(Map<Integer, String> mapOfAnswers, List<TriagingResultsDto> results,String preferredLanguage) {
        String symptoms = mapOfAnswers.get(25);
        TriagingResultsDto someDehydrationResult= new TriagingResultsDto();
        TriagingResultsDto severeDehydrationResult= new TriagingResultsDto();
        List<String> someDehydrationSuggestions=new ArrayList<>();
        List<String> someDehydrationSymptoms=new ArrayList<>();
        List<String> severeDehydrationSuggestions=new ArrayList<>();
        List<String> severeDehydrationSymptoms=new ArrayList<>();

        if (symptoms != null) {

            if (symptoms.contains("SUNKEN_EYES") && symptoms.contains("SKIN_PINCH_VERY_SLOWLY")) {
                severeDehydrationSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(25,"SUNKEN_EYES",preferredLanguage));
                severeDehydrationSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(25,"SKIN_PINCH_VERY_SLOWLY",preferredLanguage));
                severeDehydrationSuggestions.add(ORAL_FLUID_TEST_RECOMMENDATION_FOR_SEVERE_DEHYDRATION_DESC);
            }

            if (severeDehydrationSymptoms.size() > 0) {
                severeDehydrationResult.setDisease(DIARRHOEA_SEVERE_DEHYDRATION);
                severeDehydrationResult.setSymptoms(severeDehydrationSymptoms);
                severeDehydrationResult.setSuggestions(severeDehydrationSuggestions);
                results.add(severeDehydrationResult);
            }

            List<String> symptomsList = new ArrayList<>();
            symptomsList.add("SUNKEN_EYES");
            symptomsList.add("SKIN_PINCH_VERY_SLOWLY");
            symptomsList.add("RESTLESS_IRRITABLE");
            symptomsList.add("SKIN_PINCH_SLOWLY");
            Integer symptomCount = 0;
            for (String s : symptomsList) {
                if (symptoms.contains(s)) {
                    symptomCount++;
                    someDehydrationSymptoms.add(questionMasterDao.getQuestionOptionByPreferredLanguage(25, s, preferredLanguage));
                }
            }
            if (symptomCount >= 2) {
                someDehydrationSuggestions.add(ORAL_FLUID_TEST_RECOMMENDATION_FOR_SOME_DEHYDRATION_DESC);
                someDehydrationResult.setDisease(DIARRHOEA_SOME_DEHYDRATION);
                someDehydrationResult.setSymptoms(someDehydrationSymptoms);
                someDehydrationResult.setSuggestions(someDehydrationSuggestions);
                results.add(someDehydrationResult);
            }
        }
    }

    private static void checkForSeverePneumonia(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
        //IF STRIDER IN CALM CHILD = YES
        //OR
        //IF OXYGEN SATURATION < 90%
        String foundResult = null;
        String ifForStriderInCalmChild = mapOfAnswers.get(9);
        String oxygenSaturation = mapOfAnswers.get(12);

        if (Objects.equals(ifForStriderInCalmChild, "YES")) {
            results.put(SEVERE_PNEUMONIA, SEVERE_PNEUMONIA_STRIDOR_DESC);
        }
        if (Objects.equals(oxygenSaturation, "LT90")) {
            results.put(SEVERE_PNEUMONIA, SEVERE_PNEUMONIA_OXY_SAT_DESC);
        }

        if (Objects.equals(oxygenSaturation, "LT90") && Objects.equals(ifForStriderInCalmChild, "YES")) {
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
        if (!(mapOfAnswers.containsKey(7) && mapOfAnswers.containsKey(10) && mapOfAnswers.containsKey(4)) && mapOfAnswers.containsKey(11) && mapOfAnswers.containsKey(14)) {
            return;
        }
        String foundResult = null;

        String chestIndrawing = mapOfAnswers.get(7);
        String wheezing = mapOfAnswers.get(10);
        String recurrentWheezing = mapOfAnswers.get(11);
//        String coughHowLong = mapOfAnswers.get(4);
        String difficultyInBreathing = mapOfAnswers.get(14);

        if (chestIndrawing != null && chestIndrawing.equals("YES")) {
            results.put(PNEUMONIA, PNEUMONIA_CHEST_INDRAWING_DESC);
        }

        if (wheezing != null && recurrentWheezing != null && wheezing.equals("YES") && recurrentWheezing.equals("YES"))
            results.put(PNEUMONIA, PNEUMONIA_DESC_WHEEZING);
//        if (coughHowLong != null && coughHowLong.equals("GTE14"))
//            results.put(PNEUMONIA, PNEUMONIA_COUGH_GTE14_DESC);
        if (difficultyInBreathing != null && difficultyInBreathing.equals("GTE14"))
            results.put(PNEUMONIA, PNEUMONIA_DIFFICULTY_BREATHING_DESC);


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

    private static void checkForCoughOrCold(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
        //WHEEZING

        //(COUGH =YES OR  DIFFICULTY BREATHING = YES)
        //AND
        //(NO FAST BREATHING AND NO CHEST INDRAWING)
        String foundResult = null;
        String wheezing = mapOfAnswers.get(10);
        String cough = mapOfAnswers.get(3);
        String difficultyInBreathing = mapOfAnswers.get(13);
        String noFastBreathing = mapOfAnswers.get(6);
        String noChestIndrawing = mapOfAnswers.get(7);
        if (wheezing != null && wheezing.equals("YES")) {
            results.put(COUGHORCOLD, COUGHORCOLD_DESC);
        }
        if (((cough != null && cough.equals("YES")) || (difficultyInBreathing != null && difficultyInBreathing.equals("YES"))) &&
                ((noFastBreathing != null && !noFastBreathing.equals("GTE16")) && (noChestIndrawing != null && !noChestIndrawing.equals("YES")))) {
            results.put(COUGHORCOLD, COUGHORCOLD_DESC);
        }
    }

    private static void checkForDiarrhoeaWithSevereDehydration(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
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
        String diarrhoea = mapOfAnswers.get(15);
        String sunkenEyes = mapOfAnswers.get(18);
        String skinPinchAbdomen = mapOfAnswers.get(19);
        if ((diarrhoea != null && diarrhoea.equals("YES")) &&
                (sunkenEyes != null && !sunkenEyes.equals("YES")) && (skinPinchAbdomen != null && !skinPinchAbdomen.equals("VERY_SLOWLY"))) {
            results.put(DIARRHOEA_SEVERE_DEHYDRATION, ORAL_FLUID_TEST_RECOMMENDATION_FOR_SEVERE_DEHYDRATION_DESC);
        }
    }

    private static void checkForDiarrhoeaWithSomeDehydration(Map<Integer, String> mapOfAnswers, Map<String, String> results) {
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
        Integer signCounts = 0;
        String diarrhoea = mapOfAnswers.get(15);
        boolean isDiarrhoea = diarrhoea != null && diarrhoea.equals("YES");
        String sunkenEyes = mapOfAnswers.get(18);
        boolean isSunkenEyes = sunkenEyes != null && !sunkenEyes.equals("YES");
        String skinPinchVeryslowly = mapOfAnswers.get(19);
        boolean isSkinPinchVeryslowly = skinPinchVeryslowly != null && skinPinchVeryslowly.equals("VERY_SLOWLY");
        String skinPinchSlowly = mapOfAnswers.get(19);
        boolean isSkinPinchSlowly = skinPinchSlowly != null && skinPinchSlowly.equals("SLOWLY");
        String restlessAndIrritable = mapOfAnswers.get(20);
        boolean isRestlessAndIrritable = restlessAndIrritable != null && restlessAndIrritable.equals("YES");
        List<Boolean> checkList = new ArrayList<>();
        checkList.add(isRestlessAndIrritable);
        checkList.add(isSkinPinchSlowly);
        checkList.add(isSkinPinchVeryslowly);
        checkList.add(isSunkenEyes);
        for (Boolean value : checkList) {
            if (Boolean.TRUE.equals(value)) {
                signCounts++;
            }
            if (signCounts >= 2) {
                results.put(DIARRHOEA_SOME_DEHYDRATION, ORAL_FLUID_TEST_RECOMMENDATION_FOR_SOME_DEHYDRATION_DESC);
                break;
            }
        }

    }

    private static void checkForFeverSymptoms(Map<Integer, String> mapOfAnswers, List<TriagingResultsDto> results,String preferredLanguage) {
        String feverResults = mapOfAnswers.get(24);
        if (feverResults == null) {
            return;
        }
//        boolean oralSoresMouthUlcers=Arrays.stream(feverResultsArray).anyMatch("ORAL_SORES_MOUTH_ULCERS"::equals);

        if (feverResults.contains("TEMP_GTE_37_5")) {
            /*results.put(FEVER, FEVER_DESC);*/
        }
        if (feverResults.contains("TEMP_GTE_37_5") && feverResults.contains("REFUSAL_USE_LIMB") && feverResults.contains("WARM_TENDER_SWOLLEN_JOINT_BONE")) {
            /*results.put(POSSIBLE_BONE_INFECTION, POSSIBLE_BONE_INFECTION_DESC);*/
        }
        if (feverResults.contains("TEMP_GTE_37_5") && feverResults.contains("DIFFICULTY_PASSING_URINE")) {
            /*results.put(POSSIBLE_URINE_INFECTION, POSSIBLE_URINE_INFECTION_DESC);*/
        }

    }

    private static void checkForMeaslesSymptoms(Map<Integer, String> mapOfAnswers, List<TriagingResultsDto> results,String preferredLanguage) {
        String feverResults = mapOfAnswers.get(24).replace(" ", "");
        String[] feverResultsArray = feverResults != null ? feverResults.trim().split(",") : new String[0];
        if (feverResultsArray.length == 0) {
            return;
        }
        boolean hasFever = Arrays.stream(feverResultsArray).anyMatch("TEMP_GTE_37_5"::equals);
        boolean measlesInLast3Months = Arrays.stream(feverResultsArray).anyMatch("MEASLES_IN_LAST_3MONTHS"::equals);
        boolean pusDrainingFromEye = Arrays.stream(feverResultsArray).anyMatch("PUS_DRAINING_FROM_EYE"::equals);
        boolean mouthUlcersNotDeep = Arrays.stream(feverResultsArray).anyMatch("MOUTH_SORES_ULCERS_NOT_DEEP"::equals);
        boolean mouthUlcersDeep = Arrays.stream(feverResultsArray).anyMatch("MOUTH_SORES_ULCERS_DEEP"::equals);
        boolean cough = Arrays.stream(feverResultsArray).anyMatch("COUGH"::equals);
        boolean runnyNose = Arrays.stream(feverResultsArray).anyMatch("RUNNY_NOSE"::equals);
        boolean redEyes = Arrays.stream(feverResultsArray).anyMatch("RED_EYES"::equals);
        boolean cloudingOfCornea = Arrays.stream(feverResultsArray).anyMatch("CLOUDING_OF_CORNEA"::equals);

        if (hasFever && (cough || runnyNose || redEyes) && (measlesInLast3Months || cloudingOfCornea || mouthUlcersDeep)) {
            /*results.put(SEVERE_COMPLICATED_MEASLES, SEVERE_COMPLICATED_MEASLES_DESC);*/
        }
        if (hasFever && measlesInLast3Months && pusDrainingFromEye && mouthUlcersNotDeep && (cough || runnyNose || redEyes)) {
            /*results.put(MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION, MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION_DESC);*/
        }
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
        if (results.containsKey(SEVERE_PNEUMONIA))
            results.remove(PNEUMONIA);
    }

    private static void removePreviousClassifications(Map<String, String> mapOfAnswers, Map<String, String> previousClassifications) {
        for (String previousClassification : previousClassifications.keySet()) {
            mapOfAnswers.remove(previousClassification);
        }
    }
}
