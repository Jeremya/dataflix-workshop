package com.datastax.training.killrvideo.dao.cassandra;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.testutilities.TestData;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.apache.commons.math3.analysis.function.Add;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraUserDAO;
import com.datastax.training.killrvideo.testutilities.AbstractDSETest;
import com.datastax.driver.dse.DseSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 19/10/2015.
 */
@RunWith(JUnit4.class)
public class UserDAOTest extends AbstractDSETest {

    @Before
    public void cleanupTransactionTables() {
        DseSession session = CassandraSession.getSession();
        session.execute("TRUNCATE user");
    }

    @Test
    public void testAddUserAndRetrieveSameUser() throws UserAlreadyExistsException {
        CassandraUserDAO userDAO = new CassandraUserDAO();

        userDAO.addUser(TestData.TEST_USER1);
        //userDAO.addAddressToUser(TestData.TEST_USER1.getEmail(), "Home", TestData.TEST_ADDRESS1);

        User updatedUser = (User) TestData.TEST_USER1.clone();
        //addAddressToUser(updatedUser, "Home", TestData.TEST_ADDRESS1);

        User savedUser = userDAO.getUser(TestData.TEST_USER1.getEmail());

        assertEquals(savedUser, updatedUser);
    }

//    private void addAddressToUser(User user, String key, Address address) {
//        Map<String, Address> addresses = user.getAddresses();
//        if (addresses == null) {
//            addresses = new HashMap<String, Address>();
//        }
//        addresses.put(key, TestData.TEST_ADDRESS1);
//        user.setAddresses(addresses);
//    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddSameUserTwiceThrowsException() throws UserAlreadyExistsException {

        CassandraUserDAO userDAO = new CassandraUserDAO();

        userDAO.addUser(TestData.TEST_USER2);

        userDAO.addUser(TestData.TEST_USER2);

    }

}
