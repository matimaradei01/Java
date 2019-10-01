package domain;

public class UsuarioDTO {
    
    private int idusuario;
    private String username;
    private String password;

    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioDTO(int idusuario, String username, String password) {
        this.idusuario = idusuario;
        this.username = username;
        this.password = password;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", username=" + username + ", password=" + password + '}';
    }
    
    
    
}
