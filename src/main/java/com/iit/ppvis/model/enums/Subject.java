package com.iit.ppvis.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum Subject {

    CHILDISH("CHILDISH"){}, EDUCATIONAL("EDUCATIONAL"), FICTION("FICTION"),
    SCIENCE("SCIENCE");

    private static final Map<String, Subject> MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("CHILDISH", CHILDISH),
            new AbstractMap.SimpleEntry<>("EDUCATIONAL", EDUCATIONAL),
            new AbstractMap.SimpleEntry<>("FICTION", FICTION),
            new AbstractMap.SimpleEntry<>("SCIENCE", SCIENCE));

    private final String authority;

    public static Subject fromAuthority(String authority) {
        return MAP.get(authority);
    }

}
//TODO: add nested enum