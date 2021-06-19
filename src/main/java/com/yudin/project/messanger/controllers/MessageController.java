package com.yudin.project.messanger.controllers;

import com.yudin.project.messanger.dto.MessageDTO;
import com.yudin.project.messanger.dto.requests.GetNewMessagesRequest;
import com.yudin.project.messanger.dto.requests.IsUpdateResponse;
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

    @GetMapping("/isUpdate")
    public IsUpdateResponse IsUpdate(@PathVariable("dialogId") String dialogId){
        var isUpdate = messageProvider.IsUpdate(dialogId);

        return new IsUpdateResponse(isUpdate);
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

        if (!message.getDialogId().equals(dialogId))
        {
            return;
        }
        messageProvider.AddMessage(message, dialogId);
    }

}
