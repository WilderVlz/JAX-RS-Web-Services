package mx.com.gm.sga.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import mx.com.gm.sga.domain.Persona;

@WebService
public interface PersonaServiceWs {

    @WebMethod
    public List<Persona> personList();

}
