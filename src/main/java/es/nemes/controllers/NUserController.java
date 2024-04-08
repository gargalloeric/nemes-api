package es.nemes.controllers;

import es.nemes.models.NUser;
import es.nemes.models.NUserLogin;
import es.nemes.repositories.UserDAO;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/nuser")
public class NUserController {
    @Inject
    UserDAO dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNUsers() {
        return Response.ok(dao.getUsers()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createNUser(NUser user) throws URISyntaxException {
        NUser response = dao.create(user);
        if(response == NUser.NOT_FOUND) return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/nuser/" + user.getId());
        return Response.created(uri).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginNUser(NUserLogin user) throws URISyntaxException {
        NUser response = dao.findByEmail(user.getEmail());
        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (BcryptUtil.matches(user.getPassword(), response.getPassword())){
            return Response.ok().build();
        }
        return Response.status(400).build();
    }

}
