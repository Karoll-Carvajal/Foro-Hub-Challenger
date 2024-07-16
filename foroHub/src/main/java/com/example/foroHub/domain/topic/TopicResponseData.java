package com.example.foroHub.domain.topic;
import lombok.Getter;
import java.time.LocalDateTime;

public record TopicResponseData(
            @Getter
            Long id,
            String title,
            String message,
            LocalDateTime creationDate,
            Status status,
            String author,
            String course) {

        public TopicResponseData(Topic topic) {
            this(topic.getId(),
                    topic.getTitle(),
                    topic.getMessage(),
                    topic.getCreationDate(),
                    topic.getStatus(),
                    topic.getAuthor(),
                    topic.getCourse());
        }
}
