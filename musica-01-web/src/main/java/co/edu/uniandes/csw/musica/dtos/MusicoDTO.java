
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class MusicoDTO implements Serializable
{

    private Long id;    
    private String nombre;

    private GeneroEntity generoMusico;
    
    public MusicoDTO(MusicoEntity musico)
    {
        if(musico !=null)
        {
            this.id = musico.getId();
            this.nombre = musico.getNombre();
           
            this.generoMusico = musico.getGenero();
        }
    }
    
    public MusicoEntity toEntity()
    {
        MusicoEntity musico = new MusicoEntity();
        musico.setId(id);
        musico.setNombre(nombre);

        musico.setGenero(generoMusico);
        return musico;
    }
    
    public MusicoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public GeneroEntity getGenero() {
        return generoMusico;
    }

    public void setGenero(GeneroEntity genero) {
        this.generoMusico = genero;
    }
    
}
