package mx.com.gm.sga.cliente.criteria;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class PruebaApiCriteria {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String [] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        
        EntityManager em = emf.createEntityManager();
        //creamos objeto de el api de criteria utilizando el objeto em
        CriteriaBuilder cb = null;
        //trabajará con objetos persona
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;
        
        //query utilizando el api criteria
        //1. consulta de todas las personas
        //paso 1. objeto em crea instancia de criteriaBuilder
        cb = em.getCriteriaBuilder();
        
        //paso 2 creamos objeto criteria query con el objeto cb
        //especificamos el tipo de clase que vamos a utilizar
        criteriaQuery = cb.createQuery(Persona.class);
        
        //paso 3 creamos el objeto raiz del query con el objeto criteria query
        
        fromPersona = criteriaQuery.from(Persona.class);
        
        //paso 4 seleccionamos lo necesario del from
        criteriaQuery.select(fromPersona);
        
        //paso 5 creamos el tipo de query utilizando el objeto entity manager
        query = em.createQuery(criteriaQuery);
        
        //paso 6 Ejecutamos la consulta y almacenamos el response
        personas = query.getResultList();
        //mostrarPersonas(personas);
        
        //2-a  Consulta de la persona con id = 1 
        
        //jpql = select p from Persona p where p.IdPersona = 1
        
        log.debug("\n2-a Consulta de la persona con id = 1");
        
        cb = em.getCriteriaBuilder();
        
        criteriaQuery = cb.createQuery(Persona.class);
        
        fromPersona =criteriaQuery.from(Persona.class);
        
        /*ui estamos haciendo un filtro con "where" y por parametros estamos pasando,
        el campo a utilizar osea el id y luego el valor
        */
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"), 1));
        
        persona = em.createQuery(criteriaQuery).getSingleResult();
        
        log.debug(persona);
        
        // 2-b Consutla de la persona con id = 1
        log.debug("\n2-b Consulta de la persona con id = 1");
        
        cb = em.getCriteriaBuilder();
        
        criteriaQuery = cb.createQuery(Persona.class);
        
        fromPersona = criteriaQuery.from(Persona.class);
        
        criteriaQuery.select(fromPersona);
        
        //Predicate nos permite agregar varios criterios dinamicamente
        
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //Verificamos si tenemos criterios que agregar 
        Integer idPersonaParam = 1;
        //aqui agregamos el idPersona como parametro en la variable parameter
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idPersona");
        //luego la agregaremos como criterio
        criterios.add(cb.equal(fromPersona.get("idPersona"), parameter));
        
        // se agregan los criterios
        
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin criterios");
        } else if(criterios.size() == 1){
            
            /* aqui estamos indicando que solo traeremos o filtraremos por el criterio
            en la posciocion numero 0
            */
            criteriaQuery.where(criterios.get(0));
            
        } else {
            
            //esto lo utilizamos cuando tengamos varios criterios en nuestra lista 
            //con and estamos concatenando los criterios al where 
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
            
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPersona", idPersonaParam);
        
        //ejecutamos el query
        
        persona = query.getSingleResult();
        
        log.debug(persona);
        
    }
    
    public static void mostrarPersonas(List<Persona> personas){
        
        for(Persona persona: personas){
            
            log.debug(persona);
            
        }
        
    }
    
}