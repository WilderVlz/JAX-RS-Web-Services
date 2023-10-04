package mx.com.gm.sga.cliente.jpql;

import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.*;
import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;

public class PruebaJPQL {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");

        EntityManager em = emf.createEntityManager();

        //Consulta de todas las personas
        log.debug("\n Consulta de todas las personas");

        personas = em.createQuery("select p from Persona p").getResultList();

        mostrarPersonas(personas);

        //Consulta de una persona por medio de ID
        log.debug("\n Consulta de una persona por medio de ID");

        persona = (Persona) em.createQuery("select p from Persona p where p.idPersona= 1").getSingleResult();

        log.debug(persona);

        //Consulta de una persona por medio de el nombre
        log.debug("\n Consulta de una persona por medio de el nombre");

        personas = em.createQuery("select p from Persona p where p.nombre='Wilder'").getResultList();

        mostrarPersonas(personas);

        //Consulta de datos individuales creando un arreglo(TUPLA) de tipo object de 3 columnas
        log.debug("\n Consulta de datos individuales");

        //renombramos el valor que nos va a devolver la consulta con 'as Nombre'
        iter = em.createQuery("select p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p ")
                .getResultList()
                .iterator();

        while (iter.hasNext()) {

            //aqui almacenamos todos los objetos de la consulta en el array
            tupla = (Object[]) iter.next();

            String nombre = (String) tupla[0];
            String apellido = (String) tupla[1];
            String email = (String) tupla[2];

            log.debug("Nombre: " + nombre + " Apellido: " + apellido + " email: " + email);
        }

        //Consulta de objeto persona y el ID
        log.debug("\nselect p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p");

        iter = em.createQuery("select p, p.idPersona from Persona p").getResultList().iterator();

        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();

            persona = (Persona) tupla[0];

            int id = (int) tupla[1];

            log.debug("Objeto: " + persona + " ID: " + id);
        }

        // Consulta de todas las personas
        //con esta sentencia estamos creando un objeto de tipo persona con el atributo de id
        log.debug("\n con esta sentencia estamos creando un objeto de tipo persona con el atributo de id");
        personas = em.createQuery("select new mx.com.gm.sga.domain.Persona( p.idPersona) from Persona p").getResultList();
        
        //Regresa el valor minimo y maximo del ID (scaler result)
        log.debug("\n Regresa el valor minimo y maximo del ID (scaler result)");
        
        iter = em.createQuery("select min(p.idPersona) as MindID, max (p.idPersona) as MaxID, count (p.idPersona) as Contador from Persona p").getResultList().iterator();
        
        while(iter.hasNext()){
            
            tupla = (Object []) iter.next();
            
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long count = (Long) tupla[2];
            
            log.debug("idMin: " + idMin + " idMax: " + idMax + " Count: " + count );
        }
        mostrarPersonas(personas);
        
        
        // Cuenta los nombres de las personas que son distintos
        log.debug("\n Cuenta los nombres de las personas que son distintos");
        
        Long contador = (Long) em.createQuery("select count(distinct p.nombre) from Persona p").getSingleResult();
        
        log.debug("Nombres distintos: " + contador);
        
        // Concatena y el nombre y el apellido
        
        log.debug("\n Concatena el nombre y el apellido");
        
        nombres = em.createQuery("select CONCAT(p.nombre, ' ', p.apellido) as Nombre from Persona p ").getResultList();
        
        for (String nombre: nombres){
            
            log.debug(nombre);
        }
        
        
        log.debug("\n Obtiene el objeto persona con id igual al parametro proporcionado");
        
        int id = 1;
        
        q = em.createQuery("select p from Persona p where p.idPersona = :id");
        
        q.setParameter("id", id);
        
        persona = (Persona) q.getSingleResult();
        
        log.debug(persona);
        
        // Obtiene las personas que contengan una letra A/a en el nombre
        
        log.debug("\n Obtiene las personas que contengan una letra A/a en el nombre");
        //debemos de ponerlo entre porcentajes debido a que estamos usando 'like' 
        //con % indicamos que puede estar tanto al inicio como al final la palabra a obtener
        String parametro = "%a%";
        q = em.createQuery("select p from Persona p where upper(p.nombre) like upper (:parametro)");
        q.setParameter("parametro", parametro);
        
        personas = q.getResultList();
        
        mostrarPersonas(personas);
        
        //uso de between
        
        log.debug("\n uso de between");
        
        int param = 1;
        int param1 = 5;
        
        q = em.createQuery("select p from Persona p where p.idPersona between :param and :param2");
        q.setParameter("param", param);
        q.setParameter("param2", param1);
        
        personas = q.getResultList();
        
        mostrarPersonas(personas);
        
        // Uso del ordenamiento 
        log.debug("\n Uso del ordenamiento");
        //desc hace referencia a descendente
        personas = em.createQuery("select p from Persona p where p.idPersona > 0 order by p.nombre desc, p.apellido desc").getResultList();
        
        mostrarPersonas(personas);
        
        // Uso de subqueries
        
        //un sub query es un query dentro de otro query
        log.debug("\n Uso de subqueries");
        
        //el subquery lo definimos dentro de ()
        personas = em.createQuery("select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)").getResultList();        
        mostrarPersonas(personas);
        
        // uso de join con lazy loading
        log.debug("\n uso de join con lazy loading");
        
        usuarios = em.createQuery("select u from Usuario u join u.idPersona p").getResultList();
        
        mostrarUsuarios(usuarios);
        
        // Uso de left join con eager con eager loading
        
        log.debug("\n Uso de left join con eager con eager loading");
        
        //con la palabra fetch se aplica el concepto de eager
        
        usuarios = em.createQuery("select u from Usuario u left join fetch u.idPersona").getResultList();
        
        mostrarUsuarios(usuarios);
        
        
        
        
    }

    private static void mostrarPersonas(List<Persona> personas) {

        for (Persona persona : personas) {

            log.debug(persona);

        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {
        
        for(Usuario usuario: usuarios){
            
            log.debug(usuario);
        }
    }
}