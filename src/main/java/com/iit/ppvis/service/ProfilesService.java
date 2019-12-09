package com.iit.ppvis.service;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.CreateProfileRequest;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;

public interface ProfilesService {

    Profile find(String lastName);

    void create(CreateProfileRequest request);

    void updateReadList(WorkWithReadBookRequest request);

    void deleteBookFromLists(String bookName);

    void updateFavouriteList(WorkWithReadBookRequest request);

    void addToPlannedToReadList(WorkWithBookRequest request);

}
