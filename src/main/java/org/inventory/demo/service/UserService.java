package org.inventory.demo.service;

import org.inventory.demo.dto.Response;
import org.inventory.demo.dto.UserDto;
import org.inventory.demo.models.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

Response<UserAccount> CreateUserAccount(UserDto userDto);
Response<UserAccount> getUserAccountByUuid(String uuid);

Page<UserAccount> getAllUserAccounts(Pageable pageable);
}
