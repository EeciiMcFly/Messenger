package com.yudin.project.messanger.dto;

import com.yudin.project.messanger.objects.Dialog;

public class DialogDTO {
    public String dialogId;
    public String firstUserId;
    public String secondUserId;

    public DialogDTO(Dialog dialog) {
        this.dialogId = dialog.getDialogId();
        this.firstUserId = dialog.getFirstUserId();
        this.secondUserId = dialog.getSecondUserId();
    }
}
