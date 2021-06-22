package com.yudin.project.messanger.providers;

import com.yudin.project.messanger.dto.requests.AddDialogRequest;
import com.yudin.project.messanger.objects.Dialog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IDialogProvider {
    List<Dialog> getUserDialogs(String userId);
    Dialog addDialog(AddDialogRequest dialog);
    void removeDialog(String dialogId);
}
