package com.datastax.training.killrvideo.model.dao.cassandra;

import java.math.BigDecimal;
//import java.nio.charset.Charset;
//import java.util.Collection;
import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;

import com.datastax.driver.core.*;
import com.datastax.driver.dse.DseSession;
//import com.datastax.spark.connector.types.UserDefinedType;
import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.UserDAO;
//import org.jboss.netty.handler.codec.socks.SocksMessage;

public class CassandraUserDAO extends CassandraDAO implements UserDAO {

    private UserType addressType;
    private PreparedStatement addAddressToUserStatement;
    //private Collection<String> fieldNames;

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

        // TODO: You fill in this code

        // TODO: Your added code ends here

        return true;
    }

    @Override
    public User getUser(String email) {
        DseSession session = getCassandraSession();

        User newUser = new User();
        Row row = null;

        // TODO: You fill in this code

        // TODO: Your added code ends here

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

