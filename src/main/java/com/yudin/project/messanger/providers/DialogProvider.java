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
    public void addDialog(AddDialogRequest createDialogDTO) {
        var dialogId = UUID.randomUUID().toString();
        var dialog = new Dialog(dialogId, createDialogDTO.getFirstUserId(), createDialogDTO.getSecondUserId());

        dataWriter.AddDialog(dialog);
    }

    @Override
    public void removeDialog(String dialogId) {
        dataWriter.RemoveDialog(dialogId);
    }
}
