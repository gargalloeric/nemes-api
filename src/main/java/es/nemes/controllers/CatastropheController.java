package es.nemes.controllers;

import es.nemes.models.NUser;
import es.nemes.models.NUserLogin;
import es.nemes.repositories.CatastropheDAO;
import es.nemes.repositories.UserDAO;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/catastrophe")
public class CatastropheController {
    @Inject
    CatastropheDAO dao;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatastrophes() {
        return Response.ok(dao.getCatastrophes()).build();
    }
}
