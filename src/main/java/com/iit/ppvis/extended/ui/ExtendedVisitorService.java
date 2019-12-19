package com.iit.ppvis.extended.ui;

import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.ui.OwnerService;
import com.iit.ppvis.ui.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedVisitorService extends VisitorService {

    @Autowired
    public ExtendedVisitorService(CatalogService catalogService, OwnerService ownerService,
                                  ProfilesService profilesService, StorageService storageService,
                                  BookRepository bookRepository, ProfilesRepository profilesRepository) {
        super(catalogService, ownerService, profilesService, storageService, bookRepository, profilesRepository);
    }

}
