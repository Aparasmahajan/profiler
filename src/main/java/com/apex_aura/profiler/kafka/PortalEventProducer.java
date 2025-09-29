//package com.apex_aura.profiler.kafka;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PortalEventProducer {
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public PortalEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publishPortalCreated(Long portalId, String portalName, Long adminId) {
//        PortalEvent event = PortalEvent.builder()
//                .eventType("PORTAL_CREATED")
//                .portalId(portalId)
//                .portalName(portalName)
//                .adminId(adminId)
//                .timestamp(java.time.LocalDateTime.now())
//                .build();
//        kafkaTemplate.send("portal-events", portalId.toString(), event);
//    }
//}
