package es.nemes.controllers;

import es.nemes.models.*;
import es.nemes.repositories.EventDAO;
import es.nemes.repositories.SubscriptionDAO;
import es.nemes.repositories.UserDAO;
import es.nemes.repositories.ZoneDAO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/subscription")
public class SubscriptionController {
    @Inject
    SubscriptionDAO dao;
    @Inject
    UserDAO userDAO;
    @Inject
    ZoneDAO zoneDAO;
    @Inject
    EventDAO eventDAO;

    @Inject
    JsonWebToken jwt;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    @Path("/create")
    public Response createSubscription(SubscriptionQuery subscriptionQuery) throws URISyntaxException {
        String userEmail = jwt.getClaim("upn");
        NUser user = userDAO.findByEmail(userEmail);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        BigDecimal bigDecimalLat = new BigDecimal(subscriptionQuery.getCenterLat());
        BigDecimal bigDecimalLon = new BigDecimal(subscriptionQuery.getCenterLon());

        Zone zone = zoneDAO.findByCenter(bigDecimalLat, bigDecimalLon);
        if (zone == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<String> queryEventsName = subscriptionQuery.getEventsName();
        List<String> queryEventsSeverity = subscriptionQuery.getEventsSeverity();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < queryEventsName.size(); i++) {
            String eventName = queryEventsName.get(i);
            String eventSeverity = queryEventsSeverity.get(i);

            Event eventFound = eventDAO.findById(eventName, eventSeverity);

            events.add(eventFound);
        }

        /*Event eventMock = new Event();
        eventMock.setEventName("Falso");
        eventMock.setSeverity("Moderada");
        ArrayList<Event> arryMock = new ArrayList<Event>();
        arryMock.add(eventMock);*/
        Subscription subscription = new Subscription(
                user,
                zone,
                events);

        Subscription response = dao.create(subscription);
        if (response == Subscription.NOT_FOUND) return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/subscription/" + subscription.getId());
        //System.out.println(subscription);
        return Response.created(uri).build();
    }
}
