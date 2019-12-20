package com.iit.ppvis.ui;

import com.iit.ppvis.entity.enums.VisitorRole;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.iit.ppvis.entity.enums.BookStatus.PRIVATE;
import static com.iit.ppvis.entity.enums.BookStatus.PUBLIC;
import static com.iit.ppvis.entity.enums.VisitorRole.ROLE_OWNER;

@Service
@RequiredArgsConstructor
public class VisitorService extends VerticalLayout {

    private final CatalogService catalogService;
    private final OwnerService ownerService;
    private final ProfilesService profilesService;
    private final StorageService storageService;

    private final BookRepository bookRepository;
    private final ProfilesRepository profilesRepository;

    public Label takeBook() {
        var label = new Label();
        var bookName = new TextField("Название книги");
        var visitorLastName = new TextField("Фамилия посетителя");

        var take = new Button("Взять книгу", e -> {
            var findingLabel = findVisitor(bookName.getValue(), visitorLastName.getValue());
            label.add(findingLabel);
        });

        var addBookToPlannedToRead = new Button("Добавить в список планируемых к прочтению", e ->
                addToPlannedToReadList(bookName.getValue(), visitorLastName.getValue()));

        label.add(bookName, visitorLastName, take, addBookToPlannedToRead);
        return label;
    }

    public Label returnBook() {
        var label = new Label();
        var bookName = new TextField("Название книги");
        var visitorLastName = new TextField("Фамилия посетителя");

        var take = new Button("Вернуть", e -> {
            addToReadList(bookName.getValue(), visitorLastName.getValue());
            reportAboutReturning(bookName.getValue(), visitorLastName.getValue());
        });

        var addToFavouriteBookList = new Button("Добавить в список любимых", e ->
                addToFavouriteList(bookName.getValue(), visitorLastName.getValue()));

        label.add(bookName, visitorLastName, take, addToFavouriteBookList);
        return label;
    }

    public Label rateBook() {
        var bookName = new TextField("Название");
        var visitorLastName = new TextField("Фамилия владельца");
        var mark = new IntegerField("Оценка");

        var rate = new Button("Оценить", e -> {
            if (profilesRepository.existsById(visitorLastName.getValue())) {
                catalogService.updateRate(bookName.getValue(), mark.getValue());
            } else {
                Notification.show(String.format("Не существует пользователя с фамилией %s", visitorLastName.getValue()));
            }
        });
        var label = new Label();
        label.add(bookName, visitorLastName, mark, rate);
        return label;
    }

    public Label findVisitor(String bookName, String lastName) {
        if (profilesRepository.existsById(lastName)) {
            findBook(bookName, lastName);
            return new Label();
        } else {
            var label = new Label("Необходима регистрация");
            var createProfile = createProfile();
            label.add(createProfile);
            return label;
        }
    }

    protected void findBook(String bookName, String lastName) {
        var visitor = profilesService.find(lastName);
        var statuses = visitor.getRole().equals(ROLE_OWNER) ? List.of(PRIVATE, PUBLIC) : List.of(PUBLIC);

        bookRepository.findById(bookName).orElseThrow(() -> {
            Notification.show(String.format("Книга %s не существует", bookName));
            throw new NullPointerException();
        });
        catalogService.find(bookName, statuses);
        storageService.find(bookName);

        reportAboutTaking(bookName, lastName);
    }

    private Label createProfile() {
        var label = new Label();

        var firstName = new TextField("Имя");
        var lastName = new TextField("Фамилия");
        var role = new ComboBox<VisitorRole>("Роль");
        role.setItems(VisitorRole.values());

        var take = new Button("Добавить", e -> {
            profilesService.create(firstName.getValue(), lastName.getValue(), role.getValue());
            Notification.show("Профиль успешно добавлен");
        });
        label.add(firstName, lastName, role, take);
        return label;
    }

    private void reportAboutTaking(String bookName, String lastName) {
        ownerService.addVisitorCountingRecord(bookName, lastName);
    }

    private void reportAboutReturning(String bookName, String lastName) {
        ownerService.updateVisitorCountingRecord(bookName, lastName);
    }

    private void addToFavouriteList(String bookName, String lastName) {
        profilesService.updateFavouriteList(bookName, lastName);
    }

    private void addToReadList(String bookName, String lastName) {
        profilesService.updateReadList(bookName, lastName);
    }

    private void addToPlannedToReadList(String bookName, String lastName) {
        profilesService.updatePlannedToReadList(bookName, lastName);
    }

}
