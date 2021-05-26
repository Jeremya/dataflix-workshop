package com.datastax.training.killrvideo.model;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created on 17/10/2015.
 */

@JsonIgnoreProperties({ "password", "salt" })
public class User implements Cloneable {
    private String email;
    private String firstName;
    private String lastName;
    private Date joined;
    private ByteBuffer password;
    private ByteBuffer salt;
    private UUID userId;
    private Map<String, BigDecimal> phoneNumbers;
    private Map<String, Address> addresses;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public ByteBuffer getPassword() {
        return password;
    }

    public void setPassword(ByteBuffer password) {
        this.password = password;
    }

    public ByteBuffer getSalt() {
        return salt;
    }

    public void setSalt(ByteBuffer salt) {
        this.salt = salt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address> addresses) {
        this.addresses = addresses;
    }

    public Map<String, BigDecimal> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<String, BigDecimal> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (joined != null ? !joined.equals(user.joined) : user.joined != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        if (phoneNumbers != null ? !mapEquals(phoneNumbers, user.phoneNumbers) : user.phoneNumbers != null) return false;
        if (addresses != null ? !mapEquals(addresses, user.addresses) : user.addresses != null) return false;
        return userId != null ? userId.equals(user.userId) : user.userId == null;

    }

    private static <K,V> boolean mapEquals(Map<K,V> actual, Map<K,V> expected) {

        if (actual == null) {
            return expected == null;
        }
        if (expected == null) return false;

        if (actual.size() != expected.size()) return false;

        for (Map.Entry<K,V> entry : expected.entrySet()) {
            V vActual = actual.get(entry.getKey());
            if ((vActual == null) || !vActual.equals(entry.getValue())) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (joined != null ? joined.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (phoneNumbers != null ? User.getMapHashcode(phoneNumbers) : 0);
        result = 31 * result + (addresses != null ? User.getMapHashcode(addresses) : 0);
        return result;
    }

    private static <K,V> int getMapHashcode(Map<K,V> map) {
        int result = 37;
        if (map != null) {
            for (Map.Entry<K,V> entry : map.entrySet()) {
                result = 31 * result + entry.getKey().hashCode();
                V value = entry.getValue();
                result = 31 * result + (value == null ? 0 : value.hashCode());
            }
        }
        return result;
    }

    @Override
    public Object clone() {
        User clone = new User();

        clone.email = this.email;
        clone.firstName = this.firstName;
        clone.lastName = this.lastName;
        clone.joined = this.joined;
        clone.password = this.password;
        clone.salt = this.salt;
        clone.userId = this.userId;
        clone.phoneNumbers = cloneMap(this.phoneNumbers);
        clone.addresses = cloneMap(this.addresses);

        return clone;
    }

    private static <K,V> Map<K,V> cloneMap(Map<K,V> map) {
        Map<K,V> clone = null;
        if (map != null) {
            clone = new HashMap<K, V>();
            for (Map.Entry<K,V> entry : map.entrySet()) {
                clone.put(entry.getKey(), entry.getValue());
            }
        }
        return clone;
    }
}