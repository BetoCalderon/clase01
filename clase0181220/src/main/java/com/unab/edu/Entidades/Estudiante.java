
package com.unab.edu.entidades;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class Estudiante extends Persona{
    
    private int  idEstudiante ;
    private int Matricula;
    private String Usu;
    private String Pass;
    private int NIE;
    
}
