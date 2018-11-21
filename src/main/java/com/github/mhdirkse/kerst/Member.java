package com.github.mhdirkse.kerst;

import lombok.Getter;

public enum Member {
    MARTIJN("Martijn", "mhdirkse@live.nl"),
    ARRI("Arri", "mhdirkse@live.nl"),
    WERA("Wera", "mhdirkse@live.nl"),
    LIESBETH("Liesbeth", "mhdirkse@live.nl"),
    DAVID("David", "mhdirkse@live.nl");

    @Getter
    private final String firstName;

    @Getter
    private final String email;

    Member(final String firstName, final String email) {
        this.firstName = firstName;
        this.email = email;
    }
}
