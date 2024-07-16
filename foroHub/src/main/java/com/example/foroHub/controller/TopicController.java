package com.example.foroHub.controller;

import com.example.foroHub.domain.topic.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topic", description = "Contains CRUD functionalities for topics")
public class TopicController {

    @Autowired
    private ITopicRepository topicRepository;
    @Autowired
    private TopicService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registers a topic in the database",
            description = "")
    public ResponseEntity<TopicResponseData> registerTopic(@RequestBody @Valid TopicRegistrationData topicRegistrationData, UriComponentsBuilder uriComponentsBuilder) {
        TopicResponseData topicResponseData = service.registerTopic(topicRegistrationData);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicResponseData.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseData);
    }

    @GetMapping
    @Operation(
            summary = "Lists registered topics",
            description = "")
    public ResponseEntity<Page<TopicResponseData>> listTopics(@PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(topicRepository.sortByMostRecent(pagination).map(TopicResponseData::new));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Details a registered topic",
            description = "")
    public ResponseEntity<TopicResponseData> detailTopic(@PathVariable Long id) {
        TopicResponseData topicResponseData = service.detailTopic(id);
        return ResponseEntity.ok(topicResponseData);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Updates a registered topic",
            description = "")
    public ResponseEntity<TopicResponseData> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicRegistrationData topicRegistrationData) {
        TopicResponseData topicResponseData = service.updateTopic(id, topicRegistrationData);
        return ResponseEntity.ok(topicResponseData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Deletes a topic from the database",
            description = "")
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        service.deleteTopic(id);
        return ResponseEntity.ok("Topic deleted");
    }
}