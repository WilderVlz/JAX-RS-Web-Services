package mx.com.gm.sga.service;

import java.util.List;
import javax.ejb.Local;
import mx.com.gm.sga.domain.Persona;

// here we define it as a local interface

@Local
public interface PersonService {
    
    List<Persona> personList();
    
    Persona findPersonById(Persona persona);
    
    Persona findPersonByEmail(Persona persona);
    
    void createPerson(Persona person);
    
    void modifyPerson(Persona person);
    
    void deletePerson(Persona person);
    
}
