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

    private UserType addressType;
    private PreparedStatement addAddressToUserStatement;

    public CassandraUserDAO() {
        super();

        DseSession session = getCassandraSession();

        addressType = session.getCluster()
                .getMetadata()
                .getKeyspace(getCassandraSession()
                        .getLoggedKeyspace())
                .getUserType("address");

        addAddressToUserStatement = session
                .prepare("UPDATE user set addresses[:addressName] = :address WHERE " + "email = :email");
    }

    @Override
    public boolean addUser(User newUser) throws UserAlreadyExistsException {
        DseSession session = getCassandraSession();

        SimpleStatement statement = new SimpleStatement(
                "INSERT INTO USER (email, joined, user_id, fname, lname, password, salt, phone_numbers, addresses) " +
                        "VALUES (:email,:joined,:user_id,:fname,:lname,:password,:salt,:phone_numbers,:addresses) IF NOT EXISTS",
                newUser.getEmail(), newUser.getJoined(), newUser.getUserId(), newUser.getFirstName(),
                newUser.getLastName(), newUser.getPassword(), newUser.getSalt(), newUser.getPhoneNumbers(), newUser.getAddresses());


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

        // Here's the section that retrieves the addresses and maps from the UDT to the class
        Map<String, UDTValue> uDTAddresses = row.getMap("addresses", String.class, UDTValue.class);
        Map<String, Address> addresses = new HashMap<>(uDTAddresses.size());

        for (Map.Entry<String, UDTValue> entry : uDTAddresses.entrySet())
            addresses.put(entry.getKey(), UDTToAddress(entry.getValue()));

        newUser.setAddresses(addresses);

        return newUser;
    }

    @Override
    public boolean addAddressToUser(String email, String addressName, Address newAddress) {
        DseSession session = getCassandraSession();

        BoundStatement boundStatement =
                addAddressToUserStatement.bind()
                        .setString("email", email)
                        .setString("addressName", addressName)
                        .setUDTValue("address", addressToUDT(newAddress));

        session.execute(boundStatement);
        return true;
    }

    public UDTValue addressToUDT(Address address) {
        if (address == null) {
            return null;
        }

        return addressType.newValue()
                .setString("street", address.getStreet())
                .setString("city", address.getCity())
                .setString("country", address.getCountry())
                .setString("postalcode", address.getPostalCode());
    }

    public Address UDTToAddress(UDTValue udtValue) {

        Address newAddress = new Address();

        newAddress.setCity(udtValue.getString("city"));
        newAddress.setCountry(udtValue.getString("country"));
        newAddress.setStreet(udtValue.getString("street"));
        newAddress.setPostalCode(udtValue.getString("postalCode"));

        return newAddress;
    }

    @Override
    public boolean updateUser(User updatedUser) {
        throw new UnsupportedOperationException("Not done yet!");
    }

}
