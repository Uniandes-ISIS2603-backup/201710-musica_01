/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.dtos.GeneroDTO;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GeneroResource 
{
    
    @Inject private GeneroLogic generoLogic;

     /**
     * Obtiene la lista de los registros de Genero
     *
     * @return Colección de objetos de GeneroDTO
     * @generated
     */
    @GET
    public List <GeneroDTO> getGeneros()
    {
        List <GeneroDTO> GeneroDTOs = new ArrayList<>();
        List <GeneroEntity> generos = generoLogic.getGeneros();
        for(GeneroEntity genero : generos)
        {
            GeneroDTO dto = new GeneroDTO(genero);
            GeneroDTOs.add(dto);
        }
        return GeneroDTOs;
    }
    
    /**
    * Obtiene el género con el Id dado por ruta
    */
    @GET
    @Path("{id: \\d+}")
    public GeneroDTO getGenero(@PathParam("id") Long id) 
    {
        return new GeneroDTO(generoLogic.getGenero(id));
    }
    
    /*
    * Crea un nuevo género.
    */
    @POST
    public GeneroDTO addGenero(GeneroDTO generoDTO)throws BusinessLogicException{
        GeneroEntity genero = generoDTO.toEntity();
        GeneroEntity storedGenero = generoLogic.createGenero(genero);
        return new GeneroDTO(storedGenero);
       
    }
    
    /*
    * Actualiza el género con el id dado por parámetro
    */
    @PUT
    @Path("{id: \\d+}")
    public GeneroDTO updateGenero(@PathParam("id") Long id, GeneroDTO dto)
    {
        GeneroEntity entity = dto.toEntity();
        entity.setId(id);
        return new GeneroDTO(generoLogic.updateGenero(entity));
    }
    
    
    /*
    * Elimina el género con el Id dado por parámetro en la ruta.
    */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteGenero(@PathParam("id") Long id) 
    {
        generoLogic.deleteGenero(id);
    }
}