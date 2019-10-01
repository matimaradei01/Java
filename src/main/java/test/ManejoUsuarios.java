package test;

import datos.Conexion;
import datos.UsuarioDAO;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoUsuarios {
    
         private static Connection conn;
    
    public static void main(String[] args) {
        
//        Connection Conn = null;
        
        try {
            
            int opcion;
            UsuarioDaoJDBC pj = new UsuarioDaoJDBC(conn);
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            Scanner teclado = new Scanner(System.in);
            System.out.println("1.- Ver Tabla");
            System.out.println("2.- Agregar usuario");
            System.out.println("3.- Actualizar usuario");
            System.out.println("4.- Eliminar usuario");
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
    public static List<UsuarioDTO> verTabla() {
        
        try {
            List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
            UsuarioDAO p = new UsuarioDaoJDBC();
            listaUsuarios =  p.select();
            
            for (UsuarioDTO usuarios : listaUsuarios ) {
                System.out.println(usuarios.toString());
            }
            
            return listaUsuarios;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
     }
    
    public static void actualizarDatos(){
        
        try {
            Scanner teclado = new Scanner(System.in);
            UsuarioDAO p = new UsuarioDaoJDBC();
            System.out.println("Debes ingresar los datos del usuario a actualizar");
            System.out.println("ID: ");
            Integer id = teclado.nextInt();
            System.out.println("Username: ");
            String username = teclado.next();
            System.out.println("Password: ");
            String password = teclado.next();
            
            UsuarioDTO p1 = new UsuarioDTO(id, username, password);
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
            UsuarioDAO p = new UsuarioDaoJDBC();
            System.out.println("Debes ingresar los datos del usuario a agregar");
            System.out.println("Username: ");
            String username = teclado.next();
            System.out.println("Password: ");
            String password = teclado.next();
           
            
            UsuarioDTO p1 = new UsuarioDTO(username, password);
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
            UsuarioDAO p = new UsuarioDaoJDBC();
            System.out.println("Debes ingresar el id del usuario a eliminar");
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

