package com.ehi.graphql.dao.entity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class Location implements Serializable {
    public String zip;
    public String ssacd;
    public String stateAbbr;
    public String fipsCode;
    public String countyName;
    public String cityName;
    public String savingsAmount;
    public String savingsLevel;
    public String stateName;
}
