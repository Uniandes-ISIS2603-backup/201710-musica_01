/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.ejbs;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.persistence.BoletaPersistence;
import co.edu.uniandes.csw.musica.persistence.FestivalPersistence;
import co.edu.uniandes.csw.musica.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que modela la logica de la clase usuario que puede ser cliente o
 * administrador del sistema de conciertos.
 */
@Stateless
public class UsuarioLogic {

    /**
     * Persistencia de usuarios.
     */
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    /**
     * Persistencia de boletas.
     */
    @Inject
    private BoletaPersistence boletaPersistence;
    
    /**
     * Persistencia de festivales.
     */
    @Inject
    private FestivalPersistence festivalPersistence;

    public UsuarioLogic() {

    }

    /**
     * Método que crea un UsuarioLogic.
     *
     * @param user UsuarioEntity para crear el UsuarioLogic.
     * @return UaurioEntity creado.
     */
    public UsuarioEntity createUsuario(UsuarioEntity user) throws BusinessLogicException {
        validarUsuario(user);
        return usuarioPersistence.create(user);
    }

    /**
     * Método que retorna todos los usuarios en la persistencia.
     *
     * @return Lista de usuarios.
     */
    public List<UsuarioEntity> getUsuarios() {
        return usuarioPersistence.findAll();
    }

    /**
     * Método que retorna el usuario con un id especifico.
     *
     * @param id Id del usuario buscado.
     * @return Usuario buscado o null en caso de que no exista.
     */
    public UsuarioEntity getUsuario(Long id) {
        return usuarioPersistence.find(id);
    }

    /**
     * Método para actualizar los datos de un usuario.
     *
     * @param entity Usuario que se quiere actualizar.
     * @return Usuario actualizado.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no exista
     */
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException {
        validarUsuario(entity);
        return usuarioPersistence.update(entity);
    }

    /**
     * Método para eliminar un usuario.
     *
     * @param id Id del usuario que se desea eliminar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no exista
     */
    public void deleteUsuario(Long id) throws BusinessLogicException {
        validarId(id);
        usuarioPersistence.delete(id);
    }

    /**
     * Método para validar que un id corresponde a un usuario.
     *
     * @param id Id que se quiere validar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el id no
     * tenga un usuario asosciado.
     */
    public void validarId(Long id) throws BusinessLogicException {
        UsuarioEntity entity = usuarioPersistence.find(id);
        if (entity == null) {
            throw new BusinessLogicException("El id debe ser válido.");
        }
    }

    /**
     * Método para validar que un usuario esta construido correctamente.
     *
     * @param usuario Usuario que se quiere validar.
     * @throws BusinessLogicException Lanza excepcion en caso de que el usuario
     * no este correctamente construido.
     */
    public void validarUsuario(UsuarioEntity usuario) throws BusinessLogicException {
        if (usuario.getNombre() == null || usuario.getNombre().equals("")) {
            throw new BusinessLogicException("El usuario debe tener un nombre.");
        }
        if (usuario.getClave() == null || usuario.getClave().equals("")) {
            throw new BusinessLogicException("El usuario debe tener una clave.");
        }
        if (!usuario.isAdmin() && !usuario.getFestivales().isEmpty()) {
            throw new BusinessLogicException("Un usuario que no es admin no "
                    + "puede manejar festivales.");
        }
        for (BoletaEntity boleta : usuario.getBoletas()) {
            BoletaEntity result = boletaPersistence.find(boleta.getId());
            if (result == null) {
                throw new BusinessLogicException("Las boletas del usuario deben "
                        + "existir.");
            }
        }
        for (FestivalEntity festival : usuario.getFestivales()) {
            FestivalEntity result = festivalPersistence.find(festival.getId());
            if (result == null) {
                throw new BusinessLogicException("Los festivales del usuario "
                        + "deben existir.");
            }
        }
    }
}