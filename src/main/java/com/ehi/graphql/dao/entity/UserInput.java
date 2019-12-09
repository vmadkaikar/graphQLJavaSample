package com.ehi.graphql.dao.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
public class UserInput implements Serializable {
    public String email;
    public String fname;
    public String lname;
    public String dob;
}
