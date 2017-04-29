/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.ejbs.MusicoLogic;
import co.edu.uniandes.csw.musica.dtos.MusicoDTO;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.bustamante143
 */
@Path("/musicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MusicoResource 
{

    @Inject private MusicoLogic musicoLogic;

     /**
     * Obtiene la lista de los registros de Musico
     *
     * @return Colección de objetos de MusicoDTO
     * @generated
     */
    @GET
    public List <MusicoDTO> getMusicos()
    {
        List <MusicoDTO> musicoDTOs = new ArrayList<>();
        List <MusicoEntity> musicos = musicoLogic.getMusicos();
        for(MusicoEntity musico : musicos)
        {
            MusicoDTO dto = new MusicoDTO(musico);
            musicoDTOs.add(dto);
        }
        return musicoDTOs;
    }
    
    /**
    * Obtiene el músico con el Id dado por ruta
     * @param id
     * @return 
    */
    @GET
    @Path("{id: \\d+}")
    public MusicoDTO getMusico(@PathParam("id") Long id) 
    {
        MusicoDTO m = new MusicoDTO(musicoLogic.getMusico(id));
        if(m == null) throw new NotFoundException();
        return m;
    }
    
    /*
    * Actualiza el músico con el id dado por parámetro
    */

    /**
     *
     * @param id
     * @param dto
     * @return
     * @throws BusinessLogicException
     */

    @PUT
    @Path("{id: \\d+}")
    public MusicoDTO updateMusico(@PathParam("id") Long id, MusicoDTO dto) throws BusinessLogicException
    {
        MusicoEntity entity = dto.toEntity();
        entity.setId(id);
        return new MusicoDTO(musicoLogic.updateMusico(entity));
    }
    
    
    /*
    * Elimina el músico con el Id dado por parámetro en la ruta.
    */

    /**
     *
     * @param id
     * @throws BusinessLogicException
     */

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMusico(@PathParam("id") Long id) throws BusinessLogicException 
    {
        musicoLogic.deleteMusico(id);
    }
    
    
}
