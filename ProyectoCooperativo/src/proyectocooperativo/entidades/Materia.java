/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocooperativo.entidades;

import java.time.LocalDate;


public class Materia {
private int idMateria;
private String nombre;
private int AnioMateria;
private boolean estado;

    public Materia() {
    }

   

    public Materia(int idMateria, String nombre, int AnioMateria, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.AnioMateria = AnioMateria;
        this.estado = estado;
    }

    public Materia(String nombre, int AnioMateria, boolean estado) {
        this.nombre = nombre;
        this.AnioMateria = AnioMateria;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioMateria() {
        return AnioMateria;
    }

    public void setAnioMateria(int AnioMateria) {
        this.AnioMateria = AnioMateria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", AnioMateria=" + AnioMateria + ", estado=" + estado + '}';
    }




}
