package io.github.thieunguyenhung.twitsplit.models;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Message {
    private String messageText;
    private Calendar sentTime;

    public Message(String messageText, Calendar sentTime) {
        this.messageText = messageText;
        this.sentTime = sentTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Calendar getSentTime() {
        return sentTime;
    }

    public void setSentTime(Calendar sentTime) {
        this.sentTime = sentTime;
    }
}
