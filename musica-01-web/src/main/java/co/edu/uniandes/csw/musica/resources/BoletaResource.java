/* 
 * Copyright (c) 2017 la.herrera11.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.BoletaDTO;
import co.edu.uniandes.csw.musica.ejbs.BoletaLogic;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.entities.BoletaEntity;
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

@Path("/boletas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletaResource {
    
    
    @Inject private BoletaLogic boletaLogic;
    @Inject private FuncionLogic funcionLogic;
    
    
    /**
     * Obtiene la lista de las boletas
     *
     * @return Colección de objetos de boletaDTO
     * @generated
     */
    @GET
    public List <BoletaDTO> getboletas()
    {
        List <BoletaDTO> BoletasDTOs = new ArrayList<>();
        List <BoletaEntity> boletas = boletaLogic.getBoletas();
        for(BoletaEntity boleta : boletas)
        {
            BoletaDTO dto = new BoletaDTO(boleta);
            BoletasDTOs.add(dto);
        }
        return BoletasDTOs;
    }
    
    /**
    * Obtiene la boleta con el Id dado por ruta
    */
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("id") Long id) throws BusinessLogicException 
    {
        BoletaDTO ticket = new BoletaDTO(boletaLogic.getBoleta(id));
        if(ticket == null) throw new NotFoundException();
        return ticket;    
        
    }
    
    /*
    * Actualiza la boleta con el id dado por parámetro
    */
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateBoleta(@PathParam("id") Long id, BoletaDTO dto) throws BusinessLogicException
    {
        BoletaEntity entity = dto.toEntity();
        entity.setId(id);
        return new BoletaDTO(boletaLogic.updateBoleta(entity));
    }
   
    
    /*
    * Elimina la boleta con el Id dado por parámetro en la ruta.
    */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBoleta(@PathParam("id") Long id) throws BusinessLogicException 
    {
        boletaLogic.deleteBoleta(id);
    }
    
    /**
     *
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public BoletaDTO createBoleta(BoletaDTO dto) throws BusinessLogicException {
        return new BoletaDTO(boletaLogic.createBoleta(dto.toEntity()));
    }
}
