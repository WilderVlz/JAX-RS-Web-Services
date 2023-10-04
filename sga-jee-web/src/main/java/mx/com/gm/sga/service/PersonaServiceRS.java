package mx.com.gm.sga.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import mx.com.gm.sga.domain.Persona;

@Path("/personas")
@Stateless
public class PersonaServiceRS {

    @Inject
    private PersonService personaService;

    @GET
    /*Al ser varios valores es necesario utilizar los corchetes si es un valor unico
    utilizariamos solo los parentesis
     */
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Persona> listarPersonas() {
        return personaService.personList();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}") //hace referencia a /personas/{id}
    public Persona encontrarPersonaPorId(@PathParam("id") int id) {
        /*aca es algo brillante esto, el metodo recibe un objeto de tipo persona
        y nosotros solo recibimos un numero entero como parametro entonces
        creamos un nuevo objeto de tipo persona y le pasamos como parametro
        el numero entero que seria el Id 
         */
        return personaService.findPersonById(new Persona(id));
    }

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response agregarPersona(Persona persona) {
        personaService.createPerson(persona);
        try {
            return Response.ok().entity(persona).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            //build se utiliza para construir la respuesta en este caso
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada) {

        try {

            Persona persona = personaService.findPersonById(new Persona(id));

            if (persona != null) {
                personaService.modifyPerson(personaModificada);
                return Response.ok().entity(personaModificada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarPersonaPorId(@PathParam("id") int id) {
        personaService.deletePerson(new Persona(id));

        try {
            return Response.ok().status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
