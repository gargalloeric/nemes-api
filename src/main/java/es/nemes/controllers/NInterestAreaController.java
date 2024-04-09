package es.nemes.controllers;

import es.nemes.models.InterestAreaQuery;
import es.nemes.models.NInterestArea;
import es.nemes.models.NUser;
import es.nemes.models.Zone;
import es.nemes.repositories.InterestAreaDAO;
import es.nemes.repositories.UserDAO;
import es.nemes.repositories.ZoneDAO;
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
    ZoneDAO zoneDAO;

    @Inject
    JsonWebToken jwt;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    @Path("/create")
    public Response createNInterestArea(InterestAreaQuery areaQuery) throws URISyntaxException {
        Zone responseZone = zoneDAO.create(areaQuery.getZone());

        String userEmail = jwt.getClaim("upn");
        NUser user = userDAO.findByEmail(userEmail);
        NInterestArea area = new NInterestArea(
                areaQuery.getName(),
                areaQuery.getDescription(),
                user,
                areaQuery.getZone());

        NInterestArea response = dao.create(area);
        if(response == NInterestArea.NOT_FOUND) return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/interest-area/" + area.getId());
        System.out.println(area);
        return Response.created(uri).build();
    }
}
