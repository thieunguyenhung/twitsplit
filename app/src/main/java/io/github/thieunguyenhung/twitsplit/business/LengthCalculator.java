package io.github.thieunguyenhung.twitsplit.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LengthCalculator {
    public static final String MAX_LENGTH_KEY = "maxLength";
    public static final String TOTAL_SENTENCES_KEY = "totalSentence";

    public static Map<String, Integer> calculateMaxLengthAndTotalSentences(int maxLength, final List<String> words) {
        Map<String, Integer> resultMap = new HashMap<>();
        int totalSentences = 0;
        StringBuilder tempMessageBuilder = new StringBuilder();
        for (String word : words) {
            if (tempMessageBuilder.length() + word.length() + 1 > maxLength) {
                totalSentences += 1;
                tempMessageBuilder = new StringBuilder();
            }
            if (tempMessageBuilder.length() > 0) {
                tempMessageBuilder.append(" ");
            }
            tempMessageBuilder.append(word);
        }
        maxLength = maxLength - (String.valueOf(totalSentences).length() * 2 + 2);

        resultMap.put(MAX_LENGTH_KEY, maxLength);
        resultMap.put(TOTAL_SENTENCES_KEY, totalSentences);
        return resultMap;
    }
}
