package com.ehi.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ehi.graphql.dao.entity.Header;
import com.ehi.graphql.dao.entity.Location;
import com.ehi.graphql.dao.entity.User;
import com.ehi.graphql.dao.entity.*;
import com.ehi.graphql.service.EhiGraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class EhiQuery implements GraphQLQueryResolver {

    @Autowired
    private EhiGraphQLService ehiGraphQLService;

    public List<User> getUsers(final int count) {
        return this.ehiGraphQLService.getAllUser(count);
    }

    public Optional<User> getUser(final int id) {
        return this.ehiGraphQLService.getUser(id);
    }

    public AllianceInfo getAllianceInfo(final String allianceId) {
        return this.ehiGraphQLService.getAllianceInfo(allianceId);
    }

    public List<Location> getLocations(final String zip) {
        return this.ehiGraphQLService.getLocation(zip);
    }

    public Header getHeader() {
        return this.ehiGraphQLService.getHeader();
    }
}
