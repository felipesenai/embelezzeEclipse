package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class FabricaConexaoAgenda {
	
	private static final String URL = "jdbc:mysql://localhost:3306/embelezze_db";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	
	public static Connection conectar() throws SQLException{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
}
