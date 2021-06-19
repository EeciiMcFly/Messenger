package com.yudin.project.messanger.database;

import com.yudin.project.messanger.objects.Dialog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepository {
    void save(Dialog dialog);
    void removeOneByDialogId(String dialogId);
    Dialog findOneByDialogId(String dialogId);
    List<Dialog> findAllByUserId(String userId);
}
