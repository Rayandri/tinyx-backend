package com.epita.controller.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FollowRelation {
    /*
     * This class represents a blocker-blocked relation between 2 Users.
     *
     * blocker: The User who initiated the block relation.
     * blocked: The User who is blocked by the blocker.
     */
    public String followerId;
    public String followedID;
}
