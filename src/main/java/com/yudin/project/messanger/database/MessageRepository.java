package com.yudin.project.messanger.database;

import com.yudin.project.messanger.objects.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository {
    void save(Message message);

    List<Message> findAllByDialogId(String dialogId);
    List<Message> findAllByDialogIdAndDatetimeAfter(String dialogId, String datetime);
    List<Message> findAll();
}
