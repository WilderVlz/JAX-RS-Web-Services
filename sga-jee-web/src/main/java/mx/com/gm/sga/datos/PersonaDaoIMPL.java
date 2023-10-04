package mx.com.gm.sga.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;

@Stateless
public class PersonaDaoIMPL implements PersonaDao {
    
    //inyectamos unidad de persistencia
    @PersistenceContext(unitName="SgaPU")
    EntityManager em;

    @Override
    public List<Persona> findAllPersonas() {
        /*aqui utilizaremos el namedQuery que definimos en nuestra clase entidad
            el cual nos devuelve objetos java ordenamos por id y no columnas aisladas
        */
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public Persona findPersonaById(Persona persona) {
        //aqui como primer parametro especificamos la clase en la que queremos buscar
        return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        //aqui creamos nuestra query la 'p' hace referencia al alias para los objetos
        Query query = em.createQuery("from Persona p where p.email =: email");
        //aqui definimos el parametro funciona como clave valor clave= 'email', valor=.. 
        query.setParameter("email", persona.getEmail());
        
        return (Persona) query.getSingleResult();
    }

    @Override
    public void insertPersona(Persona persona) {
        //con esto guardamos informacion o nuevos registros en la base de datos
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        /*este metodo se encarga de sincronizar cualquier modificacion que hayamos hecho
        hacia la base de datos 
        */
        em.merge(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        
        /*aqui antes de eliminar primero debemos hacer un merge osea que primero
        se actualiza el estado del objeto hacia la base de datos y una vez esta 
        sincronizado el objeto podemos removerlo 
        */ 
        em.remove(em.merge(persona));
    }
    
}
