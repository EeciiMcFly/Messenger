package com.yudin.project.messanger.providers;

import com.yudin.project.messanger.dto.MessageDTO;
import com.yudin.project.messanger.objects.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IMessageProvider {
    List<Message> GetNewMessages(String dialogId, String lastUpdateTime);
    List<Message> GetAllMessages(String dialogId);
    void AddMessage(MessageDTO message, String dialogId);
    boolean IsUpdate(String dialogId);
}
