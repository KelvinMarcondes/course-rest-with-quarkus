package com.marcondes.quarkussocial.rest.dto;

import com.marcondes.quarkussocial.domain.model.Follower;
import lombok.Data;

@Data
public class FollowerResponse {
    private long id;
    private String name;

    public FollowerResponse() {
    }

    public FollowerResponse (Follower follower){
        this(follower.getFollower().getId(), follower.getFollower().getName());
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
