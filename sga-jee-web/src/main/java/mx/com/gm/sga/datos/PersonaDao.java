package mx.com.gm.sga.datos;

import java.util.List;
import mx.com.gm.sga.domain.Persona;

public interface PersonaDao {
    
    List<Persona> findAllPersonas();
    
    Persona findPersonaById(Persona persona);
    
    Persona findPersonaByEmail(Persona persona);
    
    void insertPersona(Persona persona);
    
    void updatePersona(Persona persona);
    
    void deletePersona(Persona persona);
    
}
