package com.example.foroHub.domain.topic;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


    @Table(name = "topics")
    @Entity(name = "Topic")
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Topic {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String message;
        private LocalDateTime creationDate;
        @Enumerated(EnumType.STRING)
        private Status status;
        private String author;
        private String course;

        public Topic(TopicRegistrationData topicRegistrationData) {
            this.title = topicRegistrationData.title();
            this.message = topicRegistrationData.message();
            this.creationDate = LocalDateTime.now();
            this.status = Status.PROCESS;
            this.author = topicRegistrationData.author();
            this.course = topicRegistrationData.course();
        }

        public void updateData(TopicRegistrationData topicRegistrationData) {
            this.title = topicRegistrationData.title();
            this.message = topicRegistrationData.message();
            this.creationDate = LocalDateTime.now();
            this.author = topicRegistrationData.author();
            this.course = topicRegistrationData.course();
        }
    }
