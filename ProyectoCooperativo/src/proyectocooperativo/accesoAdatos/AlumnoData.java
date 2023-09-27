/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocooperativo.accesoAdatos;

import java.sql.Connection;
import java.sql.Date;
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


public class AlumnoData {
    
    private Connection con=null;
    
    public AlumnoData (){
    con= Conexion.getConexion();
    
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql="INSERT INTO alumno (dni,apellido,nombre,fechaNac,estado)"
                + "VALUES(? ,? ,? ,? ,? )";
                
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDni());
            ps.setString(2,alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Alumno grabado correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
        
    }
    public void modificarAlumno (Alumno alumno){
        String sql="UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNac=?"
                + "WHERE idAlumno=?";
        
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,alumno.getDni());
            ps.setString(2,alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5,alumno.getIdAlumno());
            int exito = ps.executeUpdate();
            if (exito==1){
                JOptionPane.showMessageDialog(null, "Alumno Modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno" );
        }
        
    }
    
    public void eliminarAlumno (int idAlumno){
        
        String sql="UPDATE alumno SET estado= 0 WHERE idAlumno=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            int exito = ps.executeUpdate();
            if (exito==1 ){
                JOptionPane.showMessageDialog(null, "Alumno Eliminado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno" );
        }
        
    }
   
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;
        String sql = "SELECT dni, apellido, nombre, fechaNac FROM alumno WHERE idAlumno = ? AND estado = 1";
        PreparedStatement ps = null;
       
        try {
           ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

                 if(rs.next()){
                    alumno=new Alumno();
                    alumno.setIdAlumno(id);
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setEstado(true);
               

            }else {
                     JOptionPane.showMessageDialog(null, "No existe el Alumno ");
                    ps.close();
                     
                 }
                     
            }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno ");
                        
                    }
        return alumno;
        }
        


    public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = null;
        String sql = "SELECT idAlumno,dni,apellido,nombre,fechaNac FROM alumno WHERE dni=? AND estado =1";
        

        try {
           PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    alumno=new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setEstado(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el Alumno ");
                    ps.close();
                }
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno ");
        }
        return alumno;
    }

    public List<Alumno> listarAlumnos(){
      String sql="SELECT idAlumno,dni,apellido,nombre,fechaNac FROM alumno WHERE estado =1";
       ArrayList<Alumno> alumnos=new ArrayList<>();
       
       PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Alumno alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setEstado(true);
                    
                    alumnos.add(alumno);
            }
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       return alumnos;
      
    }

       }
        



