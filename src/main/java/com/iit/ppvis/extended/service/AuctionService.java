package com.iit.ppvis.extended.service;

import com.iit.ppvis.extended.repository.AuctionRepository;
import com.iit.ppvis.extended.ui.ExtendedOwnerService;
import com.iit.ppvis.repository.BookRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final ExtendedOwnerService extendedOwnerService;

    private final AuctionRepository auctionRepository;
    private final BookRepository bookRepository;

    public void buyBook(String bookName, String auctionName) {
        var auction = auctionRepository.findById(auctionName).orElseThrow(() -> {
            Notification.show(String.format("Аукциона %s не существует", auctionName));
            throw new NullPointerException();
        });

        if (auction.getFinishedAt().isAfter(Instant.now())) {
            var books = auction.getBooks();
            if (!bookRepository.existsById(bookName)) {
                Notification.show(String.format("Книги %s не существует", bookName));
            } else if (!books.contains(bookName)) {
                Notification.show(String.format("Книга %s не выставлена на аукционе %s", bookName, auctionName));
            } else {
                deleteFromList(bookName);
                extendedOwnerService.deleteBook(bookName);
                extendedOwnerService.deleteBookFromProfiles(bookName);
                extendedOwnerService.deleteBookRecords(bookName);

                books.remove(bookName);
                auction.setBooks(books);
                auctionRepository.save(auction);
                Notification.show("Книга успешно куплена");
            }
        } else {
            Notification.show("Аукцион закончился");
        }
    }

    private void deleteFromList(String bookName) {
        var auctions = auctionRepository.findAll();

        auctions.forEach(auction -> {
            var books = auction.getBooks().stream().filter(name -> !name.equals(bookName))
                    .collect(Collectors.toList());
            auction.setBooks(books);
            auctionRepository.save(auction);
        });
    }

}
