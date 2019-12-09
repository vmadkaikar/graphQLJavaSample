package com.ehi.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ehi.graphql.dao.entity.User;
import com.ehi.graphql.dao.entity.UserInput;
import com.ehi.graphql.service.EhiGraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EhiMutation implements GraphQLMutationResolver {

    @Autowired
    private EhiGraphQLService ehiGraphQLService;

    public User addUser(UserInput userInput) {
        return this.ehiGraphQLService.addUser(userInput);
    }

    public Optional<User> updateUser(UserInput userInput, int id) {
        return this.ehiGraphQLService.updateUser(userInput, id);
    }
}
