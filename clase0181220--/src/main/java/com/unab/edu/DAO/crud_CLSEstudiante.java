/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Entidades.crud_Estudiante;
import com.unab.edu.conexionmysql.ConexionBd;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author BetoCalderon
 */
public class crud_CLSEstudiante {

    //conexion con la base de datos
    ConexionBd claseConectar = new ConexionBd();
    Connection conectar = claseConectar.retornarConexion();

    //lsita para mostrar todos los estudiantes
    public ArrayList<crud_Estudiante> MostrarEstudiante() {
        ArrayList<crud_Estudiante> Estudiante = new ArrayList<>();

        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_ESTUDIANTE()");
            ResultSet resultadoConsulta = Statement.executeQuery();

            while (resultadoConsulta.next()) {
                crud_Estudiante estudiante = new crud_Estudiante();
                estudiante.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                estudiante.setMatricula(resultadoConsulta.getInt("Matricula"));
                estudiante.setIdPersona(resultadoConsulta.getInt("idPersona"));
                estudiante.setUsuario(resultadoConsulta.getString("Usu"));
                estudiante.setPassword(resultadoConsulta.getString("pass"));
                estudiante.setNIE(resultadoConsulta.getInt("NIE"));

                Estudiante.add(estudiante);
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return Estudiante;
    }

    // proceso para hacer el registro del estudiante
    public void AgregarEstudiante(crud_Estudiante estudiante) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_I_ESTUDIANTE(?,?,?,?,?)");
            Statement.setInt("pMatricula", estudiante.getMatricula());
            Statement.setInt("pidPersona", estudiante.getIdPersona());
            Statement.setString("pUsu", estudiante.getUsuario());
            Statement.setString("pPass", estudiante.getPassword());
            Statement.setInt("pNIE", estudiante.getNIE());

            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante registrado");

            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //proceso para eliminar un estudiante 
    public void BorrarEstudiante(crud_Estudiante estudiante) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_D_ESTUDIANTE(?)");

            Statement.setInt("pidEstudiante", estudiante.getIdEstudiante());

            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante eliminado");

            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //metodo de actulizacion de estudiante.

    public void ActualizarEstudiante(crud_Estudiante estudiante) {
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_U_ESTUDIANTE(?,?,?,?,?,?)");
            Statement.setInt("pidEstudiante", estudiante.getIdEstudiante());
            Statement.setInt("pMatricula", estudiante.getMatricula());
            Statement.setInt("pidPersona", estudiante.getIdPersona());
            Statement.setString("pUsu", estudiante.getUsuario());
            Statement.setString("pPass", estudiante.getPassword());
            Statement.setInt("pNIE", estudiante.getNIE());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Estudiante actualizado");

            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
