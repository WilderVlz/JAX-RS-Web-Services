package mx.com.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class PersistirObjetoJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String [] args){
        //este lo necesitaremos para crear posteriormente nuestro EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        //con este crearemos posteriormente las transacciones y cambiaremos de estado el objeto
        EntityManager em = emf.createEntityManager();
        //aqui creamos la transaccion utilizando el objeto anterior
        EntityTransaction tx = em.getTransaction();
        
        //creacion de objeto
        Persona persona = new Persona(
                        "Will", 
                        "Velez", 
                        "wilderVelez@gmail.com", 
                        "3245434312");
        
        //inicia transacccion
        tx.begin();
        
        //persistimos objeto
        em.persist(persona);
        
        log.debug("Objeto persistido- estado detached" + persona);
        
        //terminamos trasaccion
        tx.commit();
        
        //estado detached
        log.debug("Objeto persistido- estado detached" + persona);
        
        //cerramos flujo
        em.close();
    }
}
