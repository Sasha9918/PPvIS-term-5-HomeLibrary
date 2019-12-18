package com.iit.ppvis.ui;

import com.iit.ppvis.api.VisitorService;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.BookService;
import com.iit.ppvis.service.VisitorCountingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//TODO: add success where necessary
//TODO: maybe play with font

@Route
@Component
@RequiredArgsConstructor
public class MainView extends VerticalLayout {

    private final VisitorService visitorService;
    private final BookService bookService;
    private final VisitorCountingService visitorCountingService;
    private final ProfilesRepository profilesRepository;

    @Bean
    public void init() {
        var welcome = new Label("Добро пожаловать");
        var start = new Button("Посетить библиотеку", e -> {
            initStart();
        });
        add(welcome, start);
    }

    private void initStart() {
        removeAll();
        var welcome = new Label("Домашняя библиотека");
        var buttonsLayout = new HorizontalLayout();

        var take = new Button("Взять книгу", e -> {
            removeAll();
            var addingLabel = visitorService.takeBook();
            add(addingLabel);
        });

        var add = new Button("Добавить книгу", e -> {
            removeAll();//TODO: must be in owner service
            var bookName = new TextField("Название");
            var author = new TextField("Автор");
            var publishingYear = new TextField("Год издания");
            var publisher = new TextField("Издательство");
            var status = new TextField("Статус");//TODO: make enum
            var subject = new TextField("Направление");//TODO: make enum
            var genre = new TextField("Жанр");
            var ownerLastName = new TextField("Фамилия владельца");

            //ownerService.add();
        });

        var takeBack = new Button("Вернуть книгу", e -> {
            // enterpriseService.delete(processNumbers(id.getValue(), "Id"));
        });

        var delete = new Button("Удалить книгу", e -> {
            // enterpriseService.delete(processNumbers(id.getValue(), "Id"));
        });
        buttonsLayout.add(take, add, takeBack, delete);
        add(welcome, buttonsLayout);
    }

}
