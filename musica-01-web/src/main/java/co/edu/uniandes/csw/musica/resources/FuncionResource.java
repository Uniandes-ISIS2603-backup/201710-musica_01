/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.FuncionDTO;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
    @Inject private FuncionLogic funcionLogic;
    
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
public FuncionDTO getFuncion(@PathParam("id") Long id)
{
return new FuncionDTO(funcionLogic.getFuncion(id));
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
