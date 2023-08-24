package com.marcondes.quarkussocial.rest;

import com.marcondes.quarkussocial.domain.model.Follower;
import com.marcondes.quarkussocial.domain.repository.FollowerRepository;
import com.marcondes.quarkussocial.domain.repository.UserRepository;
import com.marcondes.quarkussocial.rest.dto.FollowerPerUserResponse;
import com.marcondes.quarkussocial.rest.dto.FollowerRequest;
import com.marcondes.quarkussocial.rest.dto.FollowerResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;


@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Inject
    public FollowerResource(FollowerRepository followerRepository,
                            UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    @PUT
    @Transactional
    public Response followUser (@PathParam("userId") Long userId, FollowerRequest request){

        if(userId.equals(request.getFollowerId())){
            return Response.status(Response.Status.CONFLICT).entity("Não é possível seguir a você mesmo!").build();
        }

        var user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var follower = userRepository.findById(request.getFollowerId());
        boolean follows = followerRepository.follows(follower, user);

        if (!follows){
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);

            followerRepository.persist(entity);
        }

        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @GET
    public Response listFollowers(@PathParam("userId") long userId){

        var user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var list = followerRepository.findByUser(userId);
        FollowerPerUserResponse responseObject = new FollowerPerUserResponse();
        responseObject.setFollowerCount(list.size());

        var followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());
        responseObject.setContent(followerList);

        return Response.ok(responseObject).build();
    }

}
