/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

/**
 *
 * @author alumnogreibd
 */
public class Vehiculo {
    private String matricula;
    private TipoPlaza tipo;
    private String marca;
    private String modelo;
    private Integer anoMatriculacion;
    private Usuario usuario;
    private Plaza plaza;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }
    
    public Vehiculo(String matricula, Usuario usuario) {
        this.matricula = matricula;
        this.usuario = usuario;
    }

    public Vehiculo(String matricula, TipoPlaza tipo, String marca, String modelo, Integer anoMatriculacion) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anoMatriculacion = anoMatriculacion;

    }
    
       public Vehiculo(String matricula, TipoPlaza tipo, String marca, String modelo, Integer anoMatriculacion, String dni) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anoMatriculacion = anoMatriculacion;
        this.usuario=new Usuario(dni);

    }

    public String getMatricula() {
        return matricula;
    }

    public TipoPlaza getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnoMatriculacion() {
        return anoMatriculacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Plaza getPlaza() {
        return plaza;
    }
    
    public void setUsuario(Usuario usuario) throws ExcepcionAparcamiento{
    	if (this.usuario != null) {
        	throw new ExcepcionAparcamiento("No se puede cambiar el usuario de un vehiculo");
    	}
    	this.usuario = usuario;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj==null) {
            return false;
        } else if (obj==this) {
            return true;
        } else if (obj instanceof Vehiculo) {
            Vehiculo o = (Vehiculo) obj;
            return this.matricula.equals(o.matricula);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.matricula + " (" + this.marca + " : " + this.modelo + ") ";
    }
    
    

    
}
