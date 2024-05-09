package com.railsware.mailtrap.mail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record Mail(@NonNull Address from, @NonNull List<Address> to, @NonNull String subject, String text, String html,
                   @JsonInclude(JsonInclude.Include.NON_NULL) List<Attachment> attachments) {
}
