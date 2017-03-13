/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneroDetailDTO extends GeneroDTO
{

    public GeneroDetailDTO(GeneroEntity genero) {
        super(genero);
    }

    public GeneroDetailDTO() 
    {
        super();
    }
 
    /**
     *Convierte GeneroDetailDTO a GeneroEntity incluyendo a GeneroDTO
     * 
     * @return Nuevo objeto GeneroEntity.
     */
    @Override
    public GeneroEntity toEntity()
    {
        GeneroEntity entity = super.toEntity();
        return entity;
    }
    
}
