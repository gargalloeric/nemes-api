package es.nemes.controllers;

import es.nemes.repositories.ZoneDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zone")
public class ZoneController {
    @Inject
    ZoneDAO dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZones() {
        return Response.ok(dao.getZones()).build();
    }
}
