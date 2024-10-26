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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "insert into produtos (nome, valor, status) values (?,?,?)";
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: Não foi possível cadastrar produto!");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "select * from produtos";
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");
                
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(id);
                produto.setNome(nome);
                produto.setValor(valor);
                produto.setStatus(status);
                listagem.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar itens");
        }
        return listagem;
    }
    
    
    
        
}

