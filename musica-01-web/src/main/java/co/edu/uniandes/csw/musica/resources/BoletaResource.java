// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.BoletaDTO;
import co.edu.uniandes.csw.musica.ejbs.BoletaLogic;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
// TODO: eliminar los import que no se usan
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author la.herrera11
 */

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
    // TODO: retorar una excepción/error 404 si no existe
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("id") Long id) 
    {
        return new BoletaDTO(boletaLogic.getBoleta(id));
    }
    
    /*
    * Actualiza la boleta con el id dado por parámetro
    */
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateGenero(@PathParam("id") Long id, BoletaDTO dto)
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
    public void deleteGenero(@PathParam("id") Long id) 
    {
        boletaLogic.deleteBoleta(id);
    }
    
}
