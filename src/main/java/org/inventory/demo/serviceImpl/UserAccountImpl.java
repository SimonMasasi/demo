package org.inventory.demo.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.inventory.demo.dto.Response;
import org.inventory.demo.dto.UserDto;
import org.inventory.demo.models.UserAccount;
import org.inventory.demo.repository.UserAccountRepository;
import org.inventory.demo.service.UserService;
import org.inventory.demo.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class UserAccountImpl implements UserService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public Response<UserAccount> CreateUserAccount(UserDto userDto) {
        if (userDto.getUserName()==null)
            return  new Response<>(true , ResponseCode.NULL_ARGUMENT ,"User name can not be empty" );
        if (userDto.getFirstName()==null)
            return  new Response<>(true , ResponseCode.NULL_ARGUMENT ,"First name can not be empty" );
        if (userDto.getLastName()==null)
            return  new Response<>(true , ResponseCode.NULL_ARGUMENT ,"Last name can not be empty" );
        if (userDto.getPhoneNumber()==null)
            return  new Response<>(true , ResponseCode.NULL_ARGUMENT ,"Phone Number can not be empty" );

        UserAccount account =new UserAccount();

        Optional<UserAccount> optionalByUserName = userAccountRepository.findByUserName(userDto.getUserName());

        if(optionalByUserName.isPresent())
            return  new Response<>(true , ResponseCode.DUPLICATE , "This User name is Already taken");

        account.setUserName(userDto.getUserName());
        account.setEmail(userDto.getEmail());
        account.setFirstName(userDto.getFirstName());
        account.setMiddleName(userDto.getMiddleName());
        UserAccount userAccount =userAccountRepository.save(account);

        return new Response<>(false , ResponseCode.SUCCESS , userAccount , null , "user Created Successfully");

    }

    @Override
    public Response<UserAccount> getUserAccountByUuid(String uuid) {
        if (uuid ==null){
            return new Response<>(true , ResponseCode.NULL_ARGUMENT , "uuid is required");
        }

        Optional<UserAccount> userAccountOptional =userAccountRepository.findFirstByUuid(uuid);


        return userAccountOptional.map(userAccount -> new Response<>(false , ResponseCode.SUCCESS , userAccount , "success"))
                .orElse(new Response<>(true , ResponseCode.NO_RECORD_FOUND , "no Record Found")) ;
    }

    @Override
    public Page<UserAccount> getAllUserAccounts(Pageable pageable) {
        return userAccountRepository.findAll(pageable);
    }
}
