package com.argusoft.abdmhackathon.common.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

    private static Map<String, String> lan = new HashMap<>();

    static {
        lan.put("SEVERE_PNEUMONIA", "Severe Pneumonia");
        lan.put("SEVERE_PNEUMONIA_HN", "गंभीर निमोनिया");
        lan.put("SEVERE_PNEUMONIA_GU", "ગંભીર ન્યુમોનિયા");
        lan.put("PNEUMONIA", "Probable Pneumonia");
        lan.put("PNEUMONIA_HN", "संभावित निमोनिया");
        lan.put("PNEUMONIA_GU", "સંભવિત ન્યુમોનિયા");
        lan.put("DIARRHOEA_SEVERE_DEHYDRATION", "Diarrhoea with Severe Dehydration");
        lan.put("DIARRHOEA_SEVERE_DEHYDRATION_HN", "गंभीर निर्जलीकरण के साथ दस्त");
        lan.put("DIARRHOEA_SEVERE_DEHYDRATION_GU", "ગંભીર ડિહાઇડ્રેશન સાથે ઝાડા");
        lan.put("DIARRHOEA_SOME_DEHYDRATION", "Diarrhoea with Some Dehydration");
        lan.put("DIARRHOEA_SOME_DEHYDRATION_HN", "थोड़ा निर्जलीकरण के साथ दस्त");
        lan.put("DIARRHOEA_SOME_DEHYDRATION_GU", "થોડા ડિહાઇડ્રેશન સાથે ઝાડા");
        lan.put("POSSIBLE_BONE_INFECTION", "Possible Bone/Joint Infection");
        lan.put("POSSIBLE_BONE_INFECTION_HN", "संभावित हड्डी / संयुक्त संक्रमण");
        lan.put("POSSIBLE_BONE_INFECTION_GU", "સંભવિત હાડકા/સાંધાનો ચેપ");
        lan.put("POSSIBLE_BONE_INFECTION_DESC", "");
        lan.put("POSSIBLE_BONE_INFECTION_DESC_HN", "");
        lan.put("POSSIBLE_BONE_INFECTION_DESC_GU", "");
        lan.put("FEVER", "Fever");
        lan.put("FEVER_HN", "बुखार");
        lan.put("FEVER_GU", "તાવ");
        lan.put("FEVER_DESC", "Fever");
        lan.put("FEVER_DESC_HN", "Fever");
        lan.put("FEVER_DESC_GU", "Fever");
        lan.put("POSSIBLE_URINE_INFECTION", "Possible Urine Infection");
        lan.put("POSSIBLE_URINE_INFECTION_HN", "संभावित मूत्र संक्रमण");
        lan.put("POSSIBLE_URINE_INFECTION_GU", "સંભવિત પેશાબ ચેપ");
        lan.put("POSSIBLE_URINE_INFECTION_DESC", "");
        lan.put("POSSIBLE_URINE_INFECTION_DESC_HN", "");
        lan.put("POSSIBLE_URINE_INFECTION_DESC_GU", "");
        lan.put("SEVERE_COMPLICATED_MEASLES", "Severe Complicated Measles");
        lan.put("SEVERE_COMPLICATED_MEASLES_HN", "गंभीर जटिल खसरा");
        lan.put("SEVERE_COMPLICATED_MEASLES_GU", "ગંભીર જટિલ ઓરી");
        lan.put("SEVERE_COMPLICATED_MEASLES_DESC", "Severe Complicated Measles");
        lan.put("SEVERE_COMPLICATED_MEASLES_DESC_HN", "Severe Complicated Measles");
        lan.put("SEVERE_COMPLICATED_MEASLES_DESC_GU", "Severe Complicated Measles");
        lan.put("MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION", "Measles with Eye or Mouth Complication");
        lan.put("MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION_HN", "खसरा आंख या मुंह की जटिलता के साथ");
        lan.put("MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION_GU", "આંખ અથવા મોંની જટિલતા સાથે ઓરી");
        lan.put("MEASLES_WITH_EYE_OR_MOUTH_COMPLICATION_DESC", "Measles with Eye or Mouth Complication");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION1", "Get plenty of rest.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION1_HN", "बहुत आराम करो.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION1_GU", "પુષ્કળ આરામ કરો.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION2", "Don't go back to school/work until after your temperature returns to normal and you stop coughing up mucus.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION2_HN", "जब तक आपका तापमान सामान्य न हो जाए और आप बलगम वाली खांसी बंद न कर दें तब तक स्कूल/कार्य पर वापस न जाएं.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION2_GU", "જ્યાં સુધી તમારું તાપમાન સામાન્ય ન થઈ જાય અને તમે ખાંસી બંધ ન કરો ત્યાં સુધી શાળા/કામ પર પાછા જશો નહી.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION3", "Stay hydrated. Drink plenty of fluids, especially water, to help loosen mucus in your lungs.");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION3_HN", "हाइड्रेटेड रहना। अपने फेफड़ों में बलगम को ढीला करने में मदद करने के लिए खूब सारे तरल पदार्थ, विशेष रूप से पानी पिएं।");
        lan.put("SEVERE_PNEUMONIA_SUGGESTION3_GU", "હાઇડ્રેટેડ રહો. તમારા ફેફસામાં લાળને છૂટા કરવામાં મદદ કરવા માટે પુષ્કળ પ્રવાહી પીવો, ખાસ કરીને પાણી.");
        lan.put("DIARRHOEA_SUGGESSION1", "Drink plenty of liquids, including water, broths and juices.");
        lan.put("DIARRHOEA_SUGGESSION1_HN", "पानी, शोरबा और जूस सहित खूब सारे तरल पदार्थ पिएं।");
        lan.put("DIARRHOEA_SUGGESSION1_GU", "પાણી, સૂપ અને રસ સહિત પુષ્કળ પ્રવાહી પીવો.");
        lan.put("DIARRHOEA_SUGGESSION2", "Avoid caffeine and alcohol.");
        lan.put("DIARRHOEA_SUGGESSION2_HN", "कैफीन और शराब से बचें।");
        lan.put("DIARRHOEA_SUGGESSION2_GU", "કેફીન અને આલ્કોહોલ ટાળો.");
        lan.put("DIARRHOEA_SUGGESSION3", "Add semisolid and low-fiber foods gradually as your bowel movements return to normal.");
        lan.put("DIARRHOEA_SUGGESSION3_HN", "जैसे ही आपका मल त्याग सामान्य हो जाता है, धीरे-धीरे अर्ध-ठोस और कम फाइबर वाले खाद्य पदार्थ शामिल करें।");
        lan.put("DIARRHOEA_SUGGESSION3_GU", "તમારી આંતરડાની ગતિ સામાન્ય થવા પર ધીમે ધીમે અર્ધ ઘન અને ઓછા ફાઇબરવાળા ખોરાક ઉમેરો.");
        lan.put("DIARRHOEA_SUGGESSION4", "Try soda crackers, toast, eggs, rice or chicken.");
        lan.put("DIARRHOEA_SUGGESSION4_HN", "सोडा क्रैकर्स, टोस्ट, अंडे, चावल या चिकन आज़माएं।");
        lan.put("DIARRHOEA_SUGGESSION4_GU", "સોડા ફટાકડા, ટોસ્ટ, ઇંડા, ચોખા અથવા ચિકનનો પ્રયાસ કરો.");
        lan.put("FEVER_SUGGESTION1", "Rest and drink plenty of fluids.");
        lan.put("FEVER_SUGGESTION1_HN", "आराम करें और खूब सारे तरल पदार्थ पिएं।");
        lan.put("FEVER_SUGGESTION1_GU", "આરામ કરો અને પુષ્કળ પ્રવાહી પીવો.");
        lan.put("FEVER_SUGGESTION2", "In case symptoms worsen, visit again to the hospital.");
        lan.put("FEVER_SUGGESTION2_HN", "लक्षण बिगड़ने पर दोबारा अस्पताल जाएं।");
        lan.put("FEVER_SUGGESTION2_GU", "જો લક્ષણો વધુ ખરાબ થાય, તો ફરીથી હોસ્પિટલની મુલાકાત લો.");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION1", "Be careful not to injure the area where you have the infection.");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION1_HN", "सावधान रहें कि उस क्षेत्र को घायल न करें जहां आपको संक्रमण हो।");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION1_GU", "જ્યાં તમને ચેપ લાગ્યો છે તે વિસ્તારને ઇજા ન થાય તેનું ધ્યાન રાખો.");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION2", "Use a splint, sling, or brace if needed.");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION2_HN", "यदि आवश्यक हो तो स्प्लिंट, स्लिंग या ब्रेस का प्रयोग करें।");
        lan.put("POSSIBLE_BONE_INFECTION_SUGGESTION2_GU", "જો જરૂરી હોય તો સ્પ્લિન્ટ, સ્લિંગ અથવા બ્રેસનો ઉપયોગ કરો.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION1", "Take your antibiotics as directed.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION1_HN", "निर्देशानुसार अपनी एंटीबायोटिक्स लें।");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION1_GU", "તમારા એન્ટિબાયોટિક્સ નિર્દેશન મુજબ લો.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION2", "Drink extra water and other fluids for the next day or two.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION2_HN", "अगले या दो दिनों के लिए अतिरिक्त पानी और अन्य तरल पदार्थ पिएं।");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION2_GU", "બીજા કે બે દિવસ માટે વધારાનું પાણી અને અન્ય પ્રવાહી પીવો.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION3", "Avoid drinks that are carbonated or have caffeine.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION3_HN", "ऐसे पेय से बचें जो कार्बोनेटेड हों या जिनमें कैफीन हो।");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION3_GU", "કાર્બોરેટેડ અથવા કેફીન ધરાવતા પીણાં ટાળો.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION4", "Urinate often.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION4_HN", "बार-बार पेशाब करो");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION4_GU", "વારંવાર પેશાબ કરવો.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION5", "To relieve pain, take a hot bath or lay a heating pad set on low over your lower belly or genital area.");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION5_HN", "दर्द से राहत पाने के लिए, गर्म स्नान करें या अपने निचले पेट या जननांग क्षेत्र पर एक हीटिंग पैड सेट करें।");
        lan.put("POSSIBLE_URINE_INFECTION_SUGGESTION5_GU", "પીડાને દૂર કરવા માટે, ગરમ સ્નાન કરો અથવા તમારા નીચલા પેટ અથવા જનનાંગ વિસ્તાર પર નીચા પર હીટિંગ પેડ મૂકો.");
        lan.put("MEASLES_SUGGESTION1", "Take it easy. Get rest and avoid busy activities.");
        lan.put("MEASLES_SUGGESTION1_HN", "आराम से। आराम करें और व्यस्त गतिविधियों से बचें।");
        lan.put("MEASLES_SUGGESTION1_GU", "આરામ થી કર. આરામ કરો અને વ્યસ્ત પ્રવૃત્તિઓ ટાળો.");
        lan.put("MEASLES_SUGGESTION2", "Drink plenty of fluids.");
        lan.put("MEASLES_SUGGESTION2_HN", "अधिक मात्रा में तरल पदार्थ पीओ।");
        lan.put("MEASLES_SUGGESTION2_GU", "પુષ્કળ પ્રવાહી પીવો.");
        lan.put("MEASLES_SUGGESTION3", "Moisten the air.");
        lan.put("MEASLES_SUGGESTION3_HN", "हवा को नम करें।");
        lan.put("MEASLES_SUGGESTION3_GU", "હવાને ભીની કરો");
        lan.put("MEASLES_SUGGESTION4", "Moisten your nose.");
        lan.put("MEASLES_SUGGESTION4_HN", "अपनी नाक को नम करें।");
        lan.put("MEASLES_SUGGESTION4_GU", "તમારા નાકને ભીનું કરો.");
        lan.put("MEASLES_SUGGESTION5", "Rest your eyes.");
        lan.put("MEASLES_SUGGESTION5_HN", "अपनी आँखें आराम करो।");
        lan.put("MEASLES_SUGGESTION5_GU", "તમારી આંખોને આરામ આપો.");
    }

    public static String getKeyByLanguage(String key, String language) {
        if (language.equals("HN")) {
            key = key + "_HN";
        } else if (language.equals("GU")) {
            key = key + "_GU";
        }
        return lan.get(key);
    }

}
