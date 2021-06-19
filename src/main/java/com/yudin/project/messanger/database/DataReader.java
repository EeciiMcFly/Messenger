package com.yudin.project.messanger.database;

import com.yudin.project.messanger.objects.Dialog;
import com.yudin.project.messanger.objects.Message;
import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DataReader {
    List<Dialog> GetDialogs(String userId);
    Dialog GetDialog(String dialogId);
    User GetUserByName(String userName);
    List<User> GetUsersByName(String userName);
    List<Message> GetMessagesByDialog(String dialogId);
    List<Message> GetNewMessagesByDialog(String dialogId, String lastUpdateTime);
}
