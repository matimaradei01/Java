package datos;

import domain.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    
      public List<UsuarioDTO> select () throws SQLException;
    public int insertar(UsuarioDTO p) throws SQLException;
    public int update(UsuarioDTO p) throws SQLException;
    public int eliminarRegistro(Integer id) throws SQLException;
    
}
