/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.FestivalDTO;
import co.edu.uniandes.csw.musica.dtos.GeneroDTO;
import co.edu.uniandes.csw.musica.dtos.UsuarioDTO;
import co.edu.uniandes.csw.musica.ejbs.FestivalLogic;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
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
 * @author pa.alvarado10
 */
@Path("/festivales")
@Consumes (MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FestivalResource 
{
@Inject private FestivalLogic festivalLogic;
@Inject private GeneroLogic generoLogic;
@Inject private FuncionLogic funcionLogic;
@Inject private UsuarioLogic usuarioLogic;
/**
 * Obtiene la lista de los registros de Festival
 * 
 * @return Coleccion de objetos FestivalDTO
 */
@GET
public List <FestivalDTO> getFestivales(){
List <FestivalDTO> festivalesDTOs = new ArrayList<>();
List <FestivalEntity> entity = festivalLogic.getFestivales();
for(FestivalEntity festival : entity){
FestivalDTO dto= new FestivalDTO(festival);
festivalesDTOs.add(dto);
}
return festivalesDTOs;
}
/**
 * Obtiene el festival con el id dado
 * @return festivalDTO, si existe uno con el id dado por parametro
 */
@GET
@Path ("{id:\\d+}")
public FestivalDTO getFestival(@PathParam("id") Long id)
{
return new FestivalDTO(festivalLogic.getFestival(id));
}

/**
 * 
 * @param dto cuerpo del festival que se desea agregar
 * @return retorna el festivalDTO agregado
 * @throws BusinessLogicException 
 */
@POST
public FestivalDTO addFestival(FestivalDTO dto) throws BusinessLogicException{
FestivalEntity festival = dto.toEntity();
FestivalEntity stored = festivalLogic.createFestival(festival);
return new FestivalDTO(stored);
}
/**
 * Metodo que agrega un genero, lo agrega a la lista de generos de un festival dado.
 * @param id, id del festial al cual se le desea agregar el genero (Debe existir)
 * @param dto, Cuerpo del generoDTO que se le desea agregar al festival dado 
 * @return el GeneroDTO ya creado en la base de datos 
 * @throws BusinessLogicException 
 */
@POST
@Path("{id :\\d+}/generos")
public GeneroDTO addGenero(@PathParam("id") Long id, GeneroDTO dto)throws BusinessLogicException{
    GeneroEntity entity = dto.toEntity();
    FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
    GeneroEntity stored = generoLogic.createGenero(entity);
    festival.getGeneros().add(stored);
    return new GeneroDTO(stored);
}
/**
 * Metodo que dado un id de un genero, lo agrega al festival con id dado por parametro
 * @param id, id del festival 
 * @param idGenero, id del genero que se desea agregar al festival
 * @return GeneroDTO del genero guardado en la lista de generos del festival
 * @throws BusinessLogicException 
 */
@POST
@Path("{id :\\d+}/generos/{idGenero:\\d+}")
public GeneroDTO addGenero(@PathParam("id") Long id,@PathParam("idGenero") Long idGenero)throws BusinessLogicException{
   
    GeneroEntity entity = new GeneroDTO(generoLogic.getGenero(idGenero)).toEntity();
    FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
    GeneroEntity stored = generoLogic.createGenero(entity);
    festival.getGeneros().add(stored);
    return new GeneroDTO(stored);
}
/**
 * Metodo que crea un usuario y lo agrega a la lista de administradores de un festival
 * @param id, id del festival al que se le desea agregar el administrador
 * @param dto, UsuarioDTO cuerpo del usuario que se desea crear 
 * @return el UsuarioDTO creado que fue agregado a la lista de administradores del festival dado
 * @throws BusinessLogicException 
 */
@POST
@Path("{id :\\d+}/usuarios")
public UsuarioDTO addUsuario(@PathParam("id") Long id, UsuarioDTO dto)throws BusinessLogicException{
UsuarioEntity uEntity = dto.toEntity();
FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
UsuarioEntity stored = usuarioLogic.createUsuario(uEntity);
festival.getAdmins().add(stored);
return new UsuarioDTO(stored);
}
/**
 * Metodo que agrega un Usuario con id dado, a la lista de administradores de un festival
 * @param id, id del festival al que se le desea agregar el administrador
 * @param idUsuario, id del usuario que se desea agregar a la lista de administradores del festival
 * @return UsuarioDTO, el dto del usuario agregado como administrador al festival
 * @throws BusinessLogicException 
 */
@POST
@Path("{id :\\d+}/usuarios{idUsuario:\\d+}")
public UsuarioDTO addUsuario(@PathParam("id") Long id,@PathParam("idUsuario") Long idUsuario)throws BusinessLogicException{
UsuarioEntity uEntity = new UsuarioDTO(usuarioLogic.getUsuario(idUsuario)).toEntity();
FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
UsuarioEntity stored = usuarioLogic.createUsuario(uEntity);
festival.getAdmins().add(stored);
return new UsuarioDTO(stored);
}
    /*
    * Actualiza el festival con el id dado por parámetro
    */
@PUT
@Path("{id:\\d+}")
public FestivalDTO updateFestival(@PathParam("id") Long id, FestivalDTO dto){
    FestivalEntity entity=dto.toEntity();
    entity.setId(id);
    return new FestivalDTO(festivalLogic.updateFestival(entity));
}
@DELETE
@Path("{id:\\d+}")
public void deleteFestival(@PathParam("id") Long id){
 festivalLogic.deleteFestival(id);
}
}
