/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;import co.edu.uniandes.csw.musica.dtos.UsuarioDTO;
import co.edu.uniandes.csw.musica.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ana Lucy
 */
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject private UsuarioLogic usuarioLogic;
    
    /**
     * 
     * @return Colección de objetos de UsuarioDTO
     * @generated
     */
@GET
public List <UsuarioDTO> getUsuarios()
{
 List <UsuarioDTO> usuariosDTO = new ArrayList<>();
 List <UsuarioEntity> usuarios = usuarioLogic.getUsuarios();
 for(UsuarioEntity usuario : usuarios){
     UsuarioDTO dto= new UsuarioDTO(usuario);
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
  return new UsuarioDTO(usuarioLogic.getUsuario(id));
  }
  @POST
  public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO)throws BusinessLogicException{
  UsuarioEntity usuario = usuarioDTO.toEntity();
  UsuarioEntity storedUser= usuarioLogic.createUsuario(usuario);
  return new UsuarioDTO(storedUser);
}
  /*
  *Actualiza el usuario con el id dado por parametro
  */
  @PUT
  @Path("{id: \\d+}")
  public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO usuario){
      UsuarioEntity entity = usuario.toEntity();
      entity.setId(id);
      return new UsuarioDTO(usuarioLogic.updateMusico(entity));
  }
 /*
  *Elimina el usuario con el Id dado por parametro en la ruta
  */ 
  @DELETE
  @Path("{id: \\d+}")
  public void deleteUsuario(@PathParam("id") Long id){
  usuarioLogic.deleteUsuario(id);
  }
  
}