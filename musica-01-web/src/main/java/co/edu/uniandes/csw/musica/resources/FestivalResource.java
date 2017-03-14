/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.FestivalDTO;
import co.edu.uniandes.csw.musica.ejbs.FestivalLogic;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
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
 * @author pa.alvarado10
 */
@Path("/festivales")
@Consumes (MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FestivalResource 
{
@Inject private FestivalLogic festivalLogic; 
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
