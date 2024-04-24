package es.nemes.repositories;

import es.nemes.models.Subscription;

import java.util.Collection;

public interface SubscriptionDAO {
    Subscription create(Subscription subscription);
    Collection<Subscription> getSubscriptions();
}
