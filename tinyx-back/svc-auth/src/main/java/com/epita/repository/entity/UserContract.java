package com.epita.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserContract {
    public UUID id;
    public String username = "ekaterina";
    public String password_hash;
    public Date created_at;
    public Date updated_at;
}
