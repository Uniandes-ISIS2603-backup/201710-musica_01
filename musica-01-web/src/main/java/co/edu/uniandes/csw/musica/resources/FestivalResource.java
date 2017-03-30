package co.edu.uniandes.csw.musica.resources;

import co.edu.uniandes.csw.musica.dtos.CiudadDTO;
import co.edu.uniandes.csw.musica.dtos.FestivalDTO;
import co.edu.uniandes.csw.musica.dtos.FuncionDTO;
import co.edu.uniandes.csw.musica.dtos.GeneroDTO;
import co.edu.uniandes.csw.musica.dtos.UsuarioDTO;
import co.edu.uniandes.csw.musica.ejbs.CiudadLogic;
import co.edu.uniandes.csw.musica.ejbs.FestivalLogic;
import co.edu.uniandes.csw.musica.ejbs.FuncionLogic;
import co.edu.uniandes.csw.musica.ejbs.GeneroLogic;
import co.edu.uniandes.csw.musica.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

@Path("/festivales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FestivalResource {

    @Inject
    private FestivalLogic festivalLogic;
    @Inject
    private GeneroLogic generoLogic;
    @Inject
    private FuncionLogic funcionLogic;
    @Inject
    private UsuarioLogic usuarioLogic;
    @Inject
    private CiudadLogic ciudadLogic;

    /**
     * Obtiene la lista de los registros de Festival
     *
     * @return Coleccion de objetos FestivalDTO
     */
    @GET
    public List<FestivalDTO> getFestivales() {
        List<FestivalDTO> festivalesDTOs = new ArrayList<>();
        List<FestivalEntity> entity = festivalLogic.getFestivales();
        for (FestivalEntity festival : entity) {
            FestivalDTO dto = new FestivalDTO(festival);
            festivalesDTOs.add(dto);
        }
        return festivalesDTOs;
    }

    /**
     * Obtiene el festival con el id dado
     *
     * @return festivalDTO, si existe uno con el id dado por parametro
     */
    @GET
    @Path("{id:\\d+}")
    public FestivalDTO getFestival(@PathParam("id") Long id) 
    {
        FestivalDTO f = new FestivalDTO(festivalLogic.getFestival(id));
        if( f == null) throw new NotFoundException();
        return f;
    }

    /**
     * Retorna la colección de funciones relacionadas a un festival.
     *
     * @param id, id del festival, del cual se desea consultar el listado de
     * funciones
     * @return Lista dtos, lista de los dtos de las funciones que tiene el
     * festival
     */
    @GET
    @Path("{id:\\d+}/funciones")
    public List<FuncionDTO> getFuncionesFestival(@PathParam("id") Long id) {
        FestivalEntity entity = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        ArrayList<FuncionEntity> funciones = entity.getFunciones();
        ArrayList<FuncionDTO> dtos = new ArrayList<>();
        for (FuncionEntity funcion : funciones) {
            dtos.add(new FuncionDTO(funcion));
        }
        return dtos;
    }

    /**
     * @param dto cuerpo del festival que se desea agregar
     * @return retorna el festivalDTO agregado
     * @throws BusinessLogicException
     */
    @POST
    public FestivalDTO addFestival(FestivalDTO dto) throws BusinessLogicException {
        FestivalEntity festival = dto.toEntity();
        FestivalEntity stored = festivalLogic.createFestival(festival);
        return new FestivalDTO(stored);
    }

    /**
     * Metodo que agrega un genero, lo agrega a la lista de generos de un
     * festival dado.
     *
     * @param id, id del festial al cual se le desea agregar el genero (Debe
     * existir)
     * @param dto, Cuerpo del generoDTO que se le desea agregar al festival dado
     * @return el GeneroDTO ya creado en la base de datos
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id :\\d+}/generos")
    public GeneroDTO addGeneroFestival(@PathParam("id") Long id, GeneroDTO dto) throws BusinessLogicException {
        GeneroEntity entity = dto.toEntity();
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        GeneroEntity stored = generoLogic.createGenero(entity);
        festival.getGeneros().add(stored);
        festivalLogic.updateFestival(festival);
        return new GeneroDTO(stored);
    }

    /**
     * Metodo que dado un id de un genero, lo agrega al festival con id dado por
     * parametro
     *
     * @param id, id del festival
     * @param idGenero, id del genero que se desea agregar al festival
     * @return GeneroDTO del genero guardado en la lista de generos del festival
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id :\\d+}/generos/{idGenero:\\d+}")
    public GeneroDTO addGeneroFestival(@PathParam("id") Long id, @PathParam("idGenero") Long idGenero) throws BusinessLogicException {
        GeneroEntity entity = new GeneroDTO(generoLogic.getGenero(idGenero)).toEntity();
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        festival.getGeneros().add(entity);
        festivalLogic.updateFestival(festival);
        return new GeneroDTO(entity);
    }

    /**
     * Metodo que crea un usuario y lo agrega a la lista de administradores de
     * un festival
     *
     * @param id, id del festival al que se le desea agregar el administrador
     * @param dto, UsuarioDTO cuerpo del usuario que se desea crear
     * @return el UsuarioDTO creado que fue agregado a la lista de
     * administradores del festival dado
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id :\\d+}/usuarios")
    public UsuarioDTO addUsuario(@PathParam("id") Long id, UsuarioDTO dto) throws BusinessLogicException {
        UsuarioEntity uEntity = dto.toEntity();
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        UsuarioEntity stored = usuarioLogic.createUsuario(uEntity);
        festival.getAdmins().add(stored);
        festivalLogic.updateFestival(festival);
        return new UsuarioDTO(stored);
    }

    /**
     * Metodo que agrega un Usuario con id dado, a la lista de administradores
     * de un festival
     *
     * @param id, id del festival al que se le desea agregar el administrador
     * @param idUsuario, id del usuario que se desea agregar a la lista de
     * administradores del festival
     * @return UsuarioDTO, el dto del usuario agregado como administrador al
     * festival
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id :\\d+}/usuarios{idUsuario:\\d+}")
    public UsuarioDTO addUsuario(@PathParam("id") Long id, @PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        UsuarioEntity uEntity = new UsuarioDTO(usuarioLogic.getUsuario(idUsuario)).toEntity();
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        festival.getAdmins().add(uEntity);
        festivalLogic.updateFestival(festival);
        return new UsuarioDTO(uEntity);
    }

    /**
     * Metodo que agrega una ciudad dado su id, a la lista de ciudades de un
     * festival dado
     *
     * @param id, id festival
     * @param idCiudad, id de la ciudad
     * @return dto de la ciudad agregada a la lista de ciuadades
     * @throws BusinessLogicException
     */
    @POST
    @Path("{id :\\d+}/ciudades/{idCiudad}")
    public CiudadDTO addCiudadFestival(@PathParam("id") Long id, @PathParam("idCiudad") Long idCiudad) throws BusinessLogicException {
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        CiudadEntity ciudad = new CiudadDTO(ciudadLogic.getCiudad(idCiudad)).toEntity();
        festival.getCiudades().add(ciudad);
        festivalLogic.updateFestival(festival);
        return new CiudadDTO(ciudad);
    }

    /*
     * Actualiza el festival con el id dado por parámetro
     */
    @PUT
    @Path("{id:\\d+}")
    public FestivalDTO updateFestival(@PathParam("id") Long id, FestivalDTO dto) {
        FestivalEntity entity = dto.toEntity();
        entity.setId(id);
        return new FestivalDTO(festivalLogic.updateFestival(entity));
    }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("{id:\\d+}")
    public void deleteFestival(@PathParam("id") Long id) {
        festivalLogic.deleteFestival(id);
    }

    @DELETE
    @Path("{id: \\d+}/ciudades/{idCiudad}")
    public void deleteCiudadFestival(@PathParam("id") Long id, @PathParam("idCiudad") Long idCiudad) {
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        for (CiudadEntity ciudad : festival.getCiudades()) {
            if (ciudad.getId() == idCiudad) {
                festival.getCiudades().remove(ciudad);
                break;
            }
        }
        festivalLogic.updateFestival(festival);
    }

    //DELETE /festivales/{idFestival}/ciudades/{idCiudad}
    @DELETE
    @Path("{id: \\d+}/generos/{idGenero}")
    public void deleteGeneroFestival(@PathParam("id") Long id, @PathParam("idGenero") Long idGenero) {
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        for (GeneroEntity genero : festival.getGeneros()) {
            if (Objects.equals(genero.getId(), idGenero)) {
                festival.getGeneros().remove(genero);
                break;
            }
        }
        festivalLogic.updateFestival(festival);
    }

    //DELETE /festivales/{idFestival}/generos/{idGenero}
    @DELETE
    @Path("{id: \\d+}/usuarios/{idUsuario}")
    public void deleteUsuarioFestival(@PathParam("id") Long id, @PathParam("idUsuario") Long idUsuario) {
        FestivalEntity festival = new FestivalDTO(festivalLogic.getFestival(id)).toEntity();
        for (UsuarioEntity usuario : festival.getAdmins()) {
            if (usuario.getId() == idUsuario) {
                festival.getAdmins().remove(usuario);
                break;
            }
        }
        festivalLogic.updateFestival(festival);
    }
}
