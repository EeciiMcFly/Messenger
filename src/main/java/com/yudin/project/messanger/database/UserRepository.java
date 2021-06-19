package com.yudin.project.messanger.database;

import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    void save(User user);
    void removeOneByUserId(String userId);
    User findOneByNameAndPassword(String userName, String password);
    User findOneByName(String userName);
    List<User> findAllByUserName(String userName);
}
