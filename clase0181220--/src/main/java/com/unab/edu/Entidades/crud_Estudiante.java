/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;

/**
 *
 * @author BetoCalderon
 */
@Data
public class crud_Estudiante {
    protected int idEstudiante;
    protected int Matricula;
    protected int idPersona;
    protected String Usuario;
    protected String Password;
    protected int NIE;
    
}
