package linketinder.data

import linketinder.dao.ConexaoBD
import linketinder.model.Candidato
import linketinder.model.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Date

class CandidatoData {
    void cadastrar(Candidato candidato) {
        String sqlUsuario = """
            INSERT INTO usuario
                (nome, email, estado, descricao, cidade, senha, pais, cep)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING idusuario
        """

        String sqlCandidato = """
            INSERT INTO candidato (idusuario, cpf, datanascimento)
            VALUES (?, ?, ?)
        """

        String sqlCompetencia = """
            INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            try {
                stmtUsuario.setString(1, candidato.nome)
                stmtUsuario.setString(2, candidato.email)
                stmtUsuario.setString(3, candidato.estado)
                stmtUsuario.setString(4, candidato.descricao)
                stmtUsuario.setString(5, candidato.cidade)
                stmtUsuario.setString(6, candidato.senha)
                stmtUsuario.setString(7, candidato.pais)
                stmtUsuario.setString(8, candidato.cep)

                ResultSet resultado = stmtUsuario.executeQuery()

                try {
                    if (resultado.next()) {
                        candidato.idUsuario = resultado.getInt("idusuario")
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmtUsuario.close()
            }

            PreparedStatement stmtCandidato = conexao.prepareStatement(sqlCandidato)

            try {
                stmtCandidato.setInt(1, candidato.idUsuario)
                stmtCandidato.setString(2, candidato.cpf)

                if (candidato.dataNascimento != null) {
                    stmtCandidato.setDate(3, Date.valueOf(candidato.dataNascimento))
                } else {
                    stmtCandidato.setNull(3, java.sql.Types.DATE)
                }
                stmtCandidato.executeUpdate()
            } finally {
                stmtCandidato.close()
            }

            PreparedStatement stmtCompetencia = conexao.prepareStatement(sqlCompetencia)

            try {
                candidato.competencias.each { competencia ->
                    stmtCompetencia.setInt(1, candidato.idUsuario)
                    stmtCompetencia.setInt(2, competencia.idCompetencia)
                    stmtCompetencia.executeUpdate()
                }
            } finally {
                stmtCompetencia.close()
            }

            conexao.commit()
            println "Candidato cadastrado com sucesso! ID: ${candidato.idUsuario}"

        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    void adicionarCompetencia(Integer idCandidato, Integer idCompetencia) {
        String sql = """
            INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idCandidato)
            stmt.setInt(2, idCompetencia)

            stmt.executeUpdate()
            println "Competência associada ao candidato com sucesso!"
        } finally {
            stmt.close()
            conexao.close()
        }
    }

    List<Candidato> listar() {
        String sql = """
            SELECT
                u.idusuario,
                u.nome,
                u.email,
                u.estado,
                u.cep,
                u.descricao,
                u.cidade,
                u.senha,
                u.pais,
                c.cpf,
                c.datanascimento
            FROM usuario u
            INNER JOIN candidato c ON u.idusuario = c.idusuario
            ORDER BY u.idusuario
        """

        Connection conexao = ConexaoBD.conectar()
        List<Candidato> candidatos = []

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql)

            try {
                ResultSet resultado = stmt.executeQuery()
                try {
                    while (resultado.next()) {
                        Candidato candidato = new Candidato()
                        preencherCandidato(candidato, resultado)

                        candidato.competencias = buscarCompetencias(
                                conexao,
                                candidato.idUsuario
                        )
                        candidatos.add(candidato)
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmt.close()
            }

            return candidatos

        } finally {
            conexao.close()
        }
    }

    Candidato buscarPorId(Integer idCandidato) {
        String sql = """
            SELECT
                u.idusuario,
                u.nome,
                u.email,
                u.estado,
                u.cep,
                u.descricao,
                u.cidade,
                u.senha,
                u.pais,
                c.cpf,
                c.datanascimento
            FROM usuario u
            INNER JOIN candidato c ON u.idusuario = c.idusuario
            WHERE c.idusuario = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idCandidato)
            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    Candidato candidato = new Candidato()
                    preencherCandidato(candidato, resultado)
                    candidato.competencias = buscarCompetencias(conexao, candidato.idUsuario)

                    return candidato
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

    boolean atualizar(Candidato candidato) {
        String sqlUsuario = """
            UPDATE usuario
            SET nome = ?,
                email = ?,
                estado = ?,
                descricao = ?,
                cidade = ?,
                senha = ?,
                pais = ?,
                cep = ?
            WHERE idusuario = ?
        """

        String sqlCandidato = """
            UPDATE candidato
            SET cpf = ?,
                datanascimento = ?
            WHERE idusuario = ?
        """

        String sqlExcluirCompetencias = """
            DELETE FROM candidatocompetencia
            WHERE idcandidato = ?
        """

        String sqlInserirCompetencia = """
            INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            try {
                stmtUsuario.setString(1, candidato.nome)
                stmtUsuario.setString(2, candidato.email)
                stmtUsuario.setString(3, candidato.estado)
                stmtUsuario.setString(4, candidato.descricao)
                stmtUsuario.setString(5, candidato.cidade)
                stmtUsuario.setString(6, candidato.senha)
                stmtUsuario.setString(7, candidato.pais)
                stmtUsuario.setString(8, candidato.cep)
                stmtUsuario.setInt(9, candidato.idUsuario)

                int linhasAfetadas = stmtUsuario.executeUpdate()

                if (linhasAfetadas == 0) {
                    conexao.rollback()
                    return false
                }
            } finally {
                stmtUsuario.close()
            }

            PreparedStatement stmtCandidato = conexao.prepareStatement(sqlCandidato)

            try {
                stmtCandidato.setString(1, candidato.cpf)

                if (candidato.dataNascimento != null) {
                    stmtCandidato.setDate(
                            2,
                            Date.valueOf(candidato.dataNascimento)
                    )
                } else {
                    stmtCandidato.setNull(2, java.sql.Types.DATE)
                }
                stmtCandidato.setInt(3, candidato.idUsuario)
                stmtCandidato.executeUpdate()
            } finally {
                stmtCandidato.close()
            }

            PreparedStatement stmtExcluir = conexao.prepareStatement(sqlExcluirCompetencias)

            try {
                stmtExcluir.setInt(1, candidato.idUsuario)
                stmtExcluir.executeUpdate()
            } finally {
                stmtExcluir.close()
            }

            PreparedStatement stmtCompetencia = conexao.prepareStatement(sqlInserirCompetencia)

            try {
                candidato.competencias.each { competencia ->
                    stmtCompetencia.setInt(1, candidato.idUsuario)
                    stmtCompetencia.setInt(2, competencia.idCompetencia)
                    stmtCompetencia.executeUpdate()
                }
            } finally {
                stmtCompetencia.close()
            }
            conexao.commit()
            println "Candidato atualizado com sucesso!"
            return true

        } catch (Exception e) {
            conexao.rollback()
            throw e

        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    boolean excluir(Integer idCandidato) {
        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)

            String sqlCompetencias = """
                DELETE FROM candidatocompetencia
                WHERE idcandidato = ?
            """

            PreparedStatement stmtCompetencias = conexao.prepareStatement(sqlCompetencias)

            try {
                stmtCompetencias.setInt(1, idCandidato)
                stmtCompetencias.executeUpdate()
            } finally {
                stmtCompetencias.close()
            }

            String sqlCandidato = """
                DELETE FROM candidato
                WHERE idusuario = ?
            """

            PreparedStatement stmtCandidato = conexao.prepareStatement(sqlCandidato)
            int linhasAfetadas

            try {
                stmtCandidato.setInt(1, idCandidato)
                linhasAfetadas = stmtCandidato.executeUpdate()
            } finally {
                stmtCandidato.close()
            }
            if (linhasAfetadas == 0) {
                conexao.rollback()
                println "Candidato não encontrado."
                return false
            }

            String sqlUsuario = """
                DELETE FROM usuario
                WHERE idusuario = ?
            """

            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            try {
                stmtUsuario.setInt(1, idCandidato)
                stmtUsuario.executeUpdate()
            } finally {
                stmtUsuario.close()
            }
            conexao.commit()
            println "Candidato excluído com sucesso!"
            return true
        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    private void preencherCandidato(Candidato candidato, ResultSet resultado) {
        candidato.idUsuario = resultado.getInt("idusuario")
        candidato.nome = resultado.getString("nome")
        candidato.email = resultado.getString("email")
        candidato.estado = resultado.getString("estado")
        candidato.cep = resultado.getString("cep")
        candidato.descricao = resultado.getString("descricao")
        candidato.cidade = resultado.getString("cidade")
        candidato.senha = resultado.getString("senha")
        candidato.pais = resultado.getString("pais")
        candidato.cpf = resultado.getString("cpf")

        Date dataNascimento = resultado.getDate("datanascimento")

        if (dataNascimento != null) {
            candidato.dataNascimento = dataNascimento.toLocalDate()
        }
    }

    private List<Competencia> buscarCompetencias(Connection conexao, Integer idCandidato) {
        String sql = """
            SELECT comp.idcompetencia, comp.titulo
            FROM candidatocompetencia cc
            INNER JOIN competencia comp
                ON cc.idcompetencia = comp.idcompetencia
            WHERE cc.idcandidato = ?
            ORDER BY comp.titulo
        """

        List<Competencia> competencias = []
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idCandidato)
            ResultSet resultado = stmt.executeQuery()
            try {
                while (resultado.next()) {
                    competencias.add(
                            new Competencia(
                                    resultado.getInt("idcompetencia"),
                                    resultado.getString("titulo")
                            )
                    )
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