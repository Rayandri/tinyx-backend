package com.epita.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PostContentContract {
    private String content;
    private String media;
    private UUID repost;
    private UUID replyTo;

    public PostContentContract() {
    }

}
