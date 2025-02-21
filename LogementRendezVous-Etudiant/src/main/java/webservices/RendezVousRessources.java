package webservices;

import metiers.RendezVousBusiness;
import entities.RendezVous;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousRessources {
    RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        return Response
                .status(200)
                .entity(rendezVousBusiness.getListeRendezVous())
                .build();
    }

    @POST
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(@PathParam("reference") int reference, RendezVous rendezVous) {
        if (rendezVousBusiness.addRendezVous(rendezVous)) {
            return Response.status(200).entity("RendezVous added successfully.").build();
        } else {
            return Response.status(400).entity("Logement not found.").build();
        }
    }



    @GET
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogementReference(@PathParam("reference") int reference) {
        return Response.status(200).entity(rendezVousBusiness.getListeRendezVousByLogementReference(reference)).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousBusiness.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(200).entity(rendezVous).build();
        } else {
            return Response.status(404).entity("RendezVous not found.").build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        if (rendezVousBusiness.deleteRendezVous(id)) {
            return Response.status(200).entity("RendezVous deleted successfully.").build();
        } else {
            return Response.status(404).entity("RendezVous not found.").build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        if (rendezVousBusiness.updateRendezVous(id, updatedRendezVous)) {
            return Response.status(200).entity("RendezVous updated successfully.").build();
        } else {
            return Response.status(404).entity("RendezVous not found.").build();
        }
    }
}



