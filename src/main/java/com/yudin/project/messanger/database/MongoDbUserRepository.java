package com.yudin.project.messanger.database;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.yudin.project.messanger.objects.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoDbUserRepository implements UserRepository{
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    private final MongoClient client;
    private MongoCollection<User> userCollection;

    public MongoDbUserRepository(MongoClient client) {
        this.client = client;
    }

    @PostConstruct
    void init() {
        userCollection = client.getDatabase("messenger_db").getCollection("users", User.class);
    }

    @Override
    public void save(User user) {
        user.setId(new ObjectId());
        userCollection.insertOne(user);
    }

    @Override
    public void removeOneByUserId(String userId) {
        var userIdQuery = Filters.eq("userId", userId);
        userCollection.deleteOne(userIdQuery);
    }

    @Override
    public User findOneByNameAndPassword(String userName, String password) {
        var nameQuery = Filters.eq("userName", userName);
        var passwordQuery = Filters.eq("password", password);
        var query = Filters.and(nameQuery, passwordQuery);
        return userCollection.find(query)
                .first();
    }

    @Override
    public User findOneByName(String userName) {
        var nameQuery = Filters.eq("userName", userName);
        return userCollection.find(nameQuery)
                .first();
    }

    @Override
    public List<User> findAllByUserName(String userName) {
        var query = Filters.eq("userName", userName);
        return userCollection.find(query).into(new ArrayList<>());
    }
}
