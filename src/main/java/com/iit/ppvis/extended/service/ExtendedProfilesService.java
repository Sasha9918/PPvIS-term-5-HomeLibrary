package com.iit.ppvis.extended.service;

import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedProfilesService extends ProfilesService {

    @Autowired
    public ExtendedProfilesService(CatalogRepository catalogRepository, ProfilesRepository profilesRepository) {
        super(catalogRepository, profilesRepository);
    }

}
