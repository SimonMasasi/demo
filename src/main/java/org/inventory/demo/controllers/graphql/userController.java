package org.inventory.demo.controllers.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.inventory.demo.dto.Response;
import org.inventory.demo.dto.UserDto;
import org.inventory.demo.models.UserAccount;
import org.inventory.demo.service.UserService;
import org.inventory.demo.utils.paginationutil.PageableConfig;
import org.inventory.demo.utils.paginationutil.PageableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private PageableConfig pageableConfig;

    @GraphQLMutation(name = "CreateUserAccount")
    public Response<UserAccount> CreateUserAccount(@GraphQLArgument(name = "user") UserDto userDto){
        return userService.CreateUserAccount(userDto);
    }

    @GraphQLQuery(name="getUserAccountByUuid")
    public  Response<UserAccount>getUserAccountByUuid(@GraphQLArgument(name = "uuid") String uuid){
        return  userService.getUserAccountByUuid(uuid);
    }

    @GraphQLQuery(name="getAllUserAccounts")
    public Page<UserAccount> getAllUserAccounts(@GraphQLArgument(name = "pageParam")PageableParam pageableParam){
        Pageable pageable =pageableConfig.pageable(pageableParam);
        return  userService.getAllUserAccounts(pageable);
    }

}

