package com.iit.ppvis.extended.ui;

import com.iit.ppvis.entity.enums.VisitorRole;
import com.iit.ppvis.extended.repository.AccountRepository;
import com.iit.ppvis.extended.service.AccountService;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.ui.OwnerService;
import com.iit.ppvis.ui.VisitorService;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedVisitorService extends VisitorService {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @Autowired
    public ExtendedVisitorService(CatalogService catalogService, OwnerService ownerService,
                                  ProfilesService profilesService, StorageService storageService,
                                  BookRepository bookRepository, ProfilesRepository profilesRepository,
                                  AccountService accountService, AccountRepository accountRepository) {
        super(catalogService, ownerService, profilesService, storageService, bookRepository, profilesRepository);
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public Label findVisitor(String bookName, String lastName) {
        if (accountRepository.existsById(lastName)) {
            findBook(bookName, lastName);
            return new Label();
        } else {
            Notification.show("Неверная фамилия");
            return new Label();
        }
    }

    void signUp(String firstName, String lastName, String login, String password, VisitorRole role) {
        accountService.create(firstName, lastName, login, password, role);
    }

    boolean signIn(String login, String password) {
        accountRepository.findByLoginAndPassword(login, password).orElseThrow(() -> {
            Notification.show("Неверный логин и/или пароль");
            throw new IllegalArgumentException();
        });
        return true;
    }

}
