package es.nemes.controllers;

import es.nemes.models.NInterestArea;
import es.nemes.models.NUser;
import es.nemes.repositories.InterestAreaDAO;
import es.nemes.repositories.UserDAO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/interest-area")
public class NInterestAreaController {
    @Inject
    InterestAreaDAO dao;
    @Inject
    UserDAO userDAO;

    @Inject
    JsonWebToken jwt;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    @Path("/create")
    public Response createNInterestArea(NInterestArea area) throws URISyntaxException {
        String userEmail = jwt.getClaim("upn");
        NUser user = userDAO.findByEmail(userEmail);
        area.setUser(user);
        NInterestArea response = dao.create(area);
        return Response.ok().build();
    }
}
