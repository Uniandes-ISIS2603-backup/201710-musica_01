// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.LugarDTO;
// TODO: eliminar los import que no se usan
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.barbosa11
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
    
    @GET
    public List<LugarDTO> getLugares() {
        
        return listEntity2DTO(lugarLogic.getLugares());
    }
    
    @GET
    @Path("{id: \\d+}")
    // TODO: retornar una excepción / código 404 si no existe
    public LugarDTO getLugar(@PathParam("id") Long id) {
        return new LugarDTO(lugarLogic.getLugar(id));
    }
    
    @POST
    public LugarDTO createLugar(LugarDTO dto) throws BusinessLogicException{
        return new LugarDTO(lugarLogic.createLugar(dto.toEntity()));
    }
    
        
    @POST
    @Path("{id: \\d+}/funciones/{id2: \\d+}")
    public void addLugarFestival(@PathParam("id") Long id, LugarDTO lugarDTO, @PathParam("id2") Long id2)throws BusinessLogicException
    {
        LugarEntity lugar = getLugar(id).toEntity();
        FuncionEntity funcion = funcionLogic.getFuncion(id2);
        lugar.getFuncionesLugar().add(funcion);
        funcion.setLugar(lugar);
       
    }
    
    @PUT
    @Path("{id: \\d+}")
    public LugarDTO updateLugar(@PathParam("id") Long id, LugarDTO dto) {
        LugarEntity entity = dto.toEntity();
        entity.setId(id);
        return new LugarDTO(lugarLogic.updateLugar(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id) {
        lugarLogic.deleteLugar(id);
    }
    
}
