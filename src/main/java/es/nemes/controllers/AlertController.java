package es.nemes.controllers;

import es.nemes.models.Catastrophe;
import es.nemes.models.NEmail;
import es.nemes.models.Subscription;
import es.nemes.repositories.CatastropheDAO;
import es.nemes.repositories.SubscriptionDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/alerts")
public class AlertController {
    @Inject
    SubscriptionDAO subscriptionDAO;
    @Inject
    CatastropheDAO catastropheDAO;
    @Inject
    MailerController mailer;

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
        sendMessagesToUsers(alertMap);

        return Response.ok().build();
    }

    private void sendMessagesToUsers(Map<String, List<Catastrophe>> alertMap) {
        List<NEmail> alertsToSend = new ArrayList<>();
        for (Map.Entry<String, List<Catastrophe>> entry : alertMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            alertsToSend.add(new NEmail(entry.getKey(), "Alerta", entry.getValue().toString()));
        }
        mailer.send(alertsToSend);
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
                if (subscription.getEvents().contains(catastrophe.getEvent().getEventName()) && subscription.getZone().equals(catastrophe.getZone())) {
                    String userEmail = subscription.getUser().getEmail();
//                    System.out.println("User email: " + userEmail);

                    if (alertMap.containsKey(userEmail)) {
                        alertMap.get(userEmail).add(catastrophe);
                    } else {
                        List<Catastrophe> catastropheList = new ArrayList<>();
//                        System.out.println("Añadiendo catástrofe: " + catastrophe);
                        catastropheList.add(catastrophe);
                        alertMap.put(userEmail, catastropheList);
                    }
                }
            }
        }

        return alertMap;
    }

}
