package com.railsware.mailtrap.mail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record Address(@NonNull String email, @JsonInclude(JsonInclude.Include.NON_NULL) String name) {
}
