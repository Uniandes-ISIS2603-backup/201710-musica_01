/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musica.dtos;


import co.edu.uniandes.csw.musica.entities.FuncionEntity;
import co.edu.uniandes.csw.musica.entities.LugarEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author la.herrera11
 */
 @XmlRootElement
public class FuncionDTO implements Serializable {
   
    private Long id;    
    private Date fechaInicio;
    private Date fechaFin;
    private boolean aprobada;
    private Double calificacion; 
    private LugarEntity lugar;
    
    
     public FuncionDTO(FuncionEntity funcion)
    {
        if(funcion != null)
        {
            this.id = funcion.getId();
            this.fechaInicio= funcion.getFechaInicio();
            this.fechaFin=funcion.getFechafin();
            this.aprobada=funcion.getAprobada();
            this.calificacion=funcion.getCalificacion();
            this.lugar=funcion.getLugar();
        }
    }
    
    public FuncionEntity toEntity()
    {
        FuncionEntity funcion = new FuncionEntity();
        funcion.setId(id);
        funcion.setFechaInicio((java.sql.Date) fechaInicio);
        funcion.setFechafin((java.sql.Date) fechaFin);
        funcion.setAprobada(aprobada);
        funcion.setCalificacion(calificacion);
        funcion.setLugar(lugar);
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

    public Date getFechaFin(Date fechaFin) {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin= fechaFin;
    }
    
    public boolean getAprobada() {
        return aprobada;
    }
    public void setAprobada(boolean aprobada ) {
        this.aprobada = aprobada;
    }
    
    public Double getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    public LugarEntity getLugar(){
    return lugar;
    }
    public void setLugar(LugarEntity lugar){
    this.lugar= lugar;
    }
}