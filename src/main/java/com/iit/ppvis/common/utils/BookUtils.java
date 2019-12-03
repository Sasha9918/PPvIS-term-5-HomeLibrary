package com.iit.ppvis.common.utils;

import com.iit.ppvis.entity.Book;
import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.entity.StorageRecord;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.model.enums.VisitorRole;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

import static com.iit.ppvis.model.enums.BookStatus.PRIVATE;
import static com.iit.ppvis.model.enums.BookStatus.PUBLIC;
import static com.iit.ppvis.model.enums.VisitorRole.ROLE_OWNER;

@UtilityClass
public class BookUtils {

    public static AllBookInfo createAllBookInfo(Book book, CatalogRecord catalogRecord,
                                                StorageRecord storageRecord) {
        var response = new AllBookInfo();
        response.setAuthor(book.getAuthor());
        response.setBookName(book.getBookName());
        response.setGenre(storageRecord.getGenre());
        response.setPublisher(book.getPublisher());
        response.setPublishingYear(book.getPublishingYear());
        response.setRate(catalogRecord.getRate());
        response.setStatus(catalogRecord.getStatus());
        response.setSubject(storageRecord.getSubject());
        return response;
    }

    public static List<BookStatus> prepareBookStatus(VisitorRole role) {
        if (role.equals(ROLE_OWNER)){
            return List.of(PRIVATE, PUBLIC);
        }
        return List.of(PUBLIC);
    }

    public static List<String> deleteBookFromList(List<String> bookNames, String bookName) {
        return bookNames.stream().filter(name -> !name.equals(bookName)).collect(Collectors.toList());
    }

}
