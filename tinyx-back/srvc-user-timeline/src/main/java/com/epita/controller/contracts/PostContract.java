package com.epita.controller.contracts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostContract {
    public enum PostAction {
        CREATE,
        DELETE,
        LIKED,
        UNLIKED,
    }
    public UUID id;
    public UUID author;
    public Date updated_at;
    public PostAction action;
}