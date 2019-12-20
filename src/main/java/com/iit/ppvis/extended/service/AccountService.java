package com.iit.ppvis.extended.service;

import com.iit.ppvis.entity.enums.VisitorRole;
import com.iit.ppvis.extended.entity.Account;
import com.iit.ppvis.extended.repository.AccountRepository;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends ProfilesService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(CatalogRepository catalogRepository, ProfilesRepository profilesRepository, AccountRepository accountRepository) {
        super(catalogRepository, profilesRepository);
        this.accountRepository = accountRepository;
    }

    public void create(String firstName, String lastName, String login, String password, VisitorRole role) {
        var visitor = new Account();
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setLogin(login);
        visitor.setPassword(password);
        visitor.setRole(role);
        accountRepository.save(visitor);
        Notification.show("Успешная регистрация");
    }
}
