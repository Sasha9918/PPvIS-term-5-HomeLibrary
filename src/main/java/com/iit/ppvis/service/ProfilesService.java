package com.iit.ppvis.service;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.enums.VisitorRole;

public interface ProfilesService {

    Profile find(String lastName);

    void create(String firstName, String lastName, VisitorRole role);

    void updateReadList(String bookName, String lastName);

    void deleteBookFromLists(String bookName);

    void updateFavouriteList(String bookName, String lastName);

    void updatePlannedToReadList(String bookName, String lastName);

}
