package es.nemes.controllers;

import es.nemes.models.Catastrophe;
import es.nemes.models.Subscription;
import es.nemes.repositories.CatastropheDAO;
import es.nemes.repositories.SubscriptionDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/alerts")
public class AlertController {
    @Inject
    SubscriptionDAO subscriptionDAO;
    @Inject
    CatastropheDAO catastropheDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sendalerts")
    public Response alertUsersOfActiveCatastrophes() {
        // fetch all active catastrophes
        System.out.println("{alertcontroller} Fetching catastrophes");
        List<Catastrophe> catastrophes = fetchAllCatastrophesFromDb();

        // fetch all users subscriptions
        List<Subscription> subscriptions = fetchAllSubscriptionsFromDb();
        System.out.println("lista subscripciones: " +  subscriptions);

        // comparison between subscriptions and catastrophes
        Map<String, List<Catastrophe>> alertMap = compareSubscriptionsAndCatastrophes(subscriptions, catastrophes);
        // send messages
        // TODO: connect mailing service

        return Response.ok().build();
    }

    private List<Catastrophe> fetchAllCatastrophesFromDb() {
        return catastropheDAO.getCatastrophes();
    }

    private List<Subscription> fetchAllSubscriptionsFromDb() {
        return subscriptionDAO.getSubscriptions();
    }

    private Map<String, List<Catastrophe>> compareSubscriptionsAndCatastrophes
            (List<Subscription> subscriptions, List<Catastrophe> catastrophes) {
        Map<String, List<Catastrophe>> alertMap = new HashMap<>();

        for (Subscription subscription : subscriptions) {
            for (Catastrophe catastrophe : catastrophes) {
                if (subscription.getEvents().contains(catastrophe.getEvent()) && subscription.getZone().equals(catastrophe.getZone())) {
                    String userEmail = subscription.getUser().getEmail();
                    System.out.println("User email: " + userEmail);

                    if (alertMap.containsKey(userEmail)) {
                        alertMap.get(userEmail).add(catastrophe);
                    } else {
                        List<Catastrophe> catastropheList = new ArrayList<>();
                        System.out.println("Añadiendo catástrofe: " + catastrophe);
                        catastropheList.add(catastrophe);
                        alertMap.put(userEmail, catastropheList);
                    }
                }
            }
        }

        return alertMap;
    }
}
