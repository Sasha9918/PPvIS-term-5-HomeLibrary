package com.iit.ppvis.extended.repository;

import com.iit.ppvis.extended.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByLogin (String login);

    Optional<Account> findByLoginAndPassword (String login, String password);

}
