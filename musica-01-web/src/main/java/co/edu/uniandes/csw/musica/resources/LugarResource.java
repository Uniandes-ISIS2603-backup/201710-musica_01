/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.LugarDTO;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.ejbs.LugarLogic;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
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

/**
 *
 * @author jc.bustamante143
 */
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource {
    
    @Inject private LugarLogic lugarLogic;
    @Inject private FuncionLogic funcionLogic;
    
    
    private List<LugarDTO> listEntity2DTO(List<LugarEntity> entityList){
        List<LugarDTO> list = new ArrayList<>();
        for (LugarEntity entity : entityList) {
            list.add(new LugarDTO(entity));
        }
        return list;
    }
    
    /**
     *
     * @return
     */
    @GET
    public List<LugarDTO> getLugares() {
        
        return listEntity2DTO(lugarLogic.getLugares());
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id: \\d+}")

    public LugarDTO getLugar(@PathParam("id") Long id) {
        LugarDTO l = new LugarDTO(lugarLogic.getLugar(id));
        if(l == null) throw new NotFoundException();
        return l;
    }
    
    /**
     *
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public LugarDTO createLugar(LugarDTO dto) throws BusinessLogicException{
        return new LugarDTO(lugarLogic.createLugar(dto.toEntity()));
    }
    
    /**
     *
     * @param id
     * @param lugarDTO
     * @param id2
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id: \\d+}/funciones/{id2: \\d+}")
    public void addLugarFestival(@PathParam("id") Long id, LugarDTO lugarDTO, @PathParam("id2") Long id2)throws BusinessLogicException
    {
        LugarEntity lugar = getLugar(id).toEntity();
        FuncionEntity funcion = funcionLogic.getFuncion(id2);
        lugar.getFuncionesLugar().add(funcion);
        funcion.setLugar(lugar);
       
    }
    
    /**
     *
     * @param id
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public LugarDTO updateLugar(@PathParam("id") Long id, LugarDTO dto) throws BusinessLogicException {
        LugarEntity entity = dto.toEntity();
        entity.setId(id);
        return new LugarDTO(lugarLogic.updateLugar(entity));
    }
    
    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id) throws BusinessLogicException {
        lugarLogic.deleteLugar(id);
    }
    
}
