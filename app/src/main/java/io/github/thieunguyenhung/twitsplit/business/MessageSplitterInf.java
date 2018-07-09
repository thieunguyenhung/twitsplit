package io.github.thieunguyenhung.twitsplit.business;

import java.util.List;

public interface MessageSplitterInf {
    int MAX_LENGTH = 50;

    List splitMessage(String chatMessageText);
}
