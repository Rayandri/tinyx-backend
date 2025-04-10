package com.epita.controller.contracts;

import com.epita.repository.entity.Post;
import com.epita.repository.entity.PostContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDisplayContract {
    private Post post;
    private PostContent content;
}
