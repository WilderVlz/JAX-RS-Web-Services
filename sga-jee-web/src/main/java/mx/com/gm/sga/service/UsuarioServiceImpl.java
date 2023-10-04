package mx.com.gm.sga.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.com.gm.sga.datos.UsuarioDao;
import mx.com.gm.sga.domain.Usuario;

@Stateless
public class UsuarioServiceImpl implements UsuarioService, UsuarioServiceRemote {

    @Inject
    private UsuarioDao usuarioDao;
    
    @Override
    public List<Usuario> usuarioList() {
        return this.usuarioDao.findAllUsuarios();
    }

    @Override
    public Usuario findUsuarioById(Usuario usuario) {
        return this.usuarioDao.findUsuarioById(usuario);
    }

    @Override
    public Usuario findUsuarioByEmail(Usuario usuario) {
        return null;
    }

    @Override
    public void createUsuario(Usuario usuario) {
        this.usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modifyUsuario(Usuario usuario) {
        this.usuarioDao.updateUsuario(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioDao.deleteUsuario(usuario);
    }
}
    
