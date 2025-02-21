package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRessources {
    LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).
                entity(help.getLogements()).
                build();
    }

    @GET
    @Path("/getByReference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference){
        Logement logement = help.getLogementsByReference(reference);
        if(logement == null){
            return Response
                    .status(200)
                    .entity(logement)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity(logement)
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        if (logement == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid logement data.")
                    .build();
        }

        boolean added = help.addLogement(logement);
        if (added) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("Logement added successfully.")
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add logement.")
                    .build();
        }
    }


    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Logement with reference " + reference + " deleted successfully.")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Logement with reference " + reference + " not found.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Logement with reference " + reference + " updated successfully.")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Logement with reference " + reference + " not found.")
                    .build();
        }
    }
}






