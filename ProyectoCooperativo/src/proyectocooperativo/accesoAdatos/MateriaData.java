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
import proyectocooperativo.entidades.Materia;

public class MateriaData {

    private Connection con=null;
   private AlumnoData ad=new AlumnoData();
   
  
    
    
    public MateriaData(){
    con= Conexion.getConexion();
}
    
 public void guardarMateria(Materia materia){
     
      String sql="INSERT INTO materia (nombre, AnioMateria, estado)"
                + "VALUES(? ,? ,?  )";
                
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Materia grabado correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }
     
    
    }    
    
    public void modificarMateria (Materia materia){
    String sql="UPDATE materia SET Nombre=?, AnioMateria=?"
                + "WHERE idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2,(materia.getAnioMateria()));
                        ps.setInt(3, materia.getIdMateria());
            int exito = ps.executeUpdate();
            if (exito ==1){
                JOptionPane.showMessageDialog(null,"Materia Modificada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia4");
        }
 
    }
    
     public void eliminarMateria (int idMateria){
        
        String sql="UPDATE materia SET estado= 0 WHERE idMateria=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int exito = ps.executeUpdate();
            if (exito==1 ){
                JOptionPane.showMessageDialog(null, "Materia Eliminada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Materia" );
        }
        
    }
    
     public List<Materia> listarMateria(){
      String sql="SELECT idMateria,nombre,AnioMateria FROM materia WHERE estado =1";
       ArrayList<Materia> materias=new ArrayList<>();
       
       PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("AnioMateria"));
                 materia.setEstado(true);
                    
                    
                    
                    materias.add(materia);
            }
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       return materias;
      
    }
     public Materia buscarMateria(int id) {
        Materia materia = null;
        String sql = "SELECT Nombre, AnioMateria FROM materia WHERE idMateria = ? AND estado = 1";
        
       
        try {
           PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

                 if(rs.next()){
                    materia =new Materia();
                    materia.setIdMateria(id);
                    materia.setNombre(rs.getString("Nombre"));
                    materia.setAnioMateria(rs.getInt("AnioMateria"));
                    materia.setEstado(true);
                  
            }else {
                     JOptionPane.showMessageDialog(null, "No existe la MAteria ");
                    ps.close();
                    
                 }
                    
            }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia ");
                        
                    }
        return materia;
        }
     public void modificarMateria2(Materia materia) {
    String sql = "UPDATE materia SET Nombre=?, AnioMateria=?, estado=? WHERE idMateria=?";
    try {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());
            int filasModificadas = ps.executeUpdate();
            if (filasModificadas > 0) {
                System.out.println("Materia modificada exitosamente.");
            } else {
                System.out.println("No se encontró la materia a modificar.");
            }
            // Cierra la PreparedStatement cuando ya no se necesita.
        }
    } catch (SQLException e) {
    }
}

     
}