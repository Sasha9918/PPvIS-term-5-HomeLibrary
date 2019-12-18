package com.iit.ppvis.service;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.model.enums.VisitorRole;

public interface ProfilesService {

    Profile find(String lastName);

    void create(String firstName, String lastName, VisitorRole role);

    void updateReadList(WorkWithReadBookRequest request);

    void deleteBookFromLists(String bookName);

    void updateFavouriteList(WorkWithReadBookRequest request);

    void addToPlannedToReadList(WorkWithBookRequest request);

}
