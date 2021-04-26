/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;


public class Sintactico {
    
    /*EXPRESIONES REGULARES*/
    
    String DIG = "[\\d]+";
    String GUION = "(-|_)";
    String IDENT = "[a-zA-Z_][a-zA-Z0-9_]*";
    String TIPO = "(int|double|txt|boolean)";
    String MAIN = "main\\s+\\{";
    String INIT = "" + TIPO + "\\s+" + IDENT + "\\s+;";
    String CONST_INT = "[0-9]+";
    String CONST_BOOLEAN = "(true|false)";
    String CONST_TXT = "\".*\"";
    String CONST_DOUBLE = "" + CONST_INT + "\\." + CONST_INT;
    String CONST = "(" + CONST_BOOLEAN + "|" + CONST_DOUBLE + "|" + CONST_INT + "|" + CONST_TXT + ")";
    String OPER = "(" + IDENT + "|" + CONST + ")";
    String ARIT = "(\\+|\\-|\\*|\\/|\\^)";
    String REL = "(<|>|<=|>=|==|<>)";
    String ESTRUC = "(if|else|frciclo|whciclo|main)";
    String COND = "" + OPER + "\\s+" + REL + "\\s+" + OPER;
    String IF = "^if\\s+\\(\\s+" + COND + "\\s+\\)\\s+\\{$";
    String ELSE = "\\}\\s+else\\s+\\{\\s+";
    String FRCICLO = "^frciclo\\s+\\(\\s+" + IDENT + "\\s+;\\s+" + COND + "\\s+;\\s+" + IDENT + "\\+\\+\\s+\\)\\s+\\{$";
    String WHCICLO = "whciclo\\s+\\(\\s+" + COND + "\\s+\\)\\s+\\{";
    String ASIGN = "" + IDENT + "\\s+=\\s+(\\s+"+ OPER +"|" + OPER + "\\s+" + ARIT + "\\s+" + OPER + "\\s+);";
    String SCAN = "scan\\s+\\(\\s+" + IDENT + "\\s+\\)\\s+;";
    String PRINT = "print\\s+\\(\\s+(" + IDENT + "|" + CONST + ")\\s+\\)\\s+;";
    String LLAVE_C = "(})";
    
    public void run() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            /*RUTA DEL ARCHIVO (el caso de estudio esta en programa.srg)*/
            archivo = new File("programa.srg");
            
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            // Lectura del fichero
            String linea;
            int line = 1;
            while ((linea = br.readLine()) != null) {
                if (linea.matches(IF)) {
                    System.out.println("linea: "+line+" Estructura if detectada");
                } else if (linea.matches(MAIN)) {
                    System.out.println("linea: "+line+" inicio detectado");
                } else if (linea.matches(FRCICLO)) {
                    System.out.println("linea: "+line+" Estructura frciclo detectada");
                } else if (linea.matches(WHCICLO)) {
                    System.out.println("linea: "+line+" Estructura whciclo detectada");
                } else if (linea.matches(PRINT)) {
                    System.out.println("linea: "+line+" Estructura print detectada");
                } else if (linea.matches(SCAN)) {
                    System.out.println("linea: "+line+" Estructura scan detectada");
                } else if (linea.matches(INIT)) {
                    System.out.println("linea: "+line+" Declaracion de variable");
                } else if (linea.matches(ASIGN)) {
                    System.out.println("linea: "+line+" Asignacion de variable");
                } else if (linea.matches(ELSE)) {
                    System.out.println("linea: "+line+" Estructura else detectada");
                } else if (linea.matches(LLAVE_C)) {
                    System.out.println("linea: "+line+" Llave  de cierre detectada");
                } else {
                    System.err.println("Linea desconocida: " + line);
                }
                
                line ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
