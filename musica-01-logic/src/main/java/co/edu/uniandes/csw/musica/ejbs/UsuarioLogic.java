package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioLogic {

    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity user)throws BusinessLogicException
    {
        validarUsuario(user);
        return persistence.create(user);
    }
    
    public List<UsuarioEntity> getUsuarios ()
    {
        return persistence.findAll();
    }
    
    public UsuarioEntity getUsuario (Long id)
    {
        return persistence.find(id);
    }
    
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException 
    {
        validarUsuario(entity);
        return persistence.update(entity);
    }
    
    public void deleteUsuario (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }
    
    public void validarId( Long id) throws BusinessLogicException
    {
        UsuarioEntity entity = persistence.find(id);
        if (entity == null)
         throw new BusinessLogicException ("El id debe ser válido.");
    }
    
    public void validarUsuario (UsuarioEntity usuario) throws BusinessLogicException
    {
        if(usuario.getNombre() == null || usuario.getNombre().equals(""))
            throw new BusinessLogicException("El usuario debe tener un nombre");
    }
    
}


