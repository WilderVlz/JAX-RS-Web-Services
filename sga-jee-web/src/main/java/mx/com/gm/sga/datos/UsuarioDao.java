package mx.com.gm.sga.datos;

import java.util.List;
import mx.com.gm.sga.domain.Usuario;

public interface UsuarioDao {

    List<Usuario> findAllUsuarios();

    Usuario findUsuarioById(Usuario usuario);

    Usuario findUsuarioByEmail(Usuario usuario);

    void insertUsuario(Usuario usuario);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(Usuario usuario);

}
