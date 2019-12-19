package com.iit.ppvis.ui;

import com.iit.ppvis.api.OwnerService;
import com.iit.ppvis.api.VisitorService;
import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.model.enums.Subject;
import com.iit.ppvis.service.BookService;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.iit.ppvis.model.enums.VisitorRole.ROLE_OWNER;

//TODO: maybe play with font

@Route
@Component
@RequiredArgsConstructor
public class MainView extends VerticalLayout {

    private final BookService bookService;
    private final OwnerService ownerService;
    private final ProfilesService profilesService;
    private final VisitorService visitorService;

    @Bean
    public void init() {
        var welcome = new Label("Добро пожаловать");
        var start = new Button("Посетить библиотеку", e -> initStart());
        add(welcome, start);
    }

    private void initStart() {
        removeAll();
        var welcome = new Label("Домашняя библиотека");
        var buttonsLayout = new HorizontalLayout();

        var take = new Button("Взять книгу", e -> {
            removeAll();
            var takingLabel = visitorService.takeBook();
            add(takingLabel);
        });

        var add = new Button("Добавить книгу", e -> {
            removeAll();
            var bookName = new TextField("Название");
            var author = new TextField("Автор");
            var publishingYear = new IntegerField("Год издания");
            var publisher = new TextField("Издательство");

            var status = new ComboBox<BookStatus>("Статус");
            status.setItems(BookStatus.values());

            var subject = new ComboBox<Subject>("Статус");
            subject.setItems(Subject.values());

            var genre = new TextField("Жанр");
            var ownerLastName = new TextField("Фамилия владельца");

            var addBook = new Button("Добавить", event -> {
                var owner = profilesService.find(ownerLastName.getValue());

                if (owner.getRole().equals(ROLE_OWNER)) {
                    bookService.addToLibrary(author.getValue(), bookName.getValue(), publisher.getValue(),
                            publishingYear.getValue());
                    ownerService.addToCatalog(bookName.getValue(), status.getValue());
                    ownerService.addToStorage(bookName.getValue(), subject.getValue(), genre.getValue());
                } else {
                    Notification.show("Only owners can add books to library");
                }
            });
            add(bookName, author, publishingYear, publisher, status, subject, genre, ownerLastName, addBook);
        });

        var takeBack = new Button("Вернуть книгу", e -> {
            removeAll();
            var returningLabel = visitorService.returnBook();
            add(returningLabel);
        });

        var delete = new Button("Удалить книгу", e -> {
            // enterpriseService.delete(processNumbers(id.getValue(), "Id"));
        });
        buttonsLayout.add(take, add, takeBack, delete);
        add(welcome, buttonsLayout);
    }

}
