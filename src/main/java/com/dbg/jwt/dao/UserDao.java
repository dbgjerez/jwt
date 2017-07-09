package com.dbg.jwt.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dbg.jwt.model.user.User;

@Repository
public interface UserDao extends MongoRepository<User, Integer> {

}
