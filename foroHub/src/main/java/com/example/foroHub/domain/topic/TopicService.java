package com.example.foroHub.domain.topic;

import com.example.foroHub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private ITopicRepository topicRepository;

    public TopicResponseData registerTopic(TopicRegistrationData topicRegistrationData) {
        validateDuplicateEntry(topicRegistrationData);
        Topic topic = topicRepository.save(new Topic(topicRegistrationData));
        return new TopicResponseData(topic);
    }

    public TopicResponseData detailTopic(Long id) {
        validate(id);
        Topic topic = topicRepository.getReferenceById(id);
        return new TopicResponseData(topic);
    }

    public TopicResponseData updateTopic(Long id, TopicRegistrationData topicRegistrationData) {
        validate(id);
        Topic topic = topicRepository.getReferenceById(id);
        validateDuplicateEntry(topicRegistrationData);
        topic.updateData(topicRegistrationData);
        return new TopicResponseData(topic);
    }

    public void deleteTopic(Long id) {
        validate(id);
        topicRepository.deleteById(id);
    }

    public void validate(Long id) {
        if (topicRepository.findById(id).isEmpty()) {
            throw new IntegrityValidation("Este ID de tema no fue encontrado.");
        }
    }

    private void validateDuplicateEntry(TopicRegistrationData topicRegistrationData) {
        if (topicRepository.existsByTitulo(topicRegistrationData.title())) {
            throw new IntegrityValidation("Ya existe un tema con este título, por favor modifique su título.");
        }
        if (topicRepository.existsByMensaje(topicRegistrationData.message())) {
            throw new IntegrityValidation("Ya existe un tema con este mensaje, por favor modifica tu mensaje");
        }
    }

}
