package com.railsware.mailtrap.mail;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {

    TEXT("text/html"),
    IMAGE("image/jpeg");

    private final String mime;

    Type(String mime) {
        this.mime = mime;
    }

    @JsonValue
    public String getMime() {
        return mime;
    }
}
