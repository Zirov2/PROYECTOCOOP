/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocooperativo;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import proyectocooperativo.accesoAdatos.AlumnoData;
import proyectocooperativo.accesoAdatos.Conexion;
import proyectocooperativo.accesoAdatos.InscripcionData;
import proyectocooperativo.accesoAdatos.MateriaData;
import proyectocooperativo.entidades.Alumno;
import proyectocooperativo.entidades.Inscripcion;
import proyectocooperativo.entidades.Materia;

/**
 *
 * @author Octavio
 */
public class ProyectoCooperativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       /* Connection con= Conexion.getConexion();
         Alumno Juan=new Alumno(1,12312312,"Luna","Juan Pedro",LocalDate.of(1980, 4, 25),true);
        AlumnoData alu=new AlumnoData();
       alu.guardarAlumno(Juan);
        alu.modificarAlumno(Juan);
        alu.eliminarAlumno(1);
         Alumno alumnoEncontrado = alu.buscarAlumno(4);
         System.out.println("dni"+alumnoEncontrado.getDni());
        System.out.println("apellido"+alumnoEncontrado.getApellido());
       */ 
        
        
       /* AlumnoData alu= new AlumnoData();
        for (Alumno alumno:alu.listarAlumnos())
            System.out.println(alumno.getApellido());
          System.out.println(alumno.getNombre()); 
          System.out.println(alumno.getDni);
*/
       /* Materia mat= new Materia("Matematica",2,true);
        Materia mat2= new Materia("BIOLOGIA",4,true);
        MateriaData mats=new MateriaData();
        mats.guardarMateria(mat);
        mats.guardarMateria(mat2);
        mats.modificarMateria2(mat);
        System.out.println(mats.listarMateria());
        */


         AlumnoData ad=new AlumnoData();
       MateriaData md=new MateriaData();
       InscripcionData id=new InscripcionData();
       
      
       
       Alumno mariano=ad.buscarAlumno(5);
        System.out.println(mariano);
       Materia mate=md.buscarMateria(8);
       Inscripcion insc=new Inscripcion(mariano,mate,4);
      // id.guardarInscripcion(insc);
      //id.actualizarNota(5, 8, 9);
     // id.borrarInscripcion(5, 8);
     /* for (Inscripcion inscripcion:id.obtenerInscripciones()){
          System.out.println("id"+inscripcion.getIdInscripcion());
          System.out.println("Apellido"+inscripcion.getAlumno().getApellido());
          System.out.println("Materia"+inscripcion.getMateria().getNombre());
      }  */
     
    // for (Materia materia:id.obtenerMateriasCursadas(5)){
    //     System.out.println(materia.getNombre());
     //}
     //for(Materia materia:id.obtenerMateriasNoCursadas(5)){
       //  System.out.println("nombre"+materia.getNombre());
     //}
   

      

   }

    
}