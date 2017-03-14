/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.musica.ejbs.CiudadLogic;
import co.edu.uniandes.csw.musica.entities.CiudadEntity;
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
@Path("/ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadResource {
    
    @Inject private CiudadLogic ciudadLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
     private List<CiudadDetailDTO> listEntity2DTO(List<CiudadEntity> entityList){
        List<CiudadDetailDTO> list = new ArrayList<>();
        for (CiudadEntity entity : entityList) {
            list.add(new CiudadDetailDTO(entity));
        }
        return list;
    }
     
     @GET
    public List<CiudadDetailDTO> getCiudades() {
        
        return listEntity2DTO(ciudadLogic.getCiudades());
    }
    
    
    @GET
    @Path("{id: \\d+}")
    public CiudadDetailDTO getCiudad(@PathParam("id") Long id) {
        return new CiudadDetailDTO(ciudadLogic.getCiudad(id));
    }
    
    
    @POST
    public CiudadDetailDTO createCiudad(CiudadDetailDTO dto) {
        return new CiudadDetailDTO(ciudadLogic.createCiudad(dto.toEntity()));
    }
    
    @POST
    @Path("{id: \\d+}")
    public CiudadDetailDTO createCiudad(@PathParam("id") Long id, CiudadDetailDTO dto)
    {
        return null;
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public CiudadDetailDTO updateCiudad(@PathParam("id") Long id, CiudadDetailDTO dto) {
        CiudadEntity entity = dto.toEntity();
        entity.setId(id);
        return new CiudadDetailDTO(ciudadLogic.updateCiudad(entity));
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) {
        ciudadLogic.deleteCiudad(id);
    }
   
    
}
