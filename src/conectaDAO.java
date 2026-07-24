
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB() throws ClassNotFoundException{
        Connection conn = null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            // URL configurada sem SSL e permitindo chave pública para evitar travamentos
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true", "root", "rootroot");
            
            String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true";
            conn = DriverManager.getConnection(url, "root", "rootroot");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
