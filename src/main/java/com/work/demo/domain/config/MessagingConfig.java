package com.work.demo.domain.config;

import com.work.demo.domain.events.WorkEventHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    private static final String TOPIC = "workItemTopic";
    private static final String QUEUE_NAME = "workItem.process";

    @Bean
    public Queue workItemQueue(){
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange workItemExchange(){
        return new TopicExchange(TOPIC);
    }

    @Bean
    public Binding userLoggedInBinding(Queue workItemQueue, TopicExchange workItemExchange){
        return BindingBuilder.bind(workItemQueue).to(workItemExchange).with("workItem.#");
    }
    @Bean
    public com.rabbitmq.client.ConnectionFactory connectionFactory(){
        return new com.rabbitmq.client.ConnectionFactory();
    }

    @Bean
    public SimpleMessageListenerContainer loginContainer(ConnectionFactory connectionFactory,
                                                         WorkEventHandler workEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(new MessageListenerAdapter(workEventHandler, "handleWorkItem"));
        return container;
    }

}
