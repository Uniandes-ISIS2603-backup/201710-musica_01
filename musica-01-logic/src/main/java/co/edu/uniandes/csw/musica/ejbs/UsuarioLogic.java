/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que modela la logica de la clase usuario que puede ser cliente o administrador del
 * sistema de conciertos.
 */
@Stateless
public class UsuarioLogic {

    /**
     * Persistencia del usuario.
     */
    @Inject
    private UsuarioPersistence persistence;
    
    /**
     * Método que crea un UsuarioLogic.
     * @param user UsuarioEntity para crear el UsuarioLogic.
     * @return UaurioEntity creado.
     */
    public UsuarioEntity createUsuario(UsuarioEntity user)
    {
        return persistence.create(user);
    }
    
    /**
     * Método que retorna todos los usuarios en la persistencia.
     * @return Lista de usuarios.
     */
    public List<UsuarioEntity> getUsuarios ()
    {
        return persistence.findAll();
    }
    
    /**
     * Método que retorna el usuario con un id especifico.
     * @param id Id del usuario buscado.
     * @return Usuario buscado o null en caso de que no exista.
     */
    public UsuarioEntity getUsuario (Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Método para actualizar los datos de un usuario.
     * @param entity Usuario que se quiere actualizar.
     * @return Usuario actualizado.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no exista
     */
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException 
    {
        validarUsuario(entity);
        return persistence.update(entity);
    }
    
    /**
     * Método para eliminar un usuario.
     * @param id Id del usuario que se desea eliminar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no exista
     */
    public void deleteUsuario (Long id) throws BusinessLogicException
    {
        validarId(id);
        persistence.delete(id);
    }
    
    /**
     * Método para validar que un id corresponde a un usuario.
     * @param id Id que se quiere validar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el id
     * no tenga un usuario asosciado.
     */
    public void validarId( Long id) throws BusinessLogicException
    {
        UsuarioEntity entity = persistence.find(id);
        if (entity == null)
        {
            throw new BusinessLogicException ("El id debe ser válido.");
        }
    }
    
    /**
     * Método para validar que un usuario esta construido correctamente.
     * @param usuario Usuario que se quiere validar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no este correctamente construido.
     */
    public void validarUsuario (UsuarioEntity usuario) throws BusinessLogicException
    {
        if(usuario.getNombre() == null || usuario.getNombre().equals(""))
        {
            throw new BusinessLogicException("El usuario debe tener un nombre");
        }
    }
    
}


