package com.iit.ppvis.service;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.CreateProfileRequest;

public interface ProfilesService {

    void createProfile(CreateProfileRequest request);

    Profile find(String lastName);

    void deleteBookFromLists(String bookName);

}
