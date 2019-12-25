package com.iit.ppvis.extended.ui;

import com.iit.ppvis.entity.Book;
import com.iit.ppvis.extended.entity.Auction;
import com.iit.ppvis.extended.repository.AccountRepository;
import com.iit.ppvis.extended.repository.AuctionRepository;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.service.VisitorCountingService;
import com.iit.ppvis.ui.OwnerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.iit.ppvis.entity.enums.VisitorRole.ROLE_VISITOR;

@Service
public class ExtendedOwnerService extends OwnerService {

    private final AccountRepository accountRepository;
    private final AuctionRepository auctionRepository;

    @Autowired
    public ExtendedOwnerService(CatalogService catalogService, ProfilesService profilesService,
                                StorageService storageService, VisitorCountingService visitorCountingService,
                                BookRepository bookRepository, AccountRepository accountRepository, AuctionRepository auctionRepository) {
        super(catalogService, profilesService, storageService, visitorCountingService, bookRepository);
        this.accountRepository = accountRepository;
        this.auctionRepository = auctionRepository;
    }

    public Label holdAuction(String login) {
        var account = accountRepository.findByLogin(login).orElseThrow(IllegalArgumentException::new);
        if (account.getRole().equals(ROLE_VISITOR)) {
            Notification.show("Аукционы может проводить только владелец библиотеки");
        }

        var auctionBooks = new ArrayList<String>();
        var books = new ListBox<String>();
        books.setItems(bookRepository.findAll().stream().map(Book::getBookName).collect(Collectors.toList()));
        books.addValueChangeListener(event -> {
            var bookName = String.valueOf(event.getValue());
            if (!auctionBooks.contains(bookName)) {
                auctionBooks.add(bookName);
            }
        });

        var name = new TextField("Название");

        var hold = new Button("Провести", e -> {
            var time = Instant.now();
            var auction = new Auction();
            auction.setName(name.getValue());
            auction.setBooks(auctionBooks);
            auction.setStartedAt(time);
            auction.setFinishedAt(time.plusSeconds(36000));
            auctionRepository.save(auction);
            Notification.show("Аукцион успешно начат");
        });

        var label = new Label();
        label.add(books, name, hold);
        return label;
    }
}
