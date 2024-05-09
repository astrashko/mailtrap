package com.railsware.mailtrap.mail;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Disposition {
    INLINE("inline"),
    ATTACHMENT("attachment");

    private final String disposition;

    Disposition(String disposition) {
        this.disposition = disposition;
    }

    @JsonValue
    public String getDisposition() {
        return disposition;
    }
}
