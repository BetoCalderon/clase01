
package com.unab.edu.conexiónmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class ConexionBd {

    private Connection conexion;

    public ConexionBd() {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/clase1", "root", "root");
            System.out.println("Conectado a la Base de Datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de coneccion" + e);
        }
    }

    public Connection retornarConexion() {

        return conexion;
    }
}
