package com.yudin.project.messanger.database;

import com.yudin.project.messanger.objects.Dialog;
import com.yudin.project.messanger.objects.Message;
import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSource implements DataReader, DataWriter{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final DialogRepository dialogRepository;

    public DataSource(UserRepository userRepository, MessageRepository messageRepository, DialogRepository dialogRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.dialogRepository = dialogRepository;
    }

    @Override
    public List<Dialog> GetDialogs(String userId) {
        return dialogRepository.findAllByUserId(userId);
    }

    @Override
    public Dialog GetDialog(String dialogId) {
        return dialogRepository.findOneByDialogId(dialogId);
    }

    @Override
    public void AddDialog(Dialog dialog) {
        dialogRepository.save(dialog);
    }

    @Override
    public void AddMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void RemoveDialog(String dialogID) {
        dialogRepository.removeOneByDialogId(dialogID);
    }

    @Override
    public User GetUserByNameAndPassword(String userName, String password) {
        return userRepository.findOneByNameAndPassword(userName, password);
    }

    @Override
    public List<User> GetUsersByName(String userName) {
        return userRepository.findAllByUserName(userName);
    }

    @Override
    public List<Message> GetMessagesByDialog(String dialogId) {
        return messageRepository.findAllByDialogId(dialogId);
    }

    @Override
    public List<Message> GetNewMessagesByDialog(String dialogId, String lastUpdateTime) {
        return messageRepository.findAllByDialogIdAndDatetimeAfter(dialogId, lastUpdateTime);
    }

    @Override
    public void AddUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void RemoveUser(String userId) {
        userRepository.removeOneByUserId(userId);
    }
}
