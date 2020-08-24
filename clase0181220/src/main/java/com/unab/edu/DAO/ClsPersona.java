/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

//el asterisco permite utilizar todas las importaciones que estan dentro de esa clase.
import com.unab.edu.Entidades.Persona;
import com.unab.edu.conexionmysql.ConexionBd;
import java.sql.*;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author BetoCalderon
 */
public class ClsPersona {

    ConexionBd claseConectar = new ConexionBd();
    Connection conectar = claseConectar.retornarConexion();

    //un aray list es un formato de matrices que van orden secuencial
    public ArrayList<Persona> MostrarPersona() {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            //estatemen es manejar consultas y manejar la base de datos
            CallableStatement Statement = conectar.prepareCall(" call SP_S_Persona()");
            ResultSet resultadoDeConsultado = Statement.executeQuery();
            while (resultadoDeConsultado.next()) {
                //java es sencible a mayusculas y  minusculas
                Persona persona = new Persona();
                persona.setIdPersona(resultadoDeConsultado.getInt("idPersona"));
                persona.setNombre(resultadoDeConsultado.getString("Nombre"));
                persona.setApellido(resultadoDeConsultado.getString("Apellido"));
                persona.setEdad(resultadoDeConsultado.getInt("Edad"));
                persona.setSexo(resultadoDeConsultado.getString("Sexo"));
                //llenando la coleccion de datos
                personas.add(persona);
            }
        } catch (Exception e) {
            //esta forma de mensaje solo se recomienda en modo de desarrollo
            JOptionPane.showMessageDialog(null, e);
        }
        return personas;
    }

    public void AgregarPersonas(Persona per) {
        try {
            //el signo de interrogacion significa que enviara parametros de java.
            CallableStatement Statement = conectar.prepareCall(" call SP_I_Persona(?,?,?,?)");
            Statement.setString("pNombre", per.getNombre());
            Statement.setString("pApellido", per.getApellido());
            Statement.setInt("pEdad", per.getEdad());
            Statement.setString("pSexo", per.getSexo());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Se registro con éxito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void BorrarPersonas(Persona per) {
        try {
            //el signo de interrogacion significa que enviara parametros de java.
            CallableStatement Statement = conectar.prepareCall(" call SP_D_Persona(?)");

            Statement.setInt("pIdPersona", per.getIdPersona());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Persona Eliminada");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ActulizarPersonas(Persona Persona) {
        try {
            //el signo de interrogacion significa que enviara parametros de java.
            CallableStatement Statement = conectar.prepareCall(" call SP_U_Persona(?,?,?,?,?)");
            Statement.setInt("pIdPersona", Persona.getIdPersona());
            Statement.setString("pNombre", Persona.getNombre());
            Statement.setString("pApellido", Persona.getApellido());
            Statement.setInt("pEdad", Persona.getEdad());
            Statement.setString("pSexo", Persona.getSexo());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "El registro se actulizó con éxito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
}
