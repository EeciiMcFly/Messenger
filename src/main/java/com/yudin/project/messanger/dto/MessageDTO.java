package com.yudin.project.messanger.dto;

import com.yudin.project.messanger.objects.Message;

public class MessageDTO {
    private String messageId;
    private String senderId;
    private String dialogId;
    private String messageTime;
    private String text;

    public MessageDTO(String dialogId, String senderId, String messageTime, String text) {
        this.dialogId = dialogId;
        this.senderId = senderId;
        this.messageTime = messageTime;
        this.text = text;
    }

    public MessageDTO(Message message){
        this.messageId = message.getMessageId();
        this.senderId = message.getSenderId();
        this.dialogId = message.getDialogId();
        this.messageTime = message.getDateTime();
        this.text = message.getText();
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getDialogId() {
        return dialogId;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getText() {
        return text;
    }
}
