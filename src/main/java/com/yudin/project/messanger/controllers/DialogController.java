package com.yudin.project.messanger.controllers;



import com.yudin.project.messanger.dto.requests.AddDialogRequest;
import com.yudin.project.messanger.dto.requests.DeleteDialogRequest;
import com.yudin.project.messanger.dto.DialogDTO;
import com.yudin.project.messanger.objects.Dialog;
import com.yudin.project.messanger.providers.IDialogProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dialogs")
public class DialogController {
    private final IDialogProvider dialogProvider;

    public DialogController(IDialogProvider dialogProvider) {
        this.dialogProvider = dialogProvider;
    }

    @GetMapping("/{id}")
    public List<DialogDTO> GetDialog(@PathVariable("id") String userId, HttpServletResponse response){
        var dialogs = dialogProvider.getUserDialogs(userId);
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return dialogs
                .stream()
                .map(DialogDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DialogDTO AddDialog(@RequestBody AddDialogRequest addDialogRequest) {
        if (!StringUtils.hasText(addDialogRequest.getFirstUserId()))
        {
            return null;
        }

        if (!StringUtils.hasText(addDialogRequest.getSecondUserId()))
        {
            return null;
        }

        if (addDialogRequest.getFirstUserId().equals(addDialogRequest.getSecondUserId()))
        {
            return null;
        }


        var dialog = dialogProvider.addDialog(addDialogRequest);
        //проверка на пользователей
        return new DialogDTO(dialog);
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
