/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.FestivalEntity;
import co.edu.uniandes.csw.musica.entities.GeneroEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneroDetailDTO extends GeneroDTO
{
    private List<FestivalEntity> festivalesGenero;
    private List<MusicoEntity> musicosGenero;
    
    public GeneroDetailDTO(GeneroEntity genero) {
        super(genero);
        
        if(genero != null)
        {
            this.musicosGenero = genero.getMusicosGenero();
            this.festivalesGenero = genero.getFestivalesGenero();
        }
    }

    public GeneroDetailDTO() 
    {
        super();
    }

    public List<MusicoEntity> getMusicosGenero() {
        return musicosGenero;
    }

    public void setMusicosGenero(List<MusicoEntity> musicosGenero) {
        this.musicosGenero = musicosGenero;
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
        entity.setMusicosGenero(musicosGenero);
        entity.setFestivalesGenero(festivalesGenero);
        return entity;
    }
    
}
