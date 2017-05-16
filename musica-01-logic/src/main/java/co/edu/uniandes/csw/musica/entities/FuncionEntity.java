/* 
 * Copyright (c) 2017 la.herrera11.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
package co.edu.uniandes.csw.musica.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author la.herrera11
 */
@Entity 
public class FuncionEntity implements Serializable  {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @PodamExclude
 private Long id;
 @PodamExclude
 private boolean aprobada;
 @PodamExclude
 private Double calificacion; 
 
 @Temporal(TemporalType.DATE) 
 @PodamExclude
 private Date fechaInicio;
 
 @Temporal(TemporalType.DATE)
 @PodamExclude 
 private Date fechafin;
 
 @ManyToOne
 @PodamExclude
 private LugarEntity lugarFuncion;
    
 @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL)
 @PodamExclude
 private List<BoletaEntity> boletas;
    
 @ManyToMany
 @PodamExclude
 private List<MusicoEntity> musicos;
    
 @ManyToOne
 @PodamExclude
 private FestivalEntity festival;
 
    /**
     *
     */
    public FuncionEntity(){
	 
 }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
}

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
}

    /**
     *
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
}

    /**
     *
     * @return
     */
    public Date getFechaInicio() {
	return fechaInicio;
}

    /**
     *
     * @param fechafin
     */
    public void setFechafin(Date fechafin) {
	this.fechafin = fechafin;
}

    /**
     *
     * @return
     */
    public Date getFechafin() {
	return fechafin;
}

    /**
     *
     * @return
     */
    public boolean getAprobada() {
	return aprobada;
}

    /**
     *
     * @param aprobada
     */
    public void setAprobada(boolean aprobada) {
	this.aprobada = aprobada;
}
 
    /**
     *
     * @return
     */
    public Double getCalificacion() {
	return calificacion;
}

    /**
     *
     * @param calificacion
     */
    public void setCalificacion(Double calificacion) {
	this.calificacion = calificacion;
}

    /**
     *
     * @return
     */
    public LugarEntity getLugar(){
    return lugarFuncion;
 }
    
    /**
     *
     * @param lugar
     */
    public void setLugar(LugarEntity lugar) {    
      this.lugarFuncion=lugar;
}

    /**
     *
     * @return
     */
    public List<BoletaEntity> getBoletas() {
	return boletas;
}

    /**
     *
     * @param Boletas
     */
    public void setBoletas(List<BoletaEntity> Boletas) {
	this.boletas= Boletas;
}
 
    /**
     *
     * @return
     */
    public List<MusicoEntity> getMusicos() {
	return musicos;
}

    /**
     *
     * @param musicos
     */
    public void setMusicos(List<MusicoEntity> musicos) {
	this.musicos= musicos;
}
 
}
