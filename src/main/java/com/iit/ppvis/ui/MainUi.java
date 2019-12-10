package com.iit.ppvis.ui;

import com.iit.ppvis.api.OwnerService;
import com.iit.ppvis.api.VisitorService;
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

@Route
@Component
@RequiredArgsConstructor
public class MainUi extends VerticalLayout {

    private final OwnerService ownerService;
    private final VisitorService visitorService;

    @Bean
    public void init() {
        var welcome = new Label("Добро пожаловать");
        var start = new Button("Посетить библиотеку", e -> {
            initStart();
        });
        var layout = new HorizontalLayout();
        layout.add(start);
        welcome.add(layout);
    }

    private void initStart() {
        removeAll();
        var welcome = new Label("Домашняя библиотека");
        var buttonsLayout = new HorizontalLayout();

        var take = new Button("Взять книгу", e -> {
            visitorService.takeBook();
        });

        var add = new Button("Добавить книгу", e -> {
            removeAll();
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
        buttonsLayout.add(take,add,takeBack,delete);
        welcome.add(buttonsLayout);
    }

}
