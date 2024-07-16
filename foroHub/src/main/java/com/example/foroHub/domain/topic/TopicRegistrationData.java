package com.example.foroHub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationData(
        @NotNull
        String title,
        @NotNull
        String message,
        @NotNull
        String author,
        @NotNull
        String course
) {
}
