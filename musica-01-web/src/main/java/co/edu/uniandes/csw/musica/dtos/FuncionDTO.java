/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;

import co.edu.uniandes.csw.musica.entities.BoletaEntity;
import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import co.edu.uniandes.csw.musica.entities.MusicoEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */
 @XmlRootElement
public class FuncionDTO implements Serializable {
   
    private Long id;    
    private Date fechaInicio;
    private Date fechafin;
    private boolean aprobada;
    private Double calificacion; 
    private LugarEntity lugar;
    private List<BoletaEntity> boletas;
    private List<MusicoEntity> musicos;
    
     public FuncionDTO(FuncionEntity funcion)
    {
        if(funcion != null)
        {
            this.id = funcion.getId();
            this.fechaInicio= funcion.getFechaInicio();
            this.fechafin=funcion.getFechafin();
            this.aprobada=funcion.getAprobada();
            this.calificacion=funcion.getCalificacion();
            this.lugar=funcion.getLugar();
            this.boletas=funcion.getBoletas();
            this.boletas=funcion.getBoletas();
            this.musicos= funcion.getMusicos();
        }
    }
    
    public FuncionEntity toEntity()
    {
        FuncionEntity funcion = new FuncionEntity();
        funcion.setId(id);
        funcion.setFechaInicio((java.sql.Date) fechaInicio);
        funcion.setFechafin((java.sql.Date) fechafin);
        funcion.setAprobada(aprobada);
        funcion.setAprobada(aprobada);
        funcion.setCalificacion(calificacion);
        funcion.setLugar(lugar);
        funcion.setBoletas(boletas);
        funcion.setMusicos(musicos);
        return funcion;
    }
    
    public FuncionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio(Date fechaInicio) {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio= fechaInicio;
    }

   //falta falta
}
