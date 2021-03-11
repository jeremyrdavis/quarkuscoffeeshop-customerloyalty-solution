package io.quarkuscoffeeshop.customerloyalty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class KafkaService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    final static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;

    @Incoming("orders-in") @Blocking @Transactional
    public void onOrderIn(final String payload) {

        logger.debug("payload received: {}", payload);
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            if (jsonNode.get("loyaltyMemberId") != null) {
                LoyalCustomerPurchase loyalCustomerPurchase = new LoyalCustomerPurchase(payload, jsonNode.get("loyaltyMemberId").asText());
                loyalCustomerPurchase.persist();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
