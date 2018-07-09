package io.github.thieunguyenhung.twitsplit.business;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageSplitterImpl implements MessageSplitterInf {

    @Override
    public List<String> splitMessage(String chatMessageText) {
        List<String> words = Arrays.asList(chatMessageText.trim().split(" "));
        if (CollectionUtils.isEmpty(words)) {
            return null;
        }
//        Log.d("ORIGINAL", words.toString());
        List<String> results = new ArrayList<>();
        String tempMessageText = "";
        for (String word : words) {
            if (tempMessageText.length() + word.length() + 1 <= MAX_LENGTH) {
                tempMessageText += " " + word;
                tempMessageText = tempMessageText.trim();
            } else {
                results.add(new String(tempMessageText));
                tempMessageText = "";
            }
        }
        results.add(tempMessageText);
        return results;
    }
}
