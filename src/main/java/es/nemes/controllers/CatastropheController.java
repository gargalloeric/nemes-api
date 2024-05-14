package es.nemes.controllers;

import es.nemes.models.FilterQuery;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filtered")
    public Response getFilteredCatastrophes(FilterQuery queryFilterCatastrophes) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate initialDate;
        try {
            initialDate = LocalDate.parse(queryFilterCatastrophes.getInitialDate(), dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("   > Not able to parse Date");
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format").build();
        }

        LocalDate finishDate;
        try {
            finishDate = LocalDate.parse(queryFilterCatastrophes.getFinishDate(), dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("   > Not able to parse Date");
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format").build();
        }

        return Response.ok(dao.getFilteredCatastrophes(initialDate, finishDate, queryFilterCatastrophes.getEvents())).build();
    }
}
