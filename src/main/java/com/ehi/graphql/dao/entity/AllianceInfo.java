package com.ehi.graphql.dao.entity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AllianceInfo implements Serializable {
    public String allianceId;
    public String serviceHours;
    public String throttle;
    public String channel;
    public String alliancePhone;
    public String allianceName;
    public String allianceCompanyUrl;
    public String subCategory;
    public boolean hasPreferredPharmacyFilter;
    public boolean enableOutOfNetworkPopup;
    public boolean turnOnPreferredPharmacyFilter;
}
