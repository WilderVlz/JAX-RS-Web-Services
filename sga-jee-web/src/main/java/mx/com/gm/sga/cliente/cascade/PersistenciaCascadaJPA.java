package mx.com.gm.sga.cliente.cascade;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;
import org.apache.logging.log4j.*;

public class PersistenciaCascadaJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        
        //estado transient
        Persona persona = new Persona(
        "Pacho",
        "Ramirez",
        "Pramirez@gmail.com",
        "231123454");
        
        /*aqui estamos creando la relacion entre objetos, pasandole por parametro
        el objeto de tipo persona a la hora de crear el nuevo usuario
        */
        Usuario usuario = new Usuario(
        "pachito",
        "elbananero",
        persona);
        
        //estado managed
        em.persist(usuario);
        
        //terminamos transaccion
        tx.commit();
        
        //estado detached
        log.debug("persona: " + persona);
        log.debug("usuario: " + usuario);
        
        em.close();
    }
    
}
