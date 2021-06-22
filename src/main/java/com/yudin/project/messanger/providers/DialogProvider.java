package com.yudin.project.messanger.providers;

import com.yudin.project.messanger.database.DataReader;
import com.yudin.project.messanger.database.DataWriter;
import com.yudin.project.messanger.dto.requests.AddDialogRequest;
import com.yudin.project.messanger.objects.Dialog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DialogProvider implements IDialogProvider {
    private final DataReader dataReader;
    private final DataWriter dataWriter;

    public DialogProvider(DataReader dataReader, DataWriter dataWriter) {
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    @Override
    public List<Dialog> getUserDialogs(String userId) {
        return dataReader.GetDialogs(userId);
    }

    @Override
    public Dialog addDialog(AddDialogRequest createDialogDTO) {

        var dialogs = dataReader.GetDialogs(createDialogDTO.getFirstUserId());
        for (Dialog dil: dialogs )
        {
            if (dil.getFirstUserId().equals(createDialogDTO.getFirstUserId()) && dil.getSecondUserId().equals(createDialogDTO.getSecondUserId()) ||
                    dil.getFirstUserId().equals(createDialogDTO.getSecondUserId()) && dil.getSecondUserId().equals(createDialogDTO.getFirstUserId() ))
            {
                return dil;
            }
        }

        dialogs = dataReader.GetDialogs(createDialogDTO.getSecondUserId());
        for (Dialog dil: dialogs )
        {
            if (dil.getFirstUserId().equals(createDialogDTO.getFirstUserId()) && dil.getSecondUserId().equals(createDialogDTO.getSecondUserId()) ||
                    dil.getFirstUserId().equals(createDialogDTO.getSecondUserId()) && dil.getSecondUserId().equals(createDialogDTO.getFirstUserId() ))
            {
                return dil;
            }
        }

        var dialogId = UUID.randomUUID().toString();
        var dialog = new Dialog(dialogId, createDialogDTO.getFirstUserId(), createDialogDTO.getSecondUserId());

        dataWriter.AddDialog(dialog);

        return dialog;
    }

    @Override
    public void removeDialog(String dialogId) {
        dataWriter.RemoveDialog(dialogId);
    }
}
