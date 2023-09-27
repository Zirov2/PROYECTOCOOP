/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocooperativo.accesoAdatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectocooperativo.entidades.Alumno;
import proyectocooperativo.entidades.Inscripcion;
import proyectocooperativo.entidades.Materia;

public class InscripcionData {

    private Connection con = null;
    private MateriaData md = new MateriaData();
    private AlumnoData ad = new AlumnoData();

    public InscripcionData() {
        con = Conexion.getConexion();

    }

    public void guardarInscripcion(Inscripcion insc) {

        String sql = "INSERT INTO inscripcion (idAlumno,idMateria,nota) VALUES (?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, insc.getAlumno().getIdAlumno());
                ps.setInt(2, insc.getMateria().getIdMateria());
                ps.setDouble(3, insc.getNota());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    insc.setIdInscripcion(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Inscripcion Registrada");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }

    }

    public void actualizarNota (int idAlumno, int idMateria, double nota){
        String sql="UPDATE inscripcion SET nota=? WHERE idAlumno=? and idMateria=?";
        
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1,nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas=ps.executeUpdate();
            if (filas>0){
            JOptionPane.showMessageDialog(null, "Nota Actualizada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        
        
    }
  
    public void borrarInscripcion(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno=? and idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion Eliminada correctamente");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }


    }
    
     public List<Inscripcion> obtenerInscripciones(){
       
       ArrayList<Inscripcion> cursadas= new ArrayList<>();
       String sql="SELECT * FROM inscripcion";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alu=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);
                
            }
                ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Inscripcion");
        }
       
       return cursadas;
       
   } 
     
      public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
       
       ArrayList<Inscripcion> cursadas= new ArrayList<>();
       String sql="SELECT * WHERE idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alu=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);
                
            }
                ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Inscripcion");
        }
       
       return cursadas;
       
   } 
     
     public List<Materia> obtenerMateriasCursadas(int idAlumno){
         ArrayList<Materia> materias=new ArrayList<>();
         String sql= "SELECT inscripcion.idMateria,Nombre,AnioMateria FROM inscripcion,"
                 +"materia WHERE inscripcion.idMateria= materia.idMateria"+
                 "AND inscripcion.idAlumno = ?";
         
         PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
             ps.setInt(1, idAlumno);
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 Materia materia=new Materia();
                 materia.setIdMateria(rs.getInt("idMateria"));
                 materia.setNombre(rs.getString("Nombre"));
                 materia.setAnioMateria(rs.getInt("AnioMateria"));
                 
                 
             }
             ps.close();
             
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Inscripcion");
        }
        return materias;
         
         
     }
     public List<Materia>obtenerMateriasNoCursadas(int idAlumno){
         ArrayList<Materia> materias=new ArrayList<>();
         
       String sql= "SELECT * FROM materia WHERE estado =1 AND idMateria not in"
               + "(SELECT idMateria FROM inscripcion WHERE idAlumno=?)";
       
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,idAlumno);
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 Materia materia=new Materia();
                 materia.setIdMateria(rs.getInt("idMateria"));
                 materia.setNombre(rs.getString("Nombre"));
                 materia.setAnioMateria(rs.getInt("AnioMateria"));
                 materias.add(materia);
                 
             }
             ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Inscripcion");
        }
         
         return materias;
     }
   
    public List<Alumno> obtenerAlumnosXMateria(int idMateria) {
        ArrayList<Alumno> alumnosMateria = new ArrayList<>();
        String sql = "SELECT a.idAlumno,dni,apellido, nombre,fechaNac,estado"
                + "FROM inscripcion i,alumno a WHERE i.idAlumno= a.idAlumno AND idMateria =? AND a.estado=1";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnosMateria.add(alumno);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }

        return alumnosMateria;
    }

}
