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

@Entity 
public class FuncionEntity implements Serializable  {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Temporal(TemporalType.DATE) 
 private Date fechaInicio;
 @Temporal(TemporalType.DATE) 
 private Date fechafin;
 private boolean aprobada;
 private Double calificacion; 
    
    @ManyToOne
 private LugarEntity lugarFuncion;
    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL)
 private List<BoletaEntity> boletas;
    @ManyToMany
 private List<MusicoEntity> musicos;
    @ManyToOne
 private FestivalEntity festival;
 
public FuncionEntity(){
	 
 }

public Long getId() {
        return id;
}
public void setId(Long id) {
        this.id = id;
}

 public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
}
 public Date getFechaInicio() {
	return fechaInicio;
}

 public void setFechafin(Date fechafin) {
	this.fechafin = fechafin;
}
 public Date getFechafin() {
	return fechafin;
}

 public boolean getAprobada() {
	return aprobada;
}
 public void setAprobada(boolean aprobada) {
	this.aprobada = aprobada;
}
 
  public Double getCalificacion() {
	return calificacion;
}
 public void setCalificacion(Double calificacion) {
	this.calificacion = calificacion;
}

public LugarEntity getLugar(){
    return lugarFuncion;
 }
public void setLugar(LugarEntity lugar) {    
      this.lugarFuncion=lugar;
}

 public List<BoletaEntity> getBoletas() {
	return boletas;
}
 public void setBoletas(List<BoletaEntity> Boletas) {
	this.boletas= Boletas;
}
 
 
 public List<MusicoEntity> getMusicos() {
	return musicos;
}
 public void setMusicos(List<MusicoEntity> musicos) {
	this.musicos= musicos;
}
 
}
