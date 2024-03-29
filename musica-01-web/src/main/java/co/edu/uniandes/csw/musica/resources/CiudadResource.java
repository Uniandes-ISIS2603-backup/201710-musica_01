/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.CiudadDTO;
import co.edu.uniandes.csw.musica.dtos.LugarDTO;
import co.edu.uniandes.csw.musica.ejbs.CiudadLogic;
import co.edu.uniandes.csw.musica.ejbs.LugarLogic;
import co.edu.uniandes.csw.musica.entities.CiudadEntity;
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
@Path("/ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadResource {
    
    @Inject private CiudadLogic ciudadLogic;
    @Inject private LugarLogic lugarLogic;
    
    /**
     *
     * @return
     */
    @GET
    public List<CiudadDTO> getCiudades() 
    {
        List <CiudadDTO> CiudadDTOs = new ArrayList<>();
        List <CiudadEntity> ciudades = ciudadLogic.getCiudades();
        for(CiudadEntity ciudad : ciudades)
        {
            CiudadDTO dto = new CiudadDTO(ciudad);
            CiudadDTOs.add(dto);
        }
        return CiudadDTOs;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCiudad(@PathParam("id") Long id) 
    {
        CiudadDTO c = new CiudadDTO(ciudadLogic.getCiudad(id));
        if(c == null) throw new NotFoundException();
        return c;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id: \\d+}/lugares")
    public List<LugarDTO> getLugaresCiudad(@PathParam("id") Long id) {
        
        CiudadEntity ciudad = getCiudad(id).toEntity();
        List <LugarDTO> LugarDTOs = new ArrayList<>();
        List <LugarEntity> lugares = ciudad.getLugaresCiudad();
        for(LugarEntity lugar : lugares)
        {
            LugarDTO dto = new LugarDTO(lugar);
            LugarDTOs.add(dto);
        }
        return LugarDTOs;
    }
    
    /**
     *
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public CiudadDTO createCiudad(CiudadDTO dto) throws BusinessLogicException {
        return new CiudadDTO(ciudadLogic.createCiudad(dto.toEntity()));
    }
    
    /**
     *
     * @param id
     * @param lugarDTO
     * @return
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id: \\d+}/lugares")
    public LugarDTO addLugar(@PathParam("id") Long id, LugarDTO lugarDTO)throws BusinessLogicException
    {
        LugarEntity lugar = lugarDTO.toEntity();
        CiudadEntity ciudad = new CiudadDTO(ciudadLogic.getCiudad(id)).toEntity();
        LugarEntity storedLugar = lugarLogic.createLugar(lugar);
        ciudad.getLugaresCiudad().add(lugar);
        return new LugarDTO(storedLugar);
       
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
    public CiudadDTO updateCiudad(@PathParam("id") Long id, CiudadDTO dto) throws BusinessLogicException {
        CiudadEntity entity = dto.toEntity();
        entity.setId(id);
        return new CiudadDTO(ciudadLogic.updateCiudad(entity));
    }
    
    /**
     *
     * @param id
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) throws BusinessLogicException {
        ciudadLogic.deleteCiudad(id);
    }
   
    
}
