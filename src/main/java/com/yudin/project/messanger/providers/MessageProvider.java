package com.yudin.project.messanger.providers;

import com.yudin.project.messanger.database.DataReader;
import com.yudin.project.messanger.database.DataWriter;
import com.yudin.project.messanger.dto.MessageDTO;
import com.yudin.project.messanger.objects.Message;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Component
public class MessageProvider implements IMessageProvider {
    private final DataReader dataReader;
    private final DataWriter dataWriter;
    private final HashMap<String, Boolean> isMessageUpdate;

    public MessageProvider(DataReader dataReader, DataWriter dataWriter) {
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
        isMessageUpdate = new HashMap<>();
    }

    @Override
    public List<Message> GetNewMessages(String dialogId, String lastUpdateTime) {
       return dataReader.GetNewMessagesByDialog(dialogId, lastUpdateTime);
    }

    @Override
    public List<Message> GetAllMessages(String dialogId) {
        return dataReader.GetMessagesByDialog(dialogId);
    }

    @Override
    public void AddMessage(MessageDTO messageDTO, String dialogId) {
        var message = new Message(messageDTO);
        dataWriter.AddMessage(message);
        isMessageUpdate.put(messageDTO.getDialogId(), true);
    }

    @Override
    public boolean IsUpdate(String dialogId) {
        if (!isMessageUpdate.containsKey(dialogId))
            return false;

        var isUpdate = isMessageUpdate.get(dialogId);
        isMessageUpdate.put(dialogId, false);
        return isUpdate;
    }
}
