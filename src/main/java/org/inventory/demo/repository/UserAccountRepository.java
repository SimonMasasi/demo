package org.inventory.demo.repository;

import org.inventory.demo.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount , Long> {

    Optional<UserAccount> findFirstByUuid(String uuid);

    Optional<UserAccount> findByUserName(String userName);
}
