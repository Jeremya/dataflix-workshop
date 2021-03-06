package com.datastax.training.killrvideo.model.dao;

import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.model.UserDoesNotMatchException;

/**
 * Copyright 2019 DataStax Academy
 * Container for Address UDT
 */

public interface UserDAO {

    boolean addUser(User newUser) throws UserAlreadyExistsException;

    User getUser(String email);

    boolean addAddressToUser(String email, String addressName, Address newAddress);

    boolean updateUser(User updatedUser) throws UserDoesNotMatchException;

}
