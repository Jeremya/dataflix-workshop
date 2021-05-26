package com.datastax.training.killrvideo.model.dao.cassandra;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.*;
import com.datastax.driver.dse.DseSession;
import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.UserDAO;

public class CassandraUserDAO extends CassandraDAO implements UserDAO {

//TODO: Begin custom code here

//TODO: End custom code here

    public CassandraUserDAO() {
        super();

        DseSession session = getCassandraSession();
//TODO: Begin custom code here

//TODO: End custom code here
    }

    @Override
    public boolean addUser(User newUser) throws UserAlreadyExistsException {
        DseSession session = getCassandraSession();

        SimpleStatement statement = new SimpleStatement(
                "INSERT INTO USER (email, joined, user_id, fname, lname, password, salt, phone_numbers) " +
                        "VALUES (:email,:joined,:user_id,:fname,:lname,:password,:salt,:phone_numbers) IF NOT EXISTS",
                newUser.getEmail(), newUser.getJoined(), newUser.getUserId(), newUser.getFirstName(),
                newUser.getLastName(), newUser.getPassword(), newUser.getSalt(), newUser.getPhoneNumbers());


        ResultSet result = session.execute(statement);

        if (!result.wasApplied()) {
            throw new UserAlreadyExistsException(
                    "Could not save user with the specified id. A duplicate already exists");
        }
        return true;
    }

    @Override
    public User getUser(String email) {
        DseSession session = getCassandraSession();

        ResultSet resultSet = session.execute("SELECT * FROM user WHERE email = '" + email + "'");

        if (resultSet.isExhausted()) {
            return null;
        }

        Row row = resultSet.one();

        User newUser = new User();
        newUser.setEmail(row.getString("email"));
        newUser.setFirstName(row.getString("fname"));
        newUser.setLastName(row.getString("lname"));
        newUser.setJoined(row.get("joined", Date.class));
        newUser.setPassword(row.getBytes("password"));
        newUser.setSalt(row.getBytes("salt"));
        newUser.setUserId(row.getUUID("user_id"));

        newUser.setPhoneNumbers(row.getMap("phone_numbers", String.class, BigDecimal.class));

        //TODO: Add custom code here

        //TODO: End custom code here

        return newUser;

    }

    @Override
    public boolean addAddressToUser(String email, String addressName, Address newAddress) {
        DseSession session = getCassandraSession();

        //TODO: Begin custom code here

        //TODO: End custom code here

        //TODO: Add custom BoundStatement here

        //TODO: End custom BoundStatement here

        return true;
    }

    public UDTValue addressToUDT(Address address) {
        if (address != null) {

            //TODO: Add your custom code here

            //TODO: End your custom code here

        }
        return null;
    }

    public Address UDTToAddress(UDTValue udtValue) {
        Address newAddress = new Address();
        //TODO: Begin custom code here
        
 
        
        //TODO: End custom code here
        return newAddress;
    }


    @Override
    public boolean updateUser(User updatedUser) {
        throw new UnsupportedOperationException("Not done yet!");
    }

}
