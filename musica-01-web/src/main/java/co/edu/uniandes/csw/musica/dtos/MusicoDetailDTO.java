/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jc.bustamante143
 */
@XmlRootElement
public class MusicoDetailDTO extends MusicoDTO
{
    private String trayectoria;
    private int requerimientoSonido;
    private int requerimientoCapacidad;
    private List<FuncionEntity> funcionesMusico;
    
    /**
     *
     * @param musico
     */
    public MusicoDetailDTO(MusicoEntity musico) 
    {
        super(musico);
        
        if(musico != null)
        {
            this.funcionesMusico = musico.getFuncionesMusico();
            this.trayectoria = musico.getTrayectoria();
            this.requerimientoCapacidad = musico.getRequerimientoCapacidad();
            this.requerimientoSonido = musico.getRequerimientoSonido();
        }
    }

    /**
     *
     */
    public MusicoDetailDTO() 
    {
        super();
    }

    /**
     *
     * @return
     */
    public List<FuncionEntity> getFuncionesMusico() {
        return funcionesMusico;
    }

    /**
     *
     * @param funcionesMusico
     */
    public void setFuncionesMusico(List<FuncionEntity> funcionesMusico) {
        this.funcionesMusico = funcionesMusico;
    }
    
    /**
     *
     * @return
     */
    public String getTrayectoria() {
        return trayectoria;
    }

    /**
     *
     * @param trayectoria
     */
    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    /**
     *
     * @return
     */
    public int getRequerimientoSonido() {
        return requerimientoSonido;
    }

    /**
     *
     * @param requerimientoSonido
     */
    public void setRequerimientoSonido(int requerimientoSonido) {
        this.requerimientoSonido = requerimientoSonido;
    }

    /**
     *
     * @return
     */
    public int getRequerimientoCapacidad() {
        return requerimientoCapacidad;
    }

    /**
     *
     * @param requerimientoCapacidad
     */
    public void setRequerimientoCapacidad(int requerimientoCapacidad) {
        this.requerimientoCapacidad = requerimientoCapacidad;
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
        entity.setFuncionesMusico(funcionesMusico);
        entity.setTrayectoria(trayectoria);
        entity.setRequerimientoCapacidad(requerimientoCapacidad);
        entity.setRequerimientoSonido(requerimientoSonido);
        return entity;
    }
        
    
}
