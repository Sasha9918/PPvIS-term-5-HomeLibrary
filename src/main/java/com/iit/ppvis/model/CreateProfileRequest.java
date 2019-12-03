package com.iit.ppvis.model;

import com.iit.ppvis.model.enums.VisitorRole;
import lombok.Data;

@Data
public class CreateProfileRequest {

    private String firstName;
    private String lastName;
    private VisitorRole role;

}
