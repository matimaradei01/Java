package datos;

import domain.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDAO {
    
     List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
    //CREAMOS LAS QUERYS
    private static final String SQL_SELECT = "SELECT * FROM usuario ";
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username=?, password=? WHERE idusuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE idusuario = ?";
    private Connection conexionTransaccional = null;

    public UsuarioDaoJDBC() {
    }
    
    public UsuarioDaoJDBC(Connection conn) {
        this.conexionTransaccional = conn;
    }
    
    
    public List<UsuarioDTO> select () throws SQLException{
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                UsuarioDTO u1 = new UsuarioDTO( rs.getInt("idusuario"),
                                          rs.getString("username"),
                                          rs.getString("password") );
                                         
            
                usuarios.add(u1);
            }
    
        }finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(ps);
    }
        return usuarios;  
    }
    
    public int insertar(UsuarioDTO p) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getUsername());
            ps.setString(2, p.getPassword());

            
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
    public int update(UsuarioDTO u) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getUsername());
            ps.setInt(3, u.getIdusuario());

            
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
