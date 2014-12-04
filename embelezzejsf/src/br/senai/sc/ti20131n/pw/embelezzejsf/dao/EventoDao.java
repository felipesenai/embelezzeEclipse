package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.modelo.Evento;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class EventoDao {

	PreparedStatement pstm;
	ResultSet rs;
	private Connection conexao;
	private EntityManager entityManager;

	public EventoDao() {
		entityManager = Util.getEntityManager();
	}

	public EventoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void salvar(Evento evento) throws SQLException {
		String sql = "INSERT INTO Evento(Titulo, InicioEvento, FimEvento, DescEvento, StatusEvento, cliente_ID)"
				   + "VALUES(?,?,?,?,?,?)";
		conexao = FabricaConexaoAgenda.conectar();
		pstm = conexao.prepareStatement(sql);

		pstm.setString(1, evento.getTitulo());
		pstm.setObject(2, evento.getInicio());
		pstm.setObject(3, evento.getFim());
		//pstm.setDate(2, new java.sql.Date(evento.getInicio().getTime()));
		//pstm.setDate(3, new java.sql.Date(evento.getFim().getTime()));
		pstm.setString(4, evento.getDescricao());
		pstm.setBoolean(5, evento.isStatus());
		pstm.setLong(6, evento.getCliente().getID());

		pstm.executeUpdate();

	}

	public void atualizar(Evento evento) throws SQLException {
		String sql = "UPDATE Evento SET Titulo=?, InicioEvento=?, FimEvento=?, DescEvento=?, StatusEvento=?, cliente_ID=?"
				   + " WHERE ID_Evento=?";

		conexao = FabricaConexaoAgenda.conectar();
		pstm = conexao.prepareStatement(sql);

		pstm.setString(1, evento.getTitulo());
		pstm.setObject(2, evento.getInicio());
		pstm.setObject(3, evento.getFim());
		pstm.setString(4, evento.getDescricao());
		pstm.setBoolean(5, evento.isStatus());
		pstm.setLong(6, evento.getCliente() != null ? evento.getCliente().getID():null);
		pstm.setLong(7, evento.getId());

		pstm.executeUpdate();

	}

	public List<Evento> listarEventos() throws SQLException {
		List<Evento> eventos = new ArrayList<Evento>();
		String sql = "SELECT * FROM Evento e LEFT JOIN Cliente c on e.cliente_ID = c.ID";

		conexao = FabricaConexaoAgenda.conectar();
		pstm = conexao.prepareStatement(sql);

		rs = pstm.executeQuery();

		while (rs.next()) {
			Evento e = new Evento();
			e.setId(rs.getLong(1));
			e.setTitulo(rs.getString(2));
			e.setInicio(rs.getTimestamp(3));
			e.setFim(rs.getTimestamp(4));
			e.setStatus(rs.getBoolean(5));
			e.setDescricao(rs.getString(6));

			Cliente c = new Cliente();
			c.setID(rs.getLong(7));
			c.setNome(rs.getString(11));
			e.setCliente(c);
			eventos.add(e);
		}
		return eventos;
	}

}
