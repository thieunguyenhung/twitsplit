package io.github.thieunguyenhung.twitsplit.business;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MessageSplitterImpl implements MessageSplitterInf {
    private static int MAX_LENGTH;

    public MessageSplitterImpl() {
        MAX_LENGTH = MessageSplitterInf.MAX_LENGTH;
    }

    @Override
    public List<String> splitMessage(String chatMessageText) {
        final List<String> words = Arrays.asList(chatMessageText.trim().split(" "));
        if (CollectionUtils.isEmpty(words)) {
            return null;
        }

        int currentIndicatorLength = -1;
        int nextIndicatorLength = 0;
        int currentMaxLength = MAX_LENGTH;
        int totalSentences = -1;
        while (currentIndicatorLength != nextIndicatorLength) {
            int tempMaxLength = LengthCalculator.calculateMaxLengthAndTotalSentences(currentMaxLength, words).get(LengthCalculator.MAX_LENGTH_KEY);
            currentIndicatorLength = currentMaxLength - tempMaxLength;

            Map<String, Integer> nextLengthMap = LengthCalculator.calculateMaxLengthAndTotalSentences(tempMaxLength, words);
            int nextMaxLength = nextLengthMap.get(LengthCalculator.MAX_LENGTH_KEY);
            nextIndicatorLength = tempMaxLength - nextMaxLength;

            if (currentIndicatorLength == nextIndicatorLength) {
                MAX_LENGTH = tempMaxLength;
                totalSentences = nextLengthMap.get(LengthCalculator.TOTAL_SENTENCES_KEY) + 1;
            } else {
                currentMaxLength = nextMaxLength;
                currentIndicatorLength = nextIndicatorLength;
                nextIndicatorLength = 0;
            }
        }

        List<String> results = new ArrayList<>();
        StringBuilder tempMessageBuilder = new StringBuilder();
        int sentencesCounter = 1;

        for (String word : words) {
            if (tempMessageBuilder.length() + word.length() + 1 > MAX_LENGTH) {
                results.add(sentencesCounter + "/" + totalSentences + " " + tempMessageBuilder.toString());
                sentencesCounter += 1;
                tempMessageBuilder = new StringBuilder();
            }
            if (tempMessageBuilder.length() > 0) {
                tempMessageBuilder.append(" ");
            }
            tempMessageBuilder.append(word);
        }
        results.add(sentencesCounter + "/" + totalSentences + " " + tempMessageBuilder.toString());

        return results;
    }
}