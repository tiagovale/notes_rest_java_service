package br.notas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.notas.config.BDConfig;
import br.notas.model.Nota;

public class NotaDAO {
	public List<Nota> listarNotas() throws Exception {
		List<Nota> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM TB_NOTA";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Nota nota = new Nota();
			nota.setId(rs.getLong("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));

			lista.add(nota);
		}

		return lista;
	}

	public Nota buscarNotaPorId(Long idNota) throws Exception {
		Nota nota = null;

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM TB_NOTA WHERE ID_NOTE = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setLong(1, idNota);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getLong("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
		}

		return nota;
	}

	public void addNota(Nota nota) throws Exception {
		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();

		String sql = "INSERT INTO TB_NOTA(TITULO, DESCRICAO) VALUES(?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();
	}

	public void editarNota(Nota nota, Long idNota) throws Exception {
		Connection conexao = BDConfig.getConnection();

		String sql = "UPDATE TB_NOTA SET TITULO = ?, DESCRICAO = ? WHERE ID_NOTE = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setLong(3, idNota);
		statement.execute();
	}

	public void removerNota(Long idNota) throws Exception {
		Connection conexao = BDConfig.getConnection();

		String sql = "DELETE FROM TB_NOTA WHERE ID_NOTE = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setLong(1, idNota);
		statement.execute();
	}

}
