// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.FuncionDTO;
import co.edu.uniandes.csw.musica.dtos.MusicoDTO;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.ejbs.MusicoLogic;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
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
 * @author la.herrera11
 */

@Path("/funciones")
@Consumes (MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionResource {
    
    // TODO: indentar el código de la clase
    @Inject private FuncionLogic funcionLogic;
    @Inject private MusicoLogic musicoLogic;
    /**
 * Obtiene la lista de funciones
 * 
 * @return Coleccion de objetos FuncionesDTO
 */
@GET
public List <FuncionDTO> getFunciones(){
List <FuncionDTO> funcionesDTOs = new ArrayList<>();
List <FuncionEntity> entity = funcionLogic.getFunciones();
for(FuncionEntity funcion : entity){
    FuncionDTO dto= new FuncionDTO(funcion);
    funcionesDTOs.add(dto);
}
return funcionesDTOs;
}

/**
 * Obtiene la funcion con el id dado
 * @return funucionDTO, si existe uno con el id dado por parametro
 */
@GET
@Path ("{id:\\d+}")
// TODO: retornar una excepción / código 404 si no existe
public FuncionDTO getFuncion(@PathParam("id") Long id)
{
return new FuncionDTO(funcionLogic.getFuncion(id));
}



/**
 * Metodo que dado un id de un musico, lo agrega a la funcion con id dado por parametro
 * @param id, id de la funcion 
 * @param idMusico, id del musico que se desea agregar a la funcion
 * @return MusicoDTO del musico guardado en la funcion id del festival
 * @throws BusinessLogicException 
 */
@POST
@Path("{id :\\d+}/musicos/{idMusico:\\d+}")
public MusicoDTO addMusicoFuncion(@PathParam("id") Long id,@PathParam("idMusico") Long idMusico)throws BusinessLogicException{
   
    MusicoEntity entity = new MusicoDTO(musicoLogic.getMusico(idMusico)).toEntity();
    FuncionEntity funcion = new FuncionDTO(funcionLogic.getFuncion(id)).toEntity();
    funcion.getMusicos().add(entity);
    funcionLogic.updateFuncion(funcion);
    return new MusicoDTO(entity); 
}


/**
 * Actualiza la funcion con el id dado por parámetro
 */
@PUT
@Path("{id:\\d+}")
public FuncionDTO updateFuncion(@PathParam("id") Long id, FuncionDTO dto){
    FuncionEntity entity=dto.toEntity();
    entity.setId(id);
    return new FuncionDTO(funcionLogic.updateFuncion(entity));
}

/*
 * Elimina la funcion con el Id dado por parámetro en la ruta.
 */
@DELETE
@Path("{id:\\d+}")
public void deleteFuncion(@PathParam("id") Long id){
 funcionLogic.deleteFuncion(id);
}
}
