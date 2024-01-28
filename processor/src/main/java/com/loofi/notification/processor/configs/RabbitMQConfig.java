package com.loofi.notification.processor.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.queue.name}")
  private String queueName;
  @Value("${rabbitmq.exchange.name}")
  private String exchange;
  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  @Bean
  public Queue queue(){
    return new Queue(queueName);
  }

  @Bean
  public TopicExchange exchange(){
    return new TopicExchange(exchange);
  }

  @Bean
  public Binding binding(){
    return BindingBuilder
      .bind(queue())
      .to(exchange())
      .with(routingKey);
  }
}
