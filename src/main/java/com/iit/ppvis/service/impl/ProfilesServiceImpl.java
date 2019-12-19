package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.enums.VisitorRole;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.iit.ppvis.common.utils.BookUtils.deleteBookFromList;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final CatalogRepository catalogRepository;
    private final ProfilesRepository profilesRepository;

    @Override
    @Transactional
    public void create(String firstName, String lastName, VisitorRole role) {
        var visitor = new Profile();
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setRole(role);
        profilesRepository.save(visitor);
    }

    @Override
    @Transactional
    public void updateReadList(String bookName, String lastName) {
        var profile = profilesRepository.findByLastName(lastName).orElseThrow(() -> {
                    Notification.show(String.format("There is no visitor with last name %s", lastName));
                    throw entityNotFoundException(String.format("There is no visitor with last name %s", lastName));
                }
        );

        var read = profile.getReadBooks();
        read.add(bookName);
        profile.setReadBooks(read);
        profilesRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile find(String lastName) {
        return profilesRepository.findByLastName(lastName).orElseThrow(() -> {
            Notification.show(String.format("There is no visitor with last name %s", lastName));
            throw entityNotFoundException(String.format("There is no visitor with last name %s", lastName));
        });
    }

    @Override
    @Transactional
    public void deleteBookFromLists(String bookName) {
        var profiles = profilesRepository.findAll();

        profiles.forEach(profile -> {
            var processedReadBooks = deleteBookFromList(profile.getReadBooks(), bookName);
            var processedFavouriteBooks = deleteBookFromList(profile.getFavouriteBooks(), bookName);
            var processedPlannedToReadBooks = deleteBookFromList(profile.getPlannedToReadBooks(), bookName);

            profile.setReadBooks(processedReadBooks);
            profile.setFavouriteBooks(processedFavouriteBooks);
            profile.setPlannedToReadBooks(processedPlannedToReadBooks);
            profilesRepository.save(profile);
        });
    }

    @Override
    @Transactional
    public void updateFavouriteList(String bookName, String lastName) {
        var profile = profilesRepository.findByLastName(lastName).orElseThrow(() -> {
            Notification.show(String.format("There is no visitor with last name %s", lastName));
            throw entityNotFoundException(String.format("There is no visitor with last name %s", lastName));
        });

        var favourite = profile.getFavouriteBooks();
        favourite.add(bookName);
        profile.setFavouriteBooks(favourite);
        profilesRepository.save(profile);
        Notification.show(String.format("Book %s successfully added to favourite", bookName));
    }

    @Override
    @Transactional
    public void updatePlannedToReadList(String bookName, String lastName) {
        catalogRepository.findByBookName(bookName).orElseThrow(() -> {
            Notification.show(String.format("There is no record for book with name %s", bookName));
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });

        var profile = profilesRepository.findByLastName(lastName).orElseThrow(() -> {
                    Notification.show(String.format("There is no visitor with last name %s", lastName));
                    throw entityNotFoundException(String.format("There is no visitor with last name %s", lastName));
                }
        );

        var plannedToRead = profile.getPlannedToReadBooks();
        plannedToRead.add(bookName);
        profile.setFavouriteBooks(plannedToRead);
        profilesRepository.save(profile);
    }

}

