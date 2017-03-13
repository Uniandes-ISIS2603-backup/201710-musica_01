/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MusicoDetailDTO extends MusicoDTO
{

    public MusicoDetailDTO(MusicoEntity musico) 
    {
        super(musico);
    }

    public MusicoDetailDTO() 
    {
        super();
    }
    
    /**
     *Convierte MusicoDetailDTO a MusicoEntity incluyendo a MusicoDTO
     * 
     * @return Nuevo objeto MusicoEntity.
     */
    @Override
    public MusicoEntity toEntity()
    {
        MusicoEntity entity = super.toEntity();
        return entity;
    }
        
    
}
