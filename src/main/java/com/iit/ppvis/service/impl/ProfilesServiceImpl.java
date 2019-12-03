package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.CreateProfileRequest;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.iit.ppvis.common.utils.BookUtils.deleteBookFromList;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityAlreadyExistsException;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfilesRepository profilesRepository;

    //TODO: add enum validation
    @Override
    public void createProfile(CreateProfileRequest request) {
        checkIfExists(request.getLastName());
        var visitor = new Profile();
        visitor.setFirstName(request.getFirstName());
        visitor.setLastName(request.getLastName());
        visitor.setRole(request.getRole());
        profilesRepository.save(visitor);
    }

    @Override
    public Profile find(String lastName) {
        return profilesRepository.findByLastName(lastName).orElseThrow(() ->
                entityNotFoundException(String.format("There is no visitor with last name %s", lastName)));
    }

    @Override
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

    private void checkIfExists(String lastName) {
        if (profilesRepository.existsByLastName(lastName)) {
            throw entityAlreadyExistsException(String.format("Profile of visitor %s already exists", lastName));
        }
    }

}

