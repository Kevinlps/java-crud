package principal.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import principal.Conexao;

import principal.Medico;

public class MedicoDAO {

	private Connection conexao;

	public MedicoDAO() {

		try {

			conexao = Conexao.conectar();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Medico buscarMedico(int medicoId) {

		Medico medico = null;

		String sql = "SELECT * FROM medicos WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, medicoId);

			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {

				medico = new Medico();

				medico.setId(resultado.getInt("id"));

				medico.setNome(resultado.getString("nome"));

				medico.setEspecialidade(resultado.getString("especialidade"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return medico;

	}

	public void criarMedico(Medico Medico) {

		String sql = "INSERT INTO Medicos (nome, especialidade) VALUES (?, ?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, Medico.getNome());

			stmt.setString(2, Medico.getEspecialidade());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public List<Medico> listarMedicos() {

		List<Medico> Medicos = new ArrayList<>();

		String sql = "SELECT * FROM Medicos";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {

				Medico Medico = new Medico();

				Medico.setId(resultado.getInt("id"));

				Medico.setNome(resultado.getString("nome"));

				Medico.setEspecialidade(resultado.getString("especialidade"));

				Medicos.add(Medico);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return Medicos;

	}

	public void excluirMedico(int id) {

		String sql = "DELETE FROM Medicos WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void atualizarMedico(Medico Medico) {

		String sql = "UPDATE Medicos SET nome = ?, especialidade = ? WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, Medico.getNome());

			stmt.setString(2, Medico.getEspecialidade());

			stmt.setInt(3, Medico.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void fecharConexao() {

		try {

			if (conexao != null && !conexao.isClosed()) {

				conexao.close();

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}