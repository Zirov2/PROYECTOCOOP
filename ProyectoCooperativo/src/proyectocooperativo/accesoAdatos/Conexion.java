/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*package proyectocooperativo.accesoAdatos;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

   private static final String URL ="jdbc:mysql://localhost/";
   private static final String DB="Universidadulp";
   private static final String USUARIO="root";
   private static final String PASSWORD= "";
   private static Conexion connection;
   
    
    private Conexion(){}
    
    public static Connection getConexion(){
        
        if (connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection = DriverManager.getConnection(URL+DB,USUARIO,PASSWORD);
                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Error al cargar el driver" );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al conectarse" );
            }
            
        }
        return connection;
    }
}
*/
package proyectocooperativo.accesoAdatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DB = "Universidadulp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Constructor privado para evitar instanciación externa
    private Conexion() {
    }

    // Método para obtener una conexión a la base de datos
    public static Connection getConexion() {
        if (connection == null) {
            try {
                // Cargar el controlador de MySQL (Driver)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión a la base de datos
                connection = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);
                JOptionPane.showMessageDialog(null,"Conectado");
            } catch (ClassNotFoundException ex) {
                // Si no se puede cargar el controlador
                JOptionPane.showMessageDialog(null, "Error al cargar el driver: " + ex.getMessage());
            } catch (SQLException ex) {
                // Si hay un error de SQL al conectarse a la base de datos
                JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos: " + ex.getMessage());
            }
        }
        return connection;
    }
}
