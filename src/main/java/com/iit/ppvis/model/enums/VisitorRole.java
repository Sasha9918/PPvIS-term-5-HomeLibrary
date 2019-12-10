package com.iit.ppvis.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum VisitorRole {

    ROLE_VISITOR("ROLE_VISITOR"), ROLE_OWNER("ROLE_OWNER");

    private static final Map<String, VisitorRole> MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("ROLE_VISITOR", ROLE_VISITOR),
            new AbstractMap.SimpleEntry<>("ROLE_OWNER", ROLE_OWNER));

    private final String authority;

    public static VisitorRole fromAuthority(String authority) {
        return MAP.get(authority);
    }

}
