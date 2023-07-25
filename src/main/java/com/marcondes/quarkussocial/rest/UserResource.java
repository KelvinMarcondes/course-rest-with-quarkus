package com.marcondes.quarkussocial.rest;

import com.marcondes.quarkussocial.rest.dto.CreateUserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response createUser(CreateUserRequest userRequest){
        return Response.ok(userRequest).build();

    }

    @GET
    public Response listAllUsers(CreateUserRequest userRequest){
        return Response.ok().build();

    }
}
