package br.MagicToys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.MagicToys.util.ConnectionFactory;
import br.MagicToys.model.Brinquedo;


public class BrinquedoDAO {
	private Brinquedo brinquedo;
	private int CodigoBrinquedo;
	private Connection con;
	private String sql ="";
	private PreparedStatement stmt;
	private ResultSet rs;
	public BrinquedoDAO () throws SQLException
	{
		//1 OBTER A CONEXAO
		con = ConnectionFactory.getConnection();
	}
	public BrinquedoDAO (Brinquedo brinquedo) throws SQLException
	{
		//1 OBTER A CONEXAO
		con = ConnectionFactory.getConnection();
		this.brinquedo = brinquedo;
	}
	public BrinquedoDAO (int codigoBrinquedo) throws SQLException
	{
		//1 OBTER A CONEXAO
		con = ConnectionFactory.getConnection();
		this.CodigoBrinquedo = codigoBrinquedo;
	}
	public boolean InserirBrinquedo()
	{
		try {
			//2 PREPARAR O COMANDO DE CONSULTA SQL PARA EXECUCAO
			sql = "INSERT INTO Brinquedo(Descricao, Marca, Categoria, Imagem, Valor, Detalhes) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, brinquedo.getDescricao());
			stmt.setString(2, brinquedo.getMarca());
			stmt.setString(3, brinquedo.getCategoria());
			stmt.setString(4, brinquedo.getImagem());
			stmt.setDouble(5, brinquedo.getValor());
			stmt.setString(6, brinquedo.getDetalhes());
			//3 EXECUTAR O COMANDO SQL NO BANCO E SE POSSIVEL, OBTER RESULTADO
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean AtualizarBrinquedo()
	{
		try {
			ConnectionFactory.getConnection();
			sql = "UPDATE Brinquedo SET Descricao=?, Marca=?, Categoria=?, Imagem=CASE WHEN '" + brinquedo.getImagem() + "' = '' THEN Imagem ELSE ? END, Valor=?, Detalhes=? WHERE Codigo=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, brinquedo.getDescricao());
			stmt.setString(2, brinquedo.getMarca());
			stmt.setString(3, brinquedo.getCategoria());
			stmt.setString(4, brinquedo.getImagem());
			stmt.setDouble(5, brinquedo.getValor());
			stmt.setString(6, brinquedo.getDetalhes());
			stmt.setInt(7, brinquedo.getCodigo());
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}

	}
	public boolean ExcluirBrinquedo() 
	{
		try {
			if (this.CodigoBrinquedo == 0)
			{
				return false;
			}
			else
			{
				
				sql = "DELETE FROM Brinquedo WHERE Codigo=?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, CodigoBrinquedo);
				
				stmt.executeUpdate();
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	public List<Brinquedo> ListaDeBrinquedosPorCategoria(String categoria){
		List<Brinquedo> lista = new ArrayList<Brinquedo>();
		try {
			
			sql = "SELECT * FROM Brinquedo WHERE Categoria = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categoria);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				int codigo = rs.getInt("Codigo");
				String descricao = rs.getString("Descricao");
				String marca = rs.getString("Marca");
				String imagem = rs.getString("Imagem");
				double valor = rs.getDouble("Valor");
				String detalhes = rs.getString("Detalhes");
				lista.add(new Brinquedo(codigo,descricao,marca,categoria,imagem,valor,detalhes));
			}
			return lista;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} 
	}
	public Brinquedo AcharBrinquedo(){
		try {
			
			sql = "SELECT * FROM Brinquedo WHERE codigo = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, CodigoBrinquedo);
			rs = stmt.executeQuery();
			Brinquedo brinquedo;
			if (rs.next())
			{
				String descricao = rs.getString("Descricao");
				String marca = rs.getString("Marca");
				String categoria = rs.getString("Categoria");
				String imagem = rs.getString("Imagem");
				double valor = rs.getDouble("Valor");
				String detalhes = rs.getString("Detalhes");
				brinquedo = new Brinquedo(CodigoBrinquedo,descricao,marca,categoria,imagem,valor,detalhes);
				return brinquedo;
			}
			else
				return null;
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} 
	}
	public List<Brinquedo> ListaDeBrinquedos(){
		List<Brinquedo> lista = new ArrayList<Brinquedo>();
		try {
			
			sql = "SELECT * FROM Brinquedo";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				int codigo = rs.getInt("Codigo");
				String descricao = rs.getString("Descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("Marca");
				String imagem = rs.getString("Imagem");
				double valor = rs.getDouble("Valor");
				String detalhes = rs.getString("Detalhes");
				lista.add(new Brinquedo(codigo,descricao,marca,categoria,imagem,valor,detalhes));
			}
			return lista;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} 
	}
}
