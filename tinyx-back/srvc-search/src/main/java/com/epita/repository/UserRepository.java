package com.epita.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserRepository {
    @Getter
    @Setter
    UUID id = UUID.randomUUID();

    @Getter
    @Setter
    String name;

}
