package com.iit.ppvis.service;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.entity.enums.VisitorRole;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilesService {

    private final CatalogRepository catalogRepository;
    private final ProfilesRepository profilesRepository;

    @Transactional
    public void create(String firstName, String lastName, VisitorRole role) {
        var visitor = new Profile();
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setRole(role);
        profilesRepository.save(visitor);
    }

    @Transactional
    public void updateReadList(String bookName, String lastName) {
        var profile = profilesRepository.findById(lastName).orElseThrow(() -> {
                    Notification.show(String.format("Нет посетителя с фамилией %s", lastName));
                    throw new NullPointerException();
                }
        );

        var read = profile.getReadBooks();
        read.add(bookName);
        profile.setReadBooks(read);
        profilesRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public Profile find(String lastName) {
        return profilesRepository.findById(lastName).orElseThrow(() -> {
            Notification.show(String.format("Нет посетителя с фамилией %s", lastName));
            throw new NullPointerException();
        });
    }

    @Transactional
    public void deleteBookFromLists(String bookName) {
        var profiles = profilesRepository.findAll();

        profiles.forEach(profile -> {
            var processedReadBooks = profile.getReadBooks().stream().filter(name -> !name.equals(bookName))
                    .collect(Collectors.toList());
            var processedFavouriteBooks = profile.getFavouriteBooks().stream().filter(name -> !name.equals(bookName))
                    .collect(Collectors.toList());
            var processedPlannedToReadBooks = profile.getPlannedToReadBooks().stream()
                    .filter(name -> !name.equals(bookName)).collect(Collectors.toList());

            profile.setReadBooks(processedReadBooks);
            profile.setFavouriteBooks(processedFavouriteBooks);
            profile.setPlannedToReadBooks(processedPlannedToReadBooks);
            profilesRepository.save(profile);
        });
    }

    @Transactional
    public void updateFavouriteList(String bookName, String lastName) {
        var profile = profilesRepository.findById(lastName).orElseThrow(() -> {
            Notification.show(String.format("Нет посетителя с фамилией %s", lastName));
            throw new NullPointerException();
        });

        var favourite = profile.getFavouriteBooks();
        favourite.add(bookName);
        profile.setFavouriteBooks(favourite);
        profilesRepository.save(profile);
        Notification.show(String.format("Книга %s успешно добавлена в список любимых", bookName));
    }

    @Transactional
    public void updatePlannedToReadList(String bookName, String lastName) {
        catalogRepository.findById(bookName).orElseThrow(() -> {
            Notification.show(String.format("Книги %s нет в каталоге", bookName));
            throw new NullPointerException();
        });

        var profile = profilesRepository.findById(lastName).orElseThrow(() -> {
                    Notification.show(String.format("Нет посетителя с фамилией %s", lastName));
                    throw new NullPointerException();
                }
        );

        var plannedToRead = profile.getPlannedToReadBooks();
        plannedToRead.add(bookName);
        profile.setPlannedToReadBooks(plannedToRead);
        profilesRepository.save(profile);
    }

}

