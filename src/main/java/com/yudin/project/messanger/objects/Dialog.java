package com.yudin.project.messanger.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dialog {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String dialogId;
    private String firstUserId;
    private String secondUserId;

    public Dialog() {
    }

    public Dialog(String dialogId, String firstUserId, String secondUserId) {
        this.dialogId = dialogId;
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public void setFirstUserId(String firstUserId) {
        this.firstUserId = firstUserId;
    }

    public void setSecondUserId(String secondUserId) {
        this.secondUserId = secondUserId;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDialogId() {
        return dialogId;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public String getSecondUserId() {
        return secondUserId;
    }
}
