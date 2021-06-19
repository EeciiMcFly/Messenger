package com.yudin.project.messanger.controllers;

import com.yudin.project.messanger.dto.MessageDTO;
import com.yudin.project.messanger.dto.requests.GetNewMessagesRequest;
import com.yudin.project.messanger.providers.IMessageProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dialogs/{dialogId}/messages")
public class MessageController {
    private final IMessageProvider messageProvider;

    public MessageController(IMessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @GetMapping
    public List<MessageDTO> GetAllMessages(@PathVariable("dialogId") String dialogId){
        var messages = messageProvider.GetAllMessages(dialogId);

        return messages
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/new")
    public List<MessageDTO> GetNewMessages(@PathVariable("dialogId") String dialogId,
                                           @RequestBody GetNewMessagesRequest getNewMessagesRequest){
        if (!StringUtils.hasText(getNewMessagesRequest.lastRequestedTime))
        {
            return null;
        }

        var messages = messageProvider.GetNewMessages(dialogId, getNewMessagesRequest.lastRequestedTime);

        return messages
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void SendMessage(@PathVariable("dialogId") String dialogId,
                            @RequestBody MessageDTO message){
        if (!StringUtils.hasText(message.getSenderId()) ||
            !StringUtils.hasText(message.getMessageTime()) ||
            !StringUtils.hasText(message.getDialogId()))
        {
            return;
        }

        messageProvider.AddMessage(message, dialogId);
    }

}
