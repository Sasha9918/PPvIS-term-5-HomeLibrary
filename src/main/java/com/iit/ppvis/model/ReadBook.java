package com.iit.ppvis.model;

import lombok.Data;

import java.util.Map;

@Data
public class ReadBook {

    private Map<Book, Integer> ratedBook;

}
