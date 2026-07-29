/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws ClassNotFoundException{   
        conn = new conectaDAO().connectDB();
    
        if (conn == null){
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!");
            return; // Interrompe a execução para não dar o NullPointerException
        
        } String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            try {
                prep = conn.prepareStatement(sql);
                prep.setString(1, produto.getNome());
                prep.setInt(2, produto.getValor());
                prep.setString(3, produto.getStatus());

                prep.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
            } finally {
                try {
                    if (prep != null) prep.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    
    public void venderProduto (int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
                conn = new conectaDAO().connectDB();
                prep = conn.prepareStatement(sql);

                prep.setInt(1, id);

                prep.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (prep != null) prep.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }  
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws ClassNotFoundException {
        // SQL para buscar apenas produtos com o status "Vendido"
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = new conectaDAO().connectDB();
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            // Percorre os resultados retornados do banco
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
             // Adiciona o produto filtrado à lista de retorno
            listagem.add(produto);
        }
            
            // Percorre os resultados retornados do banco
        } catch (SQLException e) {
            JOptionPane.showMessageDialog (null, "Erro ao listar vendas: " + e.getMessage());
        } finally {

        try { 
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
               e.printStackTrace();
        }
        }
    return listagem;
    }
    
    // Método ListarProdutos, para a aparecer na listagem 
    public ArrayList<ProdutosDTO> listarProdutos() throws ClassNotFoundException {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        
        try {
            prep = conn.prepareStatement(sql) ;
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor")) ;
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos no DAO: " + erro.getMessage());
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close ();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane. showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return listagem;
    }
}