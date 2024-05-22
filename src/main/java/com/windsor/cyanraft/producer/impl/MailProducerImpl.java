package com.windsor.cyanraft.producer.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.cyanraft.dto.Mail;
import com.windsor.cyanraft.producer.MailProducer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailProducerImpl implements MailProducer {

  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  @Value("${mq.exchange}")
  private String exchange;

  @Value("${mq.routing-key}")
  private String routingKey;

  @Override
  public void send(String receiver) {
    Mail mail =
        Mail.builder()
            .receivers(List.of(receiver))
            .subject("Welcome Letter from CyanRaft")
            .content("Welcome to CyanRaft")
            .build();

    try {
      log.info("Start send mail to mail service");
      Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(mail)).build();
      rabbitTemplate.convertAndSend(exchange, routingKey, message);
      log.info("End send mail to mail service");
    } catch (Exception e) {
      log.error("send message exception!", e);
    }
  }
}
