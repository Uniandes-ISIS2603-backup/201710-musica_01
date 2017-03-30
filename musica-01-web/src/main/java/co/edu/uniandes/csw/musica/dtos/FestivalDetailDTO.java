
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pa.alvarado10
 */
public class FestivalDetailDTO extends FestivalDTO {
    private List <UsuarioEntity> admins;
    private List <FuncionEntity> funciones;
    private List <GeneroEntity> generos;
    private List <CiudadEntity> ciudades;
    public FestivalDetailDTO (FestivalEntity festival){
        super(festival);
        if(festival!=null){
            this.funciones=festival.getFunciones();
            this.admins=festival.getAdmins();
        }
    }
    public FestivalDetailDTO (){
    super();
    }
    public FestivalEntity toEntity(){
        FestivalEntity entity = super.toEntity();
        entity.setFunciones((ArrayList<FuncionEntity>) this.getFunciones());
        entity.setAdmins((ArrayList <UsuarioEntity>) this.getAdmins());
        return entity;
    }

    public List<UsuarioEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(List<UsuarioEntity> admins) {
        this.admins = admins;
    }

    
    
    
}
