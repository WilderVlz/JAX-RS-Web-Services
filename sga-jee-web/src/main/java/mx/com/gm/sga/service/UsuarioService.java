package mx.com.gm.sga.service;

import java.util.List;
import javax.ejb.Local;
import mx.com.gm.sga.domain.Usuario;

@Local
public interface UsuarioService {

    List<Usuario> usuarioList();

    Usuario findUsuarioById(Usuario usuario);

    Usuario findUsuarioByEmail(Usuario usuario);

    void createUsuario(Usuario usuario);

    void modifyUsuario(Usuario usuario);

    void deleteUsuario(Usuario usuario);

}
