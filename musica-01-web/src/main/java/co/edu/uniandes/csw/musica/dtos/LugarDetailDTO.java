/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.dtos;


import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author js.barbosa11
 */
@XmlRootElement
public class LugarDetailDTO extends LugarDTO {
    
    private Boolean abierto;
    private Integer capacidad;
    private Integer capacidadSonido;
    private List<FuncionEntity> funcionesLugar;
    
    /**
     *
     */
    public LugarDetailDTO(){
        super();
    }
    
    /**
     *
     * @param entity
     */
    public LugarDetailDTO(LugarEntity entity){
        super(entity);
        
        if(entity != null){
            this.abierto = entity.getAbierto();
            this.capacidad = entity.getCapacidad();
            this.capacidadSonido = entity.getCapacidadSonido();
            this.funcionesLugar = entity.getFuncionesLugar();
        }
    }
    
    /**
     *
     * @return
     */
    public List<FuncionEntity> getFuncionesLugar(){
        return funcionesLugar;
    }

    /**
     *
     * @param funcionesLugar
     */
    public void setFuncionesLugar(List<FuncionEntity> funcionesLugar){
        this.funcionesLugar=funcionesLugar;
    }

    /**
     *
     * @return
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     *
     * @param capacidad
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     *
     * @return
     */
    public Integer getCapacidadSonido() {
        return capacidadSonido;
    }

    /**
     *
     * @param capacidadSonido
     */
    public void setCapacidadSonido(Integer capacidadSonido) {
        this.capacidadSonido = capacidadSonido;
    }

    /**
     *
     * @return
     */
    public Boolean getAbierto() {
        return abierto;
    }

    /**
     *
     * @param abierto
     */
    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }
    
    /**
     *
     * @return
     */
    @Override
    public LugarEntity toEntity(){
        LugarEntity entity = super.toEntity();
        entity.setAbierto(abierto);
        entity.setCapacidad(capacidad);
        entity.setCapacidadSonido(capacidadSonido);
        entity.setFuncionesLugar(funcionesLugar);
        return entity;
    }
    
}
