package datos;

import domain.PersonaDTO;
import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
    
    public List<PersonaDTO> select () throws SQLException;
    public int insertar(PersonaDTO p) throws SQLException;
    public int update(PersonaDTO p) throws SQLException;
    public int eliminarRegistro(Integer id) throws SQLException;
    
}
