package com.datastax.training.killrvideo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created 2019.
 */
@JsonIgnoreProperties({ "joined", "salt", "userId" })
public class RegisteringUser extends User {

    @JsonProperty("password")
    private String clearTextPassword;

    private Address address;

    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
