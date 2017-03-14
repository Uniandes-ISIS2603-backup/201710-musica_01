/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.LugarDetailDTO;
import co.edu.uniandes.csw.musica.ejbs.LugarLogic;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
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

/**
 *
 * @author js.barbosa11
 */
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource {
    
    @Inject private LugarLogic lugarLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    
    private List<LugarDetailDTO> listEntity2DTO(List<LugarEntity> entityList){
        List<LugarDetailDTO> list = new ArrayList<>();
        for (LugarEntity entity : entityList) {
            list.add(new LugarDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<LugarDetailDTO> getEmployees() {
        
        return listEntity2DTO(lugarLogic.getLugares());
    }
    
    @GET
    @Path("{id: \\d+}")
    public LugarDetailDTO getEmployee(@PathParam("id") Long id) {
        return new LugarDetailDTO(lugarLogic.getLugar(id));
    }
    
    @POST
    public LugarDetailDTO createEmployee(LugarDetailDTO dto) {
        return new LugarDetailDTO(lugarLogic.createLugar(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public LugarDetailDTO updateEmployee(@PathParam("id") Long id, LugarDetailDTO dto) {
        LugarEntity entity = dto.toEntity();
        entity.setId(id);
        return new LugarDetailDTO(lugarLogic.updateLugar(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEmployee(@PathParam("id") Long id) {
        lugarLogic.deleteLugar(id);
    }
    
}
