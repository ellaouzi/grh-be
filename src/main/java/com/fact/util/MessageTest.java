package com.fact.util;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageTest {

    public static void main(String[] args) {
        Message<String> message1 = MessageBuilder.withPayload("test")
                .setHeader("foo", "bar")
                .build();

        Message<String> message2 = MessageBuilder.fromMessage(message1).build();

        System.out.println(message2.getPayload());//("test", message2.getPayload());
        System.out.println(message2.getHeaders().get("foo")); //assertEquals("bar", message2.getHeaders().get("foo"));
    }
}
