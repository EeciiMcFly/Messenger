package com.yudin.project.messanger.database;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.yudin.project.messanger.objects.Dialog;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoDbDialogRepository implements DialogRepository{
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    private final MongoClient client;
    private MongoCollection<Dialog> dialogCollection;

    public MongoDbDialogRepository(MongoClient client) {
        this.client = client;
    }

    @PostConstruct
    void init() {
        dialogCollection = client.getDatabase("messenger_db").getCollection("dialogs", Dialog.class);
    }

    @Override
    public void save(Dialog dialog) {
        dialog.setId(new ObjectId());
        dialogCollection.insertOne(dialog);
    }

    @Override
    public void removeOneByDialogId(String dialogId) {
        var dialogIdQuery = Filters.eq("dialogId", dialogId);
        dialogCollection.deleteOne(dialogIdQuery);
    }

    @Override
    public Dialog findOneByDialogId(String dialogId) {
        var dialogIdQuery = Filters.eq("dialogId", dialogId);

        return dialogCollection.find(dialogIdQuery).first();
    }

    @Override
    public List<Dialog> findAllByUserId(String userId) {
        var firstUserIdQuery = Filters.eq("firstUserId", userId);
        var secondUserIdQuery = Filters.eq("secondUserId", userId);
        var query = Filters.or(firstUserIdQuery, secondUserIdQuery);

        return dialogCollection.find(query).into(new ArrayList<>());
    }
}
