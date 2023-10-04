package mx.com.gm.sga.cliente.asociaciones;

import java.util.List;
import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;
import org.apache.logging.log4j.*;

public class ClienteAsociacionesJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        
        EntityManager em = emf.createEntityManager();
        
        /*aqui estamos utilizando un namedQuery definido en la entidad persona
        para hacer un select en este caso, el metodo getResultList nos devuleve
        una lista de objetos
        */
        List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        //cerramos conexion
        em.close();
        
        //iteramos la lista de objetos obtenidos
        for(Persona persona: personas){
            
            log.debug("Persona: " + persona);
            
            //aqui iteramos la coleccion de usuarios relacionados a cada persona
            for(Usuario usuario: persona.getUsuarioList())
                //aqui mandamos a imprimir los objetos
                log.debug("Usuario: " + usuario);
        }
    }
}