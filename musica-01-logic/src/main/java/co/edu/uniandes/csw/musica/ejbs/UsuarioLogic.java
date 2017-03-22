// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Pablo Alavarado
 * 
 */
@Stateless
public class UsuarioLogic {

    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity user)throws BusinessLogicException
    {
        if (user.getNombre() == null)
            throw new BusinessLogicException ("El usuario debe tener nombre.");
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
    
    // TODO: revisar validaciones al momento de actualizar
    public UsuarioEntity updateUsuario(UsuarioEntity entity) 
    {
        return persistence.update(entity);
    }
    
    // TODO: revisar validaciones al momento de eliminar
    public void deleteUsuario (Long id)
    {
        persistence.delete(id);
    }
    
}


