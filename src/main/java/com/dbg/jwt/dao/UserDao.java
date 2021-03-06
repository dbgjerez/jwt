package com.dbg.jwt.dao;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dbg.jwt.model.user.User;

@Repository
public interface UserDao extends MongoRepository<User, ObjectId> {

	public Optional<User> findOneByEmailAndPassword(String email, String password);

}
