package datos;

import java.sql.*;

public class Conexion {
    
    private static final String JDBC_URL= "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_ADMIN= "root";
    private static final String JDBC_PW= "admin";

    //CREAMOS POOL DE CONEXION
    
    
    //CREAR CONEXION A LA BASE DE DATOS

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_ADMIN, JDBC_PW);
    }
    
    //CERRAR METODOS RECIBE RESULTSET, ESTE TIENE TODOS LOS RESULTADOS DE LA BASE DE DATO.

    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }   
    }
    
    //CERRAR METODOS, RECIBE STATMENT, ESTE SE USA PARA EJECUTAR SENTENCIAS.
    public static void close(Statement s){
       try {
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    public static void close(Connection c){
       try {
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
}
