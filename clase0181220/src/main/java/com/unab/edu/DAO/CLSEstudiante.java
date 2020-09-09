
package com.unab.edu.DAO;
import com.unab.edu.conexiónmysql.ConexionBd;
import com.unab.edu.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CruzCalles
 */
public class ClsEstudiante {
    ConexionBd classConectar = new ConexionBd();
    Connection conectar = classConectar.retornarConexion();
    
    

    public boolean LoguinEstudiante(String usuario, String pass) {
        ArrayList<Estudiante> ListaUsuariosPass = new ArrayList<>();
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_LoguinEstudiante(?,?)");
            Statement.setString("pUsuario", usuario);
            Statement.setString("pPass", pass);
            ResultSet resultadoDeConsulta = Statement.executeQuery();
            while (resultadoDeConsulta.next()) {
                Estudiante es = new Estudiante();
                es.setUsu(resultadoDeConsulta.getString("USU"));
                es.setPass(resultadoDeConsulta.getString("PASS"));
                ListaUsuariosPass.add(es);
            }
            String usuariodebasedatos = "";
            String passdebasedatos = "";
            for (var iterador : ListaUsuariosPass) {
                usuariodebasedatos = iterador.getUsu();
                passdebasedatos = iterador.getPass();

            }
            if (usuariodebasedatos.equals(usuario) && passdebasedatos.equals(pass)) {
                return true;
            }
            conectar.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    public ArrayList<Estudiante> MostrarEstudiante() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_ESTUDIANTE");
            ResultSet resultadoConsulta = Statement.executeQuery();
            while (resultadoConsulta.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                estudiante.setMatricula(resultadoConsulta.getInt("Matricula"));
                estudiante.setIdPersona(resultadoConsulta.getInt("idPersona"));
                estudiante.setNombre(resultadoConsulta.getString("Nombre"));
                estudiante.setUsu(resultadoConsulta.getString("Usu"));
                estudiante.setPass(resultadoConsulta.getString("Pass"));
                estudiante.setNIE(resultadoConsulta.getInt("NIE"));
                
                
                estudiantes.add(estudiante);
            }
             conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return estudiantes;
    }
    public void AgregarEstudiante(Estudiante est) {

        try {
            CallableStatement Statement = conectar.prepareCall("call SP_I_ESTUDIANTE (?,?,?,?,?)");
            
            Statement.setInt("pMatricula", est.getMatricula());
            Statement.setInt("pidPersona", est.getIdPersona());
            Statement.setString("pUsu", est.getUsu());
            Statement.setString("pPass", est.getPass());
            Statement.setInt("pNIE", est.getNIE());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "¡Ingresado con Exito!");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void BorrarEstudiante(Estudiante est) {

        try {
            CallableStatement Statement = conectar.prepareCall("call SP_D_ESTUDIANTE(?)");
            Statement.setInt("PIdEstudiante", est.getIdEstudiante());
            Statement.execute();
            JOptionPane.showMessageDialog(null, "¡Datos Eliminados!");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ActualizarEstudiante(Estudiante est) {

        try {
            CallableStatement Statement = conectar.prepareCall("call SP_U_ESTUDIANTE(?,?,?,?,?,?)");
            Statement.setInt("PIdEstudiante", est.getIdEstudiante());
            Statement.setInt("PMatricula", est.getMatricula());
            Statement.setInt("PIdPersona", est.getIdPersona());
            Statement.setString("PUsu", est.getUsu());
            Statement.setString("PPass", est.getPass());
            Statement.setInt("PNie", est.getNIE());
            
            Statement.execute();
            JOptionPane.showMessageDialog(null, "Actualización de datos");
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
