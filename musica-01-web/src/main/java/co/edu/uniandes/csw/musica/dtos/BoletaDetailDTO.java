// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.UsuarioEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */
@XmlRootElement
public class BoletaDetailDTO extends BoletaDTO {
    
    private UsuarioEntity cliente;
    private FuncionEntity funcion;
    
    public BoletaDetailDTO(BoletaEntity boleta) {
        super(boleta);
        
        if(boleta != null)
        {
            this.cliente = boleta.getCliente();
            this.funcion = boleta.getFuncion();
        }
    }

    public BoletaDetailDTO() 
    {
        super();
    }

    public UsuarioEntity getCliente() {
        return cliente;
    }
    public void setcliente(UsuarioEntity cliente) {
        this.cliente = cliente;
    }
    
    public FuncionEntity getFuncion() {
        return funcion;
    }
    public void setFuncion(FuncionEntity funcion) {
        this.funcion = funcion;
    }
    /**
     *Convierte BoletaDetailDTO a BoletaEntity incluyendo a BoletaDTO
     * 
     * @return Nuevo objeto BoletaEntity.
     */
    @Override
    public BoletaEntity toEntity()
    {
        BoletaEntity entity = super.toEntity();
        entity.setCliente(cliente);
        entity.setFuncion(funcion);
        return entity;
    }
}