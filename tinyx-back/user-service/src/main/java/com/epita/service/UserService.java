package com.epita.service;

import com.epita.repository.BlockRepository;
import com.epita.repository.FollowRepository;
import com.epita.repository.LikeRepository;
import com.epita.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    LikeRepository likeRepository;
    @Inject
    FollowRepository followRepository;
    @Inject
    BlockRepository blockRepository;
}
