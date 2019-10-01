package test;

import domain.PersonaDTO;
import datos.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoPersonas {
    
    private static Connection conn;
    
    public static void main(String[] args) {
        
//        Connection Conn = null;
        
        try {
            
            int opcion;
            PersonaDaoJDBC pj = new PersonaDaoJDBC(conn);
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            Scanner teclado = new Scanner(System.in);
            System.out.println("1.- Ver Tabla");
            System.out.println("2.- Agregar persona");
            System.out.println("3.- Actualizar persona");
            System.out.println("4.- Eliminar persona");
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1:
                    verTabla();
                    break;
                case 2:
                    insertarDatos();
                    break;
                case 3:
                    actualizarDatos();
                    break;
                case 4:
                    eliminarFila();
                    break;      
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
}
    public static List<PersonaDTO> verTabla() {
        
        try {
            List<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
            PersonaDAO p = new PersonaDaoJDBC();
            listaPersonas =  p.select();
            
            for (PersonaDTO listaPersona : listaPersonas) {
                System.out.println(listaPersona.toString());
            }
            
            return listaPersonas;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
     }
    
    public static void actualizarDatos(){
        
        try {
            Scanner teclado = new Scanner(System.in);
            PersonaDAO p = new PersonaDaoJDBC();
            System.out.println("Debes ingresar los datos de la persona a actualizar");
            System.out.println("ID: ");
            Integer id = teclado.nextInt();
            System.out.println("Nombre: ");
            String nombre = teclado.next();
            System.out.println("Apellido: ");
            String apellido = teclado.next();
            System.out.println("Email: ");
            String email = teclado.next();
            System.out.println("Telefono: ");
            String telefono = teclado.next();
            
            PersonaDTO p1 = new PersonaDTO(id, nombre, apellido, email, telefono);
            int rows = p.update(p1);
            conn.commit();
            
            System.out.println("Columnas afectadas " + rows);
         
            verTabla();
        } catch (SQLException ex) {
            try {
                conn.rollback();
                ex.printStackTrace(System.out);
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
        
    }
    
    public static void insertarDatos(){
        
        try {
            Scanner teclado = new Scanner(System.in);
            PersonaDAO p = new PersonaDaoJDBC();
            System.out.println("Debes ingresar los datos de la persona a agregar");
            System.out.println("Nombre: ");
            String nombre = teclado.next();
            System.out.println("Apellido: ");
            String apellido = teclado.next();
            System.out.println("Email: ");
            String email = teclado.next();
            System.out.println("Telefono: ");
            String telefono = teclado.next();
            
            PersonaDTO p1 = new PersonaDTO(nombre, apellido, email, telefono);
            int rows = p.insertar(p1);
            conn.commit(); //EL COMMIT TE GUARDA LOS DATOS A LA CONEXION
            System.out.println("Columnas afectadas " + rows);
            
            verTabla();
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                conn.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
        
    }

    private static void eliminarFila() {
        
        try {
            Scanner teclado = new Scanner(System.in);
            PersonaDAO p = new PersonaDaoJDBC();
            System.out.println("Debes ingresar el id de la persona a eliminar");
            System.out.println("ID: ");
            Integer id = teclado.nextInt();
            
            int rows = p.eliminarRegistro(id);
            conn.commit();
            System.out.println("Columnas afectadas " + rows);
            
            verTabla();
        } catch (SQLException ex) {
            try {
                conn.rollback(); //EN CASO DE QUE SE EJECUTE UN ERROR, NO SE GUARDARA NADA.
                ex.printStackTrace(System.out);
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

}

