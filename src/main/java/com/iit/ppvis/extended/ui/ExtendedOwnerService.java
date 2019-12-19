package com.iit.ppvis.extended.ui;

import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.service.VisitorCountingService;
import com.iit.ppvis.ui.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedOwnerService extends OwnerService {

    @Autowired
    public ExtendedOwnerService(CatalogService catalogService, ProfilesService profilesService,
                                StorageService storageService, VisitorCountingService visitorCountingService,
                                BookRepository bookRepository) {
        super(catalogService, profilesService, storageService, visitorCountingService, bookRepository);
    }

    void holdAuction() {

    }
}
