package com.iit.ppvis.model;

import lombok.Data;

@Data
public class WorkWithReadBookRequest {

    private String bookName;
    private String visitorLastName;
    private Integer rate;

}
