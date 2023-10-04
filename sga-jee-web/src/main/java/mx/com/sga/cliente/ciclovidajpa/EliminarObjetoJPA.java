package mx.com.sga.cliente.ciclovidajpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EliminarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        //este lo necesitaremos para crear posteriormente nuestro EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        //con este crearemos posteriormente las transacciones y cambiaremos de estado el objeto
        EntityManager em = emf.createEntityManager();
        //aqui creamos la transaccion utilizando el objeto anterior
        EntityTransaction tx = em.getTransaction();

        //inicia transacccion
        tx.begin();

        //recuperamos objeto
        /*para que esto funcione es necesario agregar esta clase
        dentro de la configuracion del persistence.xml
         */
        Persona persona = em.find(Persona.class, 7);

        //terminamos trasaccion
        tx.commit();

        //estado detached
        log.debug("Objeto recuperado: " + persona);

        //abrir nueva transaccion
        tx.begin();
        
        //eliminamos el objeto encontrado y actulizamos la base de datos
        em.remove(em.merge(persona));
        
        //terminamos la trasaccion
        tx.commit();

        //estado detached
        log.debug("Objeto Eliminado en base de datos:" + persona);

        //cerramos flujo
        em.close();
    }
}
