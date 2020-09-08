/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;
 
import com.unab.edu.Entidades.Estudiante;
import com.unab.edu.conexionmysql.ConexionBd;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dayan
 */
public class CLSEstudiante {
    ConexionBd claseConectar = new ConexionBd();
        Connection conectar = claseConectar.retornarConexion();
    
    public  boolean LoguinEstudiante(String Usuario, String Pass){
    ArrayList<Estudiante> ListaUsuarioPass = new ArrayList<> ();
    
    try{
        CallableStatement Statement = conectar.prepareCall("call SP_S_LOGUINESTUDIANTE(?,?)");
        Statement.setString("pUsuario", Usuario);
        Statement.setString("pPass", Pass);
        ResultSet resultadoConsulta = Statement.executeQuery();
          
          while(resultadoConsulta.next()){
             Estudiante es = new Estudiante();
             
             es.setUsu(resultadoConsulta.getString("Usu"));
             es.setPass(resultadoConsulta.getString("Pass"));
             
             ListaUsuarioPass.add(es);
          }
          
          String usuarioBaseDatos = null;
          String passBaseDatos = null; 
          for(var iterador : ListaUsuarioPass){
              usuarioBaseDatos = iterador.getUsu();
              passBaseDatos = iterador.getPass();   
          }if(usuarioBaseDatos.equals(Usuario) && passBaseDatos.equals(Pass)){
              return true;
          }
          conectar.close();
          
            
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    return false;
    }
}
