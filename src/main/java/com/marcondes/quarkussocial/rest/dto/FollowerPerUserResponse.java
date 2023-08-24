package com.marcondes.quarkussocial.rest.dto;

import com.marcondes.quarkussocial.domain.model.Follower;
import lombok.Data;

import java.util.List;

@Data
public class FollowerPerUserResponse {
    private Integer followerCount;
    private List<FollowerResponse> content;
}
