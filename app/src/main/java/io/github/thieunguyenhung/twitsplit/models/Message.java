package io.github.thieunguyenhung.twitsplit.models;

import java.util.Calendar;

public class Message {
    private String messageText;
    private Calendar sentTime;
    private int itemBackgroundResource;

    public Message(String messageText, Calendar sentTime, int itemBackgroundResource) {
        this.messageText = messageText;
        this.sentTime = sentTime;
        this.itemBackgroundResource = itemBackgroundResource;
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

    public int getItemBackgroundResource() {
        return itemBackgroundResource;
    }

    public void setItemBackgroundResource(int itemBackgroundResource) {
        this.itemBackgroundResource = itemBackgroundResource;
    }
}
