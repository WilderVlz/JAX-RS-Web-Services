package mx.com.gm.sga.service;

import java.util.List;
import javax.ejb.Remote;
import mx.com.gm.sga.domain.Persona;

@Remote
public interface PersonaServiceRemote {
    
    List<Persona> personList();
    
    Persona findPersonById(Persona persona);
    
    Persona findPersonByEmail(Persona persona);
    
    void createPerson(Persona person);
    
    void modifyPerson(Persona person);
    
    void deletePerson(Persona person);
    
}
