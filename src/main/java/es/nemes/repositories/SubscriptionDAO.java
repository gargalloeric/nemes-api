package es.nemes.repositories;

import es.nemes.models.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    Subscription create(Subscription subscription);
    List<Subscription> getSubscriptions();
}
