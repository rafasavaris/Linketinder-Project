package linketinder.data

import linketinder.dao.ConexaoBD
import linketinder.model.Vaga
import linketinder.model.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagaData {
    void cadastrar(Vaga vaga) {
        String sqlVaga = """
            INSERT INTO vaga
                (idempresa, titulo, descricao, cep, cidade, estado, pais)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            RETURNING idvaga
        """

        String sqlCompetencia = """
            INSERT INTO vagacompetencia (idvaga, idcompetencia)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtVaga = conexao.prepareStatement(sqlVaga)

            try {
                stmtVaga.setInt(1, vaga.idEmpresa)
                stmtVaga.setString(2, vaga.titulo)
                stmtVaga.setString(3, vaga.descricao)
                stmtVaga.setString(4, vaga.cep)
                stmtVaga.setString(5, vaga.cidade)
                stmtVaga.setString(6, vaga.estado)
                stmtVaga.setString(7, vaga.pais)

                ResultSet resultado = stmtVaga.executeQuery()

                try {
                    if (resultado.next()) {
                        vaga.idVaga = resultado.getInt("idvaga")
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmtVaga.close()
            }

            PreparedStatement stmtCompetencia = conexao.prepareStatement(sqlCompetencia)

            try {
                vaga.competencias.each { competencia ->
                    stmtCompetencia.setInt(1, vaga.idVaga)
                    stmtCompetencia.setInt(2, competencia.idCompetencia)
                    stmtCompetencia.executeUpdate()
                }
            } finally {
                stmtCompetencia.close()
            }
            conexao.commit()
            println "Vaga cadastrada com sucesso! ID: ${vaga.idVaga}"
        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    List<Vaga> listar() {
        String sqlVagas = """
            SELECT
                idvaga,
                idempresa,
                titulo,
                descricao,
                cep,
                cidade,
                estado,
                pais
            FROM vaga
            ORDER BY idvaga
        """

        Connection conexao = ConexaoBD.conectar()
        List<Vaga> vagas = []

        try {
            PreparedStatement stmtVagas = conexao.prepareStatement(sqlVagas)
            try {
                ResultSet resultadoVagas = stmtVagas.executeQuery()
                try {
                    while (resultadoVagas.next()) {
                        Vaga vaga = new Vaga()
                        preencherVaga(vaga, resultadoVagas)
                        vaga.competencias = buscarCompetencias(conexao, vaga.idVaga)
                        vagas.add(vaga)
                    }
                } finally {
                    resultadoVagas.close()
                }
            } finally {
                stmtVagas.close()
            }
            return vagas
        } finally {
            conexao.close()
        }
    }

    Vaga buscarPorId(Integer idVaga) {
        String sql = """
            SELECT
                idvaga,
                idempresa,
                titulo,
                descricao,
                cep,
                cidade,
                estado,
                pais
            FROM vaga
            WHERE idvaga = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idVaga)
            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    Vaga vaga = new Vaga()
                    preencherVaga(vaga, resultado)
                    vaga.competencias = buscarCompetencias(conexao, vaga.idVaga)
                    return vaga
                }
                return null
            } finally {
                resultado.close()
            }
        } finally {
            stmt.close()
            conexao.close()
        }
    }

    boolean atualizar(Vaga vaga) {
        String sqlVaga = """
            UPDATE vaga
            SET idempresa = ?,
                titulo = ?,
                descricao = ?,
                cep = ?,
                cidade = ?,
                estado = ?,
                pais = ?
            WHERE idvaga = ?
        """

        String sqlExcluirCompetencias = """
            DELETE FROM vagacompetencia
            WHERE idvaga = ?
        """

        String sqlInserirCompetencia = """
            INSERT INTO vagacompetencia (idvaga, idcompetencia)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtVaga = conexao.prepareStatement(sqlVaga)
            int linhasAfetadas

            try {
                stmtVaga.setInt(1, vaga.idEmpresa)
                stmtVaga.setString(2, vaga.titulo)
                stmtVaga.setString(3, vaga.descricao)
                stmtVaga.setString(4, vaga.cep)
                stmtVaga.setString(5, vaga.cidade)
                stmtVaga.setString(6, vaga.estado)
                stmtVaga.setString(7, vaga.pais)
                stmtVaga.setInt(8, vaga.idVaga)

                linhasAfetadas = stmtVaga.executeUpdate()
            } finally {
                stmtVaga.close()
            }

            if (linhasAfetadas == 0) {
                conexao.rollback()
                println "Vaga não encontrada."
                return false
            }

            PreparedStatement stmtExcluir = conexao.prepareStatement(sqlExcluirCompetencias)

            try {
                stmtExcluir.setInt(1, vaga.idVaga)
                stmtExcluir.executeUpdate()
            } finally {
                stmtExcluir.close()
            }

            PreparedStatement stmtCompetencia = conexao.prepareStatement(sqlInserirCompetencia)

            try {
                vaga.competencias.each { competencia ->
                    stmtCompetencia.setInt(1, vaga.idVaga)
                    stmtCompetencia.setInt(2, competencia.idCompetencia)
                    stmtCompetencia.executeUpdate()
                }
            } finally {
                stmtCompetencia.close()
            }
            conexao.commit()
            println "Vaga atualizada com sucesso!"
            return true
        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    boolean excluir(Integer idVaga) {
        Connection conexao = ConexaoBD.conectar()
        try {
            conexao.setAutoCommit(false)
            String sqlCompetencias = """
                DELETE FROM vagacompetencia
                WHERE idvaga = ?
            """

            PreparedStatement stmtCompetencias = conexao.prepareStatement(sqlCompetencias)

            try {
                stmtCompetencias.setInt(1, idVaga)
                stmtCompetencias.executeUpdate()
            } finally {
                stmtCompetencias.close()
            }

            String sqlVaga = """
                DELETE FROM vaga
                WHERE idvaga = ?
            """

            PreparedStatement stmtVaga = conexao.prepareStatement(sqlVaga)
            int linhasAfetadas

            try {
                stmtVaga.setInt(1, idVaga)
                linhasAfetadas = stmtVaga.executeUpdate()
            } finally {
                stmtVaga.close()
            }
            if (linhasAfetadas == 0) {
                conexao.rollback()
                println "Vaga não encontrada."
                return false
            }
            conexao.commit()
            println "Vaga excluída com sucesso!"
            return true
        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    private void preencherVaga(Vaga vaga, ResultSet resultado) {
        vaga.idVaga = resultado.getInt("idvaga")
        vaga.idEmpresa = resultado.getInt("idempresa")
        vaga.titulo = resultado.getString("titulo")
        vaga.descricao = resultado.getString("descricao")
        vaga.cep = resultado.getString("cep")
        vaga.cidade = resultado.getString("cidade")
        vaga.estado = resultado.getString("estado")
        vaga.pais = resultado.getString("pais")
    }

    private List<Competencia> buscarCompetencias(Connection conexao, Integer idVaga) {
        String sql = """
            SELECT c.idcompetencia, c.titulo
            FROM vagacompetencia vc
            INNER JOIN competencia c
                ON vc.idcompetencia = c.idcompetencia
            WHERE vc.idvaga = ?
            ORDER BY c.idcompetencia
        """

        List<Competencia> competencias = []
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idVaga)
            ResultSet resultado = stmt.executeQuery()

            try {
                while (resultado.next()) {
                    Competencia competencia = new Competencia(
                            resultado.getInt("idcompetencia"),
                            resultado.getString("titulo")
                    )
                    competencias.add(competencia)
                }
            } finally {
                resultado.close()
            }
        } finally {
            stmt.close()
        }
        return competencias
    }
}