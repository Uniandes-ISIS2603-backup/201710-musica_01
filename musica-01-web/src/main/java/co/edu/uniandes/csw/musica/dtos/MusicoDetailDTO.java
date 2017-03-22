// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MusicoDetailDTO extends MusicoDTO
{
    private String trayectoria;
    private int requerimientoSonido;
    private int requerimientoCapacidad;
    private List<FuncionEntity> funcionesMusico;
    
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

    public MusicoDetailDTO() 
    {
        super();
    }

    public List<FuncionEntity> getFuncionesMusico() {
        return funcionesMusico;
    }

    public void setFuncionesMusico(List<FuncionEntity> funcionesMusico) {
        this.funcionesMusico = funcionesMusico;
    }
    
        public String getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    public int getRequerimientoSonido() {
        return requerimientoSonido;
    }

    public void setRequerimientoSonido(int requerimientoSonido) {
        this.requerimientoSonido = requerimientoSonido;
    }

    public int getRequerimientoCapacidad() {
        return requerimientoCapacidad;
    }

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
