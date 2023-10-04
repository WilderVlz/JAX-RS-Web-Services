package mx.com.gm.sga.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;

//nombramos el bean
@Named("personaBean")
//definimos el alcance (scope) del bean
@RequestScoped
public class PersonaBean {
    
    Logger log = LogManager.getRootLogger();
    
    //injectamos las dependencias de servicio
    @Inject
    private PersonService personaService;
    private Persona personaSeleccionada;
    private List<Persona> personas;
    
    public PersonaBean(){
    log.debug("Iniciando el objeto de persona bean");
    }
    /*esta anotacion nos sirve para que cuando se haya creado un objeto se pueda inicializar
    de alguna manera 
    */
    @PostConstruct
    public void inicializar(){
        //inicializamos las variables 
        
        personas = personaService.personList();
        log.debug("Personas recuperadas en managed bean: " + personas);
        personaSeleccionada = new Persona();
    }
    
    public void editListener(RowEditEvent event) {
        
        Persona persona = (Persona) event.getObject();
        personaService.modifyPerson(persona);
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
    public void agregarPersona(){
        this.personaService.createPerson(personaSeleccionada);
        this.personas.add(personaSeleccionada);
        this.personaSeleccionada = null;
    }
    
    public void eliminarPersona(){
        this.personaService.deletePerson(personaSeleccionada);
        this.personas.remove(this.personaSeleccionada);
        this.personaSeleccionada = null;
    }
    
    public void reiniciarPersonaSeleccionada(){
        
        this.personaSeleccionada = new Persona();
    }
}