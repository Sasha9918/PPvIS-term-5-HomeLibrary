package com.iit.ppvis.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum BookStatus {

    PRIVATE("PRIVATE"), PUBLIC("PUBLIC");

    private static final Map<String, BookStatus> MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("PRIVATE", PRIVATE),
            new AbstractMap.SimpleEntry<>("PUBLIC", PUBLIC));

    private final String authority;

    public static BookStatus fromAuthority(String authority) {
        return MAP.get(authority);
    }

}
