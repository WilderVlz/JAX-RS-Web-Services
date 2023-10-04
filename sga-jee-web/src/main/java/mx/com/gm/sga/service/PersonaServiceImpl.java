package mx.com.gm.sga.service;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.datos.PersonaDao;

//define this class as a EJB 
@Stateless
@WebService(endpointInterface = "mx.com.gm.sga.service.PersonaServiceWs")
public class PersonaServiceImpl implements PersonService, PersonaServiceWs {
    
    /*aqui utilizamos CDI(context & dependency injection) para injectar este objeto
    con esto ya tendremos acceso a la capa de datos
    */
    @Inject
    private PersonaDao personaDao;
    
    /* esta variable la instanciamos y le injectamos las dependencias para
    utilizarla luego cuando queramos hacer un rollback en un transaccion
    */
    @Resource
    private SessionContext contexto;

    @Override
    public List<Persona> personList() {
        
        return personaDao.findAllPersonas();
    }

    @Override
    public Persona findPersonById(Persona persona) {
        return personaDao.findPersonaById(persona);
    }

    @Override
    public Persona findPersonByEmail(Persona persona) {
        return personaDao.findPersonaByEmail(persona);
    }

    @Override
    public void createPerson(Persona persona) {
        personaDao.insertPersona(persona);
    }

    @Override
    public void modifyPerson(Persona persona) {
        try {
        personaDao.updatePersona(persona);
        } catch (Throwable t){
        contexto.setRollbackOnly();
        t.printStackTrace(System.out);
        }
    }

    @Override
    public void deletePerson(Persona persona) {
        personaDao.deletePersona(persona);
    }
}