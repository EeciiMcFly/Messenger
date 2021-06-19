package com.yudin.project.messanger.database;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.yudin.project.messanger.objects.Message;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoDbMessageRepository implements MessageRepository{
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    private final MongoClient client;
    private MongoCollection<Message> messageCollection;

    public MongoDbMessageRepository(MongoClient client) {
        this.client = client;
    }

    @PostConstruct
    void init() {
        messageCollection = client.getDatabase("messenger_db").getCollection("messages", Message.class);
    }

    @Override
    public void save(Message message) {
        message.setId(new ObjectId());
        messageCollection.insertOne(message);
    }

    @Override
    public List<Message> findAllByDialogId(String dialogId) {
        var dialogIdQuery = Filters.eq("dialogId", dialogId);

        return messageCollection.find(dialogIdQuery).into(new ArrayList<>());
    }

    @Override
    public List<Message> findAllByDialogIdAndDatetimeAfter(String dialogId, String datetime) {
        var dialogIdQuery = Filters.eq("dialogId", dialogId);
        var dateTimeQuery = Filters.gt("dateTime", datetime);
        var query = Filters.and(dialogIdQuery, dateTimeQuery);

        return messageCollection.find(query).into(new ArrayList<>());
    }

    @Override
    public List<Message> findAll() {
        return messageCollection.find().into(new ArrayList<>());
    }
}
