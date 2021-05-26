package com.datastax.training.killrvideo.dao.cassandra;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.testutilities.TestData;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraUserDAO;
import com.datastax.training.killrvideo.testutilities.AbstractDSETest;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.core.UDTValue;

import junit.framework.Assert;

/**
 * DataStax Academy Sample Application
 * <p>
 * Copyright 2019 DataStax
 */
@RunWith(JUnit4.class)
public class UserDAOAddressTest extends AbstractDSETest {

    @Before
    public void cleanupTransactionTables() {
        DseSession session = CassandraSession.getSession();
        session.execute("TRUNCATE user");
    }

    @Test
    public void testAddressToUDT() {

        CassandraUserDAO cu = new CassandraUserDAO();

        UDTValue addressUDT1 = cu.addressToUDT(TestData.TEST_ADDRESS1);
        assertEquals(TestData.TEST_ADDRESS1.getStreet(), addressUDT1.getString("street"));
        assertEquals(TestData.TEST_ADDRESS1.getCity(), addressUDT1.getString("city"));
        assertEquals(TestData.TEST_ADDRESS1.getPostalCode(), addressUDT1.getString("postalcode"));
        assertEquals(TestData.TEST_ADDRESS1.getCountry(), addressUDT1.getString("country"));

        UDTValue addressUDT2 = cu.addressToUDT(TestData.TEST_ADDRESS2);
        assertEquals(TestData.TEST_ADDRESS2.getStreet(), addressUDT2.getString("street"));
        assertEquals(TestData.TEST_ADDRESS2.getCity(), addressUDT2.getString("city"));
        assertEquals(TestData.TEST_ADDRESS2.getPostalCode(), addressUDT2.getString("postalcode"));
        assertEquals(TestData.TEST_ADDRESS2.getCountry(), addressUDT2.getString("country"));

        UDTValue addressUDT3 = cu.addressToUDT(TestData.TEST_ADDRESS3);
        assertEquals(TestData.TEST_ADDRESS3.getStreet(), addressUDT3.getString("street"));
        assertEquals(TestData.TEST_ADDRESS3.getCity(), addressUDT3.getString("city"));
        assertEquals(TestData.TEST_ADDRESS3.getPostalCode(), addressUDT3.getString("postalcode"));
        assertEquals(TestData.TEST_ADDRESS3.getCountry(), addressUDT3.getString("country"));

        assertNull(addressUDT3.getString("postalcode"));
    }

    @Test
    public void testUDTToAddress() {

        CassandraUserDAO cu = new CassandraUserDAO();

        UDTValue addressUDT1 = cu.addressToUDT(TestData.TEST_ADDRESS1);
        Address newAddress1 = cu.UDTToAddress(addressUDT1);

        assertEquals(TestData.TEST_ADDRESS1.getStreet(), newAddress1.getStreet());
        assertEquals(TestData.TEST_ADDRESS1.getCity(), newAddress1.getCity());
        assertEquals(TestData.TEST_ADDRESS1.getPostalCode(), newAddress1.getPostalCode());
        assertEquals(TestData.TEST_ADDRESS1.getCountry(), newAddress1.getCountry());

        UDTValue addressUDT2 = cu.addressToUDT(TestData.TEST_ADDRESS2);
        Address newAddress2 = cu.UDTToAddress(addressUDT2);

        assertEquals(TestData.TEST_ADDRESS2.getStreet(), newAddress2.getStreet());
        assertEquals(TestData.TEST_ADDRESS2.getCity(), newAddress2.getCity());
        assertEquals(TestData.TEST_ADDRESS2.getPostalCode(), newAddress2.getPostalCode());
        assertEquals(TestData.TEST_ADDRESS2.getCountry(), newAddress2.getCountry());

        UDTValue addressUDT3 = cu.addressToUDT(TestData.TEST_ADDRESS3);
        Address newAddress3 = cu.UDTToAddress(addressUDT3);

        assertEquals(TestData.TEST_ADDRESS3.getStreet(), newAddress3.getStreet());
        assertEquals(TestData.TEST_ADDRESS3.getCity(), newAddress3.getCity());
        assertEquals(TestData.TEST_ADDRESS3.getPostalCode(), newAddress3.getPostalCode());
        assertEquals(TestData.TEST_ADDRESS3.getCountry(), newAddress3.getCountry());

    }

    @Test
    public void testAddUserAndRetrieveSameUserWithAddress() throws UserAlreadyExistsException {
        CassandraUserDAO userDAO = new CassandraUserDAO();

        userDAO.addUser(TestData.TEST_USER1);
        userDAO.addAddressToUser(TestData.TEST_USER1.getEmail(), "Home", TestData.TEST_ADDRESS1);

        User savedUser = userDAO.getUser(TestData.TEST_USER1.getEmail());

        assertNotNull(savedUser);
        assertNotNull(savedUser.getUserId());
        assertNotNull(savedUser.getAddresses());

        User modifiedUser1 = (User) TestData.TEST_USER1.clone();
        HashMap<String, Address> addressSet = new HashMap<>();
        addressSet.put("Home", TestData.TEST_ADDRESS1);
        modifiedUser1.setAddresses(addressSet);
        assertEquals(savedUser, modifiedUser1);
    }

    @Test
    public void testAddAddressToUserAndRetrieve() throws UserAlreadyExistsException {
        CassandraUserDAO userDAO = new CassandraUserDAO();

        userDAO.addUser(TestData.TEST_USER1);
        userDAO.addAddressToUser(TestData.TEST_USER1.getEmail(), "Home", TestData.TEST_ADDRESS1);
        userDAO.addAddressToUser(TestData.TEST_USER1.getEmail(), "Billing", TestData.TEST_ADDRESS3);

        User savedUser = userDAO.getUser(TestData.TEST_USER1.getEmail());

        assertNotNull(savedUser);
        assertNotNull(savedUser.getUserId());
        assertNotNull(savedUser.getAddresses());

        User modifiedUser1 = (User) TestData.TEST_USER1.clone();
        HashMap<String, Address> addressSet = new HashMap<>();
        addressSet.put("Home", TestData.TEST_ADDRESS1);
        addressSet.put("Billing", TestData.TEST_ADDRESS3);
        modifiedUser1.setAddresses(addressSet);
        assertEquals(savedUser, modifiedUser1);
    }

}
