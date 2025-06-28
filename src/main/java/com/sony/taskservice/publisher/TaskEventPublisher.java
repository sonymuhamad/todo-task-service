package com.sony.taskservice.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

// TaskEventPublisher.java
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskEventPublisher {

    private final KafkaTemplate<String, Object> template;
    private static final String SEND_MAIL_TOPIC = "send-mail";

    public void publish(String taskId) {
        Map<String, String> payload = Map.of("task_id", taskId);

        template.send(SEND_MAIL_TOPIC, taskId, payload)
                .whenComplete((meta, ex) -> {
                    if (ex == null) {
                        log.debug("Mail task {} published to {}",
                                taskId, meta.toString());
                    } else {
                        log.error("Failed to publish mail task {}", taskId, ex);
                    }
                });
    }
}
