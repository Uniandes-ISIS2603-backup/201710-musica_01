
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO 
{
    private List<BoletaEntity> boletas; 
    
    public UsuarioDetailDTO(UsuarioEntity usuario)
    {
        super(usuario);
        if(usuario != null)
        {
            this.boletas = usuario.getBoletas();
        }
    }
    
    public UsuarioDetailDTO()
    {
        super();
    }

    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();
        entity.setBoletas(boletas);
        return entity;
    }

    public List<BoletaEntity> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }
    
    


}
