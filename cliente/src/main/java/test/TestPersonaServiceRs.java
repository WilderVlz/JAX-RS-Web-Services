package test;

import domain.Persona;
import java.util.List;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

public class TestPersonaServiceRs {

    private static final String URL_BASE = "http://localhost:8080/sga-jee-web/webservice";
    private static Client cliente;
    private static WebTarget webTarget;
    private static Persona persona;
    private static List<Persona> personas;
    private static Invocation.Builder invocationBuilder;
    private static Response response;

    public static void main(String[] args) {

        // inicializamos el cliente
        cliente = ClientBuilder.newClient();

        //Leer una persona
        webTarget = cliente.target(URL_BASE).path("/personas");

        //Proporcionamos un ID para encontrar una persona especifica
        persona = webTarget.path("/1").request(MediaType.APPLICATION_XML).get(Persona.class);
        System.out.println("Persona recuperada: " + persona);

        //Leer todas las personas
        //esto nos regresa el listado de personas
        personas = webTarget.request(MediaType.APPLICATION_XML).get(Response.class).readEntity(new GenericType<List<Persona>>() {
        });

        imprimirPersonas(personas);

        //Agregar una persona
        persona = new Persona(
                2,
                "Raul",
                "Zapato",
                "Rzapaty@gmail.com",
                "3212345643");

        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

        response = invocationBuilder.post(Entity.entity(persona, MediaType.APPLICATION_XML));

        System.out.println("");
        //esto nos devuelve el codigo de estado
        System.out.println(response.getStatus());

        //Recuperamos la persona recien agregada para luego modificarla y eliminarla
        Persona personaRecuperada = response.readEntity(Persona.class);
        System.out.println("Persona agregada: " + personaRecuperada);

        //modificar la persona agregada
        persona = personaRecuperada;
        persona.setApellido("zorongo");

        String pathId = "/" + persona.getIdPersona();

        //aqui hacemos la consulta al metodo put, esto lo basamos en el path (ruta)
        invocationBuilder = webTarget.path(pathId).request(MediaType.APPLICATION_XML);

        //aqui definimos la respuesta
        response = invocationBuilder.put(Entity.entity(persona, MediaType.APPLICATION_XML));

        System.out.println("");
        System.out.println("Response: " + response.getStatus());
        System.out.println("Persona modificada: " + response.readEntity(Persona.class));

        //eliminar persona
        Persona personaEliminar = personaRecuperada;

        String pathEliminarId = "/" + personaEliminar.getIdPersona();

        invocationBuilder = webTarget.path(pathEliminarId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();

        System.out.println("");
        System.out.println("response: " + response.getStatus());
        System.out.println("Persona Eliminada: " + personaEliminar);

    }

    public static void imprimirPersonas(List<Persona> personas) {

        System.out.println("Personas Recuperadas");

        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }

}
