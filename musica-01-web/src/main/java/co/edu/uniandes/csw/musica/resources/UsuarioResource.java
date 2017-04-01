package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.BoletaDTO;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.musica.dtos.UsuarioDTO;
import co.edu.uniandes.csw.musica.ejbs.BoletaLogic;
import co.edu.uniandes.csw.musica.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioLogic usuarioLogic;
    @Inject
    private BoletaLogic boletaLogic;

    /**
     *
     * @return Colección de objetos de UsuarioDTO
     * @generated
     */
    @GET
    public List<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        List<UsuarioEntity> usuarios = usuarioLogic.getUsuarios();
        for (UsuarioEntity usuario : usuarios) {
            UsuarioDTO dto = new UsuarioDTO(usuario);
            usuariosDTO.add(dto);
        }
        return usuariosDTO;
    }

    /**
     * Obtiene el Usuario con el Id dado por ruta
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id") Long id) 
    {
        UsuarioDTO u = new UsuarioDTO(usuarioLogic.getUsuario(id));
        if (u == null) throw new NotFoundException();
        return u;
    }
    

    //POST /usuarios/{idUsuario}/boletas

    /**
     * Agrega al cliente con el id dado, la boleta con el id pasado por
     * parametro
     *
     * @param id, id del cliente al que se le desea asociar la boleta
     * @param nueva, id de la boleta que se le desea asociar al cliente
     * @return
     */
    @POST
    @Path("{id: \\d+}/boletas/{idBoleta}")
    public BoletaDTO addBoletaUsuario(@PathParam("id") Long id, @PathParam("idBoleta") Long nueva) throws BusinessLogicException {
        UsuarioEntity entity = new UsuarioDTO(usuarioLogic.getUsuario(id)).toEntity();
        BoletaEntity boleta = new BoletaDTO(boletaLogic.getBoleta(nueva)).toEntity();
        //BoletaEntity stored = boletaLogic
        entity.getBoletas().add(boleta);
        usuarioLogic.updateUsuario(entity);
        return new BoletaDTO(boleta);
    }

    /**
     * Metodo que agrega un usuario dado por parametro
     *
     * @param usuarioDTO, usuario que desea ser agregado
     * @return el UsuarioDTO que fue creado y guardado en la base de datos
     * @throws BusinessLogicException
     */
    @POST
    public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) throws BusinessLogicException {
        UsuarioEntity usuario = usuarioDTO.toEntity();
        UsuarioEntity storedUser = usuarioLogic.createUsuario(usuario);
        return new UsuarioDTO(storedUser);
    }

    /*
  *Actualiza el usuario con el id dado por parametro
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO usuario) throws BusinessLogicException {
        UsuarioEntity entity = usuario.toEntity();
        entity.setId(id);
        return new UsuarioDTO(usuarioLogic.updateUsuario(entity));
    }

    /*
  *Elimina el usuario con el Id dado por parametro en la ruta
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        usuarioLogic.deleteUsuario(id);
    }

    /**
     * Metodo que borra una boleta de la listas de boletas de un usuario
     *
     * @param id
     * @param idBoleta
     */
    @DELETE
    @Path("{id:\\d+}/boletas/{idBoleta}")
    public void deleteBoletaUsuario(@PathParam("id") Long id, @PathParam("idBoleta") Long idBoleta) throws BusinessLogicException {
        UsuarioEntity uEntity = new UsuarioDTO(usuarioLogic.getUsuario(id)).toEntity();
        ArrayList<BoletaEntity> boletas = (ArrayList<BoletaEntity>) uEntity.getBoletas();
        boolean encontro = false;
        for (int i = 0; i < boletas.size(); i++) {
            BoletaEntity boleta = boletas.get(i);
            if (boleta.getId() == idBoleta) {
                uEntity.getBoletas().remove(i);
                encontro = true;
                break;
            }
        }
        if (!encontro) {
            throw new BusinessLogicException("El usuario no tiene registro de la boleta que se desea eliminar de la lista de boletas");
        }
        usuarioLogic.updateUsuario(uEntity);
    }
}
