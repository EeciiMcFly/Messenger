package com.yudin.project.messanger.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yudin.project.messanger.dto.MessageDTO;
import org.bson.types.ObjectId;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String messageId;
    private String text;
    private String senderId;
    private String dialogId;
    private String dateTime;

    public Message() {
    }

    public Message(MessageDTO messageDTO)
    {
        this.messageId = UUID.randomUUID().toString();
        this.text = messageDTO.getText();
        this.senderId = messageDTO.getSenderId();
        this.dialogId = messageDTO.getDialogId();
        this.dateTime = messageDTO.getMessageTime();
    }

    public Message(String messageId, String text, String senderId, String dialogId, String dateTime) {
        this.messageId = messageId;
        this.text = text;
        this.senderId = senderId;
        this.dialogId = dialogId;
        this.dateTime = dateTime;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getDialogId() {
        return dialogId;
    }

    public String getDateTime() {
        return dateTime;
    }
}
