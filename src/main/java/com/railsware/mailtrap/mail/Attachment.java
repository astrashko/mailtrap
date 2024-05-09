package com.railsware.mailtrap.mail;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record Attachment(@NonNull String content, @NonNull Type type, @NonNull String filename, @NonNull Disposition disposition, String content_id) {
}