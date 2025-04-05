package com.epita.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PostContentContract {
    public String content;
    public String media;
    public UUID repost;
    public UUID replyTo;

    public PostContentContract() {
    }

}
