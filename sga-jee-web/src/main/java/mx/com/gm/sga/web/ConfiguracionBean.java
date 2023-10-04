package mx.com.gm.sga.web;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;
//configuramos la version a utilizar
//esto no será necesario en versiones posteriores
@FacesConfig(
version=JSF_2_3
)
//con esto nos aseguramos que funcionara durante toda la ejecucion de la app
@ApplicationScoped
public class ConfiguracionBean {
    
}
