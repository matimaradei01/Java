package datos;

import domain.PersonaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoJDBC implements PersonaDAO{
    
    List<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
    //CREAMOS LAS QUERYS
    private static final String SQL_SELECT = "SELECT * FROM persona ";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE idpersona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idpersona = ?";
    private Connection conexionTransaccional = null;

    public PersonaDaoJDBC() {
    }
    
    public PersonaDaoJDBC(Connection conn) {
        this.conexionTransaccional = conn;
    }
    
    public List<PersonaDTO> select () throws SQLException{
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                PersonaDTO p1 = new PersonaDTO( rs.getInt("idpersona"),
                                          rs.getString("nombre"),
                                          rs.getString("apellido"),
                                          rs.getString("email"), 
                                          rs.getString("telefono") );
            
                listaPersonas.add(p1);
            }
    
        }finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(ps);
    }
        return listaPersonas;  
    }
    
    public int insertar(PersonaDTO p) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            
            System.out.println("Ejecutando query.. " + SQL_INSERT);
            rows = ps.executeUpdate(); //devolverá el número correcto de filas actualizadas en una operación MERGE.
            System.out.println("Filas modificadas " + rows  );

            
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
    }
    //ACTUALIZAR TABLA
    public int update(PersonaDTO p) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getId_persona());

            
            System.out.println("Ejecutando query.. " + SQL_UPDATE);
            rows = ps.executeUpdate(); //devolverá el número correcto de filas actualizadas en una operación MERGE.
            System.out.println("Filas modificadas " + rows  );

            
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
    }
    
    public int eliminarRegistro(Integer id) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);

            System.out.println("Ejecutando query.. " + SQL_DELETE);
            rows = ps.executeUpdate(); //devolverá el número correcto de filas actualizadas en una operación MERGE.
            System.out.println("Registros eliminados " + rows  );

        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        return rows;
        
    }
    
    
}
