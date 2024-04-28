package es.nemes.controllers;

import es.nemes.models.SubscriptionQuery;
import es.nemes.models.Subscription;
import es.nemes.models.NUser;
import es.nemes.models.Zone;
import es.nemes.repositories.SubscriptionDAO;
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

@Path("/subscription")
public class SubscriptionController {
    @Inject
    SubscriptionDAO dao;
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
    public Response createNInterestArea(SubscriptionQuery subscriptionQuery) throws URISyntaxException {
        String userEmail = jwt.getClaim("upn");
        NUser user = userDAO.findByEmail(userEmail);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Subscription subscription = new Subscription(
                subscriptionQuery.getName(),
                subscriptionQuery.getDescription(),
                user,
                subscriptionQuery.getZone(),
                subscriptionQuery.getEvents());

        Subscription response = dao.create(subscription);
        if(response == Subscription.NOT_FOUND) return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/subscription/" + subscription.getId());
        return Response.created(uri).build();
    }
}
