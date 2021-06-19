package com.yudin.project.messanger.database;


import com.yudin.project.messanger.objects.Dialog;
import com.yudin.project.messanger.objects.Message;
import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Component;

@Component
public interface DataWriter {
    void AddDialog(Dialog dialog);
    void AddMessage(Message message);
    void AddUser(User user);
    void RemoveUser(String userId);
    void RemoveDialog(String dialogID);
}
