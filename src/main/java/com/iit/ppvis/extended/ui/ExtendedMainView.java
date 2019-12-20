package com.iit.ppvis.extended.ui;

import com.iit.ppvis.entity.enums.BookStatus;
import com.iit.ppvis.entity.enums.Genre;
import com.iit.ppvis.entity.enums.Subject;
import com.iit.ppvis.entity.enums.VisitorRole;
import com.iit.ppvis.extended.service.AccountService;
import com.iit.ppvis.extended.service.AuctionService;
import com.iit.ppvis.service.BookService;
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

import static com.iit.ppvis.entity.enums.VisitorRole.ROLE_OWNER;

@Component
@Route("extended")
@RequiredArgsConstructor
public class ExtendedMainView extends VerticalLayout {

    private final AuctionService auctionService;
    private final BookService bookService;
    private final ExtendedOwnerService extendedOwnerService;
    private final AccountService accountService;
    private final ExtendedVisitorService extendedVisitorService;

    @Bean
    public void extendedInit() {
        var welcome = new Label("Добро пожаловать");
        var start = new Button("Посетить библиотеку", e -> {
            removeAll();

            var firstName = new TextField("Имя");
            var lastName = new TextField("Фамилия");
            var login = new TextField("Логин");
            var password = new TextField("Пароль");
            var role = new ComboBox<VisitorRole>("Роль");
            role.setItems(VisitorRole.values());

            var signIn = new Button("Авторизоваться", event -> {
                if (extendedVisitorService.signIn(login.getValue(), password.getValue())) {
                    initStart(login.getValue());
                }
            });
            var signUp = new Button("Зарегистрироваться", event -> extendedVisitorService.signUp(
                    firstName.getValue(), lastName.getValue(), login.getValue(), password.getValue(), role.getValue()));
            add(firstName, lastName, login, password, role, signIn, signUp);
        });
        add(welcome, start);
    }

    private void initStart(String login) {
        removeAll();
        var welcome = new Label("Домашняя библиотека");
        var buttonsLayout = new HorizontalLayout();

        var take = new Button("Взять книгу", e -> {
            var takingLabel = extendedVisitorService.takeBook();
            add(takingLabel);
        });

        var add = new Button("Добавить книгу", e -> {
            var bookName = new TextField("Название");
            var author = new TextField("Автор");
            var publishingYear = new IntegerField("Год издания");
            var publisher = new TextField("Издательство");

            var status = new ComboBox<BookStatus>("Статус");
            status.setItems(BookStatus.values());

            var subject = new ComboBox<Subject>("Направление");
            subject.setItems(Subject.values());

            var genre = new ComboBox<Genre>("Жанр");
            genre.setItems(Genre.values());
            var ownerLastName = new TextField("Фамилия владельца");

            var addBook = new Button("Добавить", event -> {
                var owner = accountService.find(ownerLastName.getValue());

                if (owner.getRole().equals(ROLE_OWNER)) {
                    bookService.addToLibrary(author.getValue(), bookName.getValue(), publisher.getValue(),
                            publishingYear.getValue());
                    extendedOwnerService.addToCatalog(bookName.getValue(), status.getValue());
                    extendedOwnerService.addToStorage(bookName.getValue(), subject.getValue(), genre.getValue());
                } else {
                    Notification.show("Только владельцы библиотеки могут добавлять книги");
                }
            });
            add(bookName, author, publishingYear, publisher, status, subject, genre, ownerLastName, addBook);
        });

        var rate = new Button("Оценить книгу", e -> {
            var ratingLabel = extendedVisitorService.rateBook();
            add(ratingLabel);
        });

        var takeBack = new Button("Вернуть книгу", e -> {
            var returningLabel = extendedVisitorService.returnBook();
            add(returningLabel);
        });

        var delete = new Button("Удалить книгу", e -> {
            var bookName = new TextField("Название");
            var ownerLastName = new TextField("Фамилия владельца");

            var deleteBook = new Button("Удалить", event -> {
                var owner = accountService.find(ownerLastName.getValue());

                if (owner.getRole().equals(ROLE_OWNER)) {
                    extendedOwnerService.deleteBookFromProfiles(bookName.getValue());
                    extendedOwnerService.deleteBookRecords(bookName.getValue());
                    extendedOwnerService.deleteBook(bookName.getValue());
                } else {
                    Notification.show("Только владельцы библиотеки могут удалять книги");
                }
            });
            add(bookName, ownerLastName, deleteBook);
        });

        var hold = new Button("Провести аукцион", e -> {
            var holdingLabel = extendedOwnerService.holdAuction(login);
            add(holdingLabel);
        });

        var visit = new Button("Купить книгу", e -> {
            var bookName = new TextField("Название книги");
            var auctionName = new TextField("Название аукциона");
            var buy = new Button("Купить", event -> auctionService.buyBook(bookName.getValue(),
                    auctionName.getValue()));
            add(bookName, auctionName, buy);
        });

        buttonsLayout.add(take, add, takeBack, rate, hold, visit, delete);
        add(welcome, buttonsLayout);
    }

}
