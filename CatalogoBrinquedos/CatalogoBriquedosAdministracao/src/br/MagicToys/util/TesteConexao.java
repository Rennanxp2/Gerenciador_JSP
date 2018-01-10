package br.MagicToys.util;

import java.sql.Connection;
import java.sql.SQLException;

import br.MagicToys.dao.BrinquedoDAO;

public class TesteConexao {

	public static void main(String[] args) {
		try {
			Connection con = ConnectionFactory.getConnection();
			BrinquedoDAO  dao = new BrinquedoDAO(1);
			System.out.println(dao.ExcluirBrinquedo());
			dao.ListaDeBrinquedosPorCategoria("asda");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
