/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.dtos.GeneroDTO;
import co.edu.uniandes.csw.musica.dtos.MusicoDTO;
import co.edu.uniandes.csw.musica.ejbs.MusicoLogic;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
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

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GeneroResource 
{
    
    @Inject private GeneroLogic generoLogic;
    @Inject private MusicoLogic musicoLogic;

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
        GeneroDTO g = new GeneroDTO(generoLogic.getGenero(id));
        if(g == null) throw new NotFoundException();
        return g;
    }
    
    /*
    * Crea un nuevo género.
    */
    @POST
    public GeneroDTO addGenero(GeneroDTO generoDTO)throws BusinessLogicException
    {
        GeneroEntity genero = generoDTO.toEntity();
        GeneroEntity storedGenero = generoLogic.createGenero(genero);
        return new GeneroDTO(storedGenero);
       
    }
    
    /*
    * Crea un nuevo músico asociado a un género.
    */
    @POST
    @Path("{id: \\d+}/musicos")
    public MusicoDTO addMusico(@PathParam("id") Long id, MusicoDTO musicoDTO)throws BusinessLogicException
    {
        MusicoEntity musico = musicoDTO.toEntity();
        GeneroEntity genero = new GeneroDTO(generoLogic.getGenero(id)).toEntity();
        MusicoEntity storedMusico = musicoLogic.createMusico(musico);
        genero.getMusicosGenero().add(musico);
        return new MusicoDTO(storedMusico);
       
    }
    
    /*
    * Actualiza el género con el id dado por parámetro
    */
    @PUT
    @Path("{id: \\d+}")
    public GeneroDTO updateGenero(@PathParam("id") Long id, GeneroDTO dto) throws BusinessLogicException
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
    public void deleteGenero(@PathParam("id") Long id) throws BusinessLogicException 
    {
        generoLogic.deleteGenero(id);
    }
}
