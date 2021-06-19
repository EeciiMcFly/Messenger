package com.yudin.project.messanger.controllers;



import com.yudin.project.messanger.dto.requests.AddDialogRequest;
import com.yudin.project.messanger.dto.requests.DeleteDialogRequest;
import com.yudin.project.messanger.dto.DialogDTO;
import com.yudin.project.messanger.providers.IDialogProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dialogs")
public class DialogController {
    private final IDialogProvider dialogProvider;

    public DialogController(IDialogProvider dialogProvider) {
        this.dialogProvider = dialogProvider;
    }

    @GetMapping("/{id}")
    public List<DialogDTO> GetDialog(@PathVariable("id") String userId){
        var dialogs = dialogProvider.getUserDialogs(userId);

        return dialogs
                .stream()
                .map(DialogDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void AddDialog(@RequestBody AddDialogRequest addDialogRequest) {
        if (!StringUtils.hasText(addDialogRequest.getFirstUserId()))
        {
            return;
        }

        if (!StringUtils.hasText(addDialogRequest.getSecondUserId()))
        {
            return;
        }

        //проверка на пользователей
        dialogProvider.addDialog(addDialogRequest);
    }

    @DeleteMapping
    public void DeleteDialog(@RequestBody DeleteDialogRequest deleteDialogRequest){
        if (!StringUtils.hasText(deleteDialogRequest.dialogId))
        {
            return;
        }

        dialogProvider.removeDialog(deleteDialogRequest.dialogId);
    }
}
