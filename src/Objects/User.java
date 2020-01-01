/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Jorge
 */
public class User {
    
    private String nombre;
    private String apellido;
    private int carnet;
    private String contraseña;
    private String carnetS;
    
    public User(String nombre, String apellido, int carnet, String contraseña, String carnetS){
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        this.contraseña = contraseña;
        this.carnetS = carnetS;
        
    }

    public void setCarnetS(String carnetS) {
        this.carnetS = carnetS;
    }

    public String getCarnetS() {
        return carnetS;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getCarnet() {
        return carnet;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
}
