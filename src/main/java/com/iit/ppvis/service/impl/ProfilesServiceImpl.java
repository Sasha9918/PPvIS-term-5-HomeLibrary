package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.CreateProfileRequest;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.iit.ppvis.common.utils.BookUtils.deleteBookFromList;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityAlreadyExistsException;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final CatalogRepository catalogRepository;
    private final ProfilesRepository profilesRepository;

    @Override
    @Transactional
    public void create(CreateProfileRequest request) {
        checkIfExists(request.getLastName());
        var visitor = new Profile();
        visitor.setFirstName(request.getFirstName());
        visitor.setLastName(request.getLastName());
        visitor.setRole(request.getRole());
        profilesRepository.save(visitor);
    }

    @Override
    @Transactional
    public void updateReadList(WorkWithReadBookRequest request) {
        var lastName = request.getVisitorLastName();
        var profile = profilesRepository.findByLastName(request.getVisitorLastName()).orElseThrow(() ->
                entityNotFoundException(String.format("There is no visitor with last name %s", lastName)));

        var read = profile.getReadBooks();
        read.add(request.getBookName());
        profile.setReadBooks(read);
        profilesRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile find(String lastName) {
        return profilesRepository.findByLastName(lastName).orElseThrow(() ->
                entityNotFoundException(String.format("There is no visitor with last name %s", lastName)));
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
    public void updateFavouriteList(WorkWithReadBookRequest request) {
        var lastName = request.getVisitorLastName();
        var profile = profilesRepository.findByLastName(request.getVisitorLastName()).orElseThrow(() ->
                entityNotFoundException(String.format("There is no visitor with last name %s", lastName)));

        var favourite = profile.getFavouriteBooks();
        favourite.add(request.getBookName());
        profile.setFavouriteBooks(favourite);
        profilesRepository.save(profile);
    }

    @Override
    @Transactional
    public void addToPlannedToReadList(WorkWithBookRequest request) {
        var bookName = request.getBookName();
        catalogRepository.findByBookName(bookName).orElseThrow(() -> {
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });

        var lastName = request.getVisitorLastName();
        var profile = profilesRepository.findByLastName(request.getVisitorLastName()).orElseThrow(() ->
                entityNotFoundException(String.format("There is no visitor with last name %s", lastName)));

        var plannedToRead = profile.getPlannedToReadBooks();
        plannedToRead.add(request.getBookName());
        profile.setFavouriteBooks(plannedToRead);
        profilesRepository.save(profile);
    }

    private void checkIfExists(String lastName) {
        if (profilesRepository.existsByLastName(lastName)) {
            throw entityAlreadyExistsException(String.format("Profile of visitor %s already exists", lastName));
        }
    }

}

