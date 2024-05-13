package es.nemes.controllers;

import es.nemes.models.NEmail;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class MailerController {
    @Inject
    ReactiveMailer reactiveMailer;

    public MailerController() {
    }

    public void send(List<NEmail> emails) {
        emails.stream()
                .map(email -> Mail.withText(email.getTo(), email.getSubject(), email.getMessage()))
                .forEach(email -> reactiveMailer.send(email).subscribe().with(
                        success -> System.out.println("Message sent successfully"),
                        error -> System.err.println("Something went wrong: " + error.getMessage())
                ));
    }
}
