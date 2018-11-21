package com.github.mhdirkse.kerst;

import lombok.Getter;

public enum Member {
    JOHN("John", "john@live.nl"),
    JACK("Jack", "jack@hotmail.com");

    @Getter
    private final String firstName;

    @Getter
    private final String email;

    Member(final String firstName, final String email) {
        this.firstName = firstName;
        this.email = email;
    }
}
