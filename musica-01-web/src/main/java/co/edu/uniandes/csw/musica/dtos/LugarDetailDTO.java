// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

// TODO: eliminar los import que no son necesarios
import co.edu.uniandes.csw.musica.entities.CiudadEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
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
    
    public LugarDetailDTO(){
        super();
    }
    
    public LugarDetailDTO(LugarEntity entity){
        super(entity);
        
        if(entity != null){
            this.abierto = entity.getAbierto();
            this.capacidad = entity.getCapacidad();
            this.capacidadSonido = entity.getCapacidadSonido();
            this.funcionesLugar = entity.getFuncionesLugar();
        }
    }
    
    public List<FuncionEntity> getFuncionesLugar(){
        return funcionesLugar;
    }
    public void setFuncionesLugar(List<FuncionEntity> funcionesLugar){
        this.funcionesLugar=funcionesLugar;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCapacidadSonido() {
        return capacidadSonido;
    }

    public void setCapacidadSonido(Integer capacidadSonido) {
        this.capacidadSonido = capacidadSonido;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }
    
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
