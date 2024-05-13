package es.nemes.controllers;

import es.nemes.models.Coordinate;
import es.nemes.models.Zone;
import es.nemes.repositories.ZoneDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.*;


@Path("/zone")
public class ZoneController {
    @Inject
    ZoneDAO dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZones() {
        Collection<Zone> zones = dao.getZones();
        for (Zone zone : zones) {
            sortCoordinatesById(zone.getPolygons());
        }
        return Response.ok(zones).build();
    }

    public static void sortCoordinatesById(List<Coordinate> polygon) {
        // Define a custom comparator to compare Coordinates by their id
        Comparator<Coordinate> idComparator = Comparator.comparingLong(Coordinate::getId);
        // Sort the polygon list using the custom comparator
        polygon.sort(idComparator);
    }
}
