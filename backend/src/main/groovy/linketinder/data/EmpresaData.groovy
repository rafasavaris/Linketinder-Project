package linketinder.data

import linketinder.dao.ConexaoBD
import linketinder.model.Empresa

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaData {
    void cadastrar(Empresa empresa) {
        String sqlUsuario = """
            INSERT INTO usuario
                (nome, email, estado, descricao, cidade, senha, pais, cep)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING idusuario
        """

        String sqlEmpresa = """
            INSERT INTO empresa (idusuario, cnpj)
            VALUES (?, ?)
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            try {
                stmtUsuario.setString(1, empresa.nome)
                stmtUsuario.setString(2, empresa.email)
                stmtUsuario.setString(3, empresa.estado)
                stmtUsuario.setString(4, empresa.descricao)
                stmtUsuario.setString(5, empresa.cidade)
                stmtUsuario.setString(6, empresa.senha)
                stmtUsuario.setString(7, empresa.pais)
                stmtUsuario.setString(8, empresa.cep)

                ResultSet resultado = stmtUsuario.executeQuery()

                try {
                    if (resultado.next()) {
                        empresa.idUsuario = resultado.getInt("idusuario")
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmtUsuario.close()
            }

            PreparedStatement stmtEmpresa = conexao.prepareStatement(sqlEmpresa)

            try {
                stmtEmpresa.setInt(1, empresa.idUsuario)
                stmtEmpresa.setString(2, empresa.cnpj)
                stmtEmpresa.executeUpdate()
            } finally {
                stmtEmpresa.close()
            }
            conexao.commit()
            println "Empresa cadastrada com sucesso! ID: ${empresa.idUsuario}"

        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    List<Empresa> listar() {
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
                e.cnpj
            FROM usuario u
            INNER JOIN empresa e ON u.idusuario = e.idusuario
            ORDER BY u.idusuario
        """

        Connection conexao = ConexaoBD.conectar()
        List<Empresa> empresas = []

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql)
            try {
                ResultSet resultado = stmt.executeQuery()
                try {
                    while (resultado.next()) {
                        Empresa empresa = new Empresa()
                        preencherEmpresa(empresa, resultado)
                        empresas.add(empresa)
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmt.close()
            }
            return empresas
        } finally {
            conexao.close()
        }
    }

    Empresa buscarPorId(Integer idEmpresa) {
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
                e.cnpj
            FROM usuario u
            INNER JOIN empresa e ON u.idusuario = e.idusuario
            WHERE e.idusuario = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idEmpresa)
            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    Empresa empresa = new Empresa()

                    preencherEmpresa(empresa, resultado)

                    return empresa
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

    boolean atualizar(Empresa empresa) {
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
              AND EXISTS (
                  SELECT 1
                  FROM empresa
                  WHERE empresa.idusuario = usuario.idusuario
              )
        """

        String sqlEmpresa = """
            UPDATE empresa
            SET cnpj = ?
            WHERE idusuario = ?
        """

        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)
            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            int linhasAfetadas

            try {
                stmtUsuario.setString(1, empresa.nome)
                stmtUsuario.setString(2, empresa.email)
                stmtUsuario.setString(3, empresa.estado)
                stmtUsuario.setString(4, empresa.descricao)
                stmtUsuario.setString(5, empresa.cidade)
                stmtUsuario.setString(6, empresa.senha)
                stmtUsuario.setString(7, empresa.pais)
                stmtUsuario.setString(8, empresa.cep)
                stmtUsuario.setInt(9, empresa.idUsuario)

                linhasAfetadas = stmtUsuario.executeUpdate()
            } finally {
                stmtUsuario.close()
            }

            if (linhasAfetadas == 0) {
                conexao.rollback()
                println "Empresa não encontrada."
                return false
            }

            PreparedStatement stmtEmpresa = conexao.prepareStatement(sqlEmpresa)

            try {
                stmtEmpresa.setString(1, empresa.cnpj)
                stmtEmpresa.setInt(2, empresa.idUsuario)

                stmtEmpresa.executeUpdate()
            } finally {
                stmtEmpresa.close()
            }
            conexao.commit()
            println "Empresa atualizada com sucesso!"
            return true
        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    boolean excluir(Integer idEmpresa) {
        Connection conexao = ConexaoBD.conectar()

        try {
            conexao.setAutoCommit(false)

            String sqlVerificarVagas = """
                SELECT COUNT(*) AS total
                FROM vaga
                WHERE idempresa = ?
            """

            PreparedStatement stmtVerificar = conexao.prepareStatement(sqlVerificarVagas)

            try {
                stmtVerificar.setInt(1, idEmpresa)
                ResultSet resultado = stmtVerificar.executeQuery()

                try {
                    if (resultado.next() && resultado.getInt("total") > 0) {
                        conexao.rollback()
                        println "Não é possível excluir: a empresa possui vagas cadastradas."
                        return false
                    }
                } finally {
                    resultado.close()
                }
            } finally {
                stmtVerificar.close()
            }

            String sqlEmpresa = """
                DELETE FROM empresa
                WHERE idusuario = ?
            """

            PreparedStatement stmtEmpresa = conexao.prepareStatement(sqlEmpresa)

            int linhasAfetadas

            try {
                stmtEmpresa.setInt(1, idEmpresa)
                linhasAfetadas = stmtEmpresa.executeUpdate()
            } finally {
                stmtEmpresa.close()
            }

            if (linhasAfetadas == 0) {
                conexao.rollback()
                println "Empresa não encontrada."
                return false
            }

            String sqlUsuario = """
                DELETE FROM usuario
                WHERE idusuario = ?
            """

            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario)

            try {
                stmtUsuario.setInt(1, idEmpresa)
                stmtUsuario.executeUpdate()
            } finally {
                stmtUsuario.close()
            }

            conexao.commit()
            println "Empresa excluída com sucesso!"
            return true

        } catch (Exception e) {
            conexao.rollback()
            throw e
        } finally {
            conexao.setAutoCommit(true)
            conexao.close()
        }
    }

    private void preencherEmpresa(Empresa empresa, ResultSet resultado) {
        empresa.idUsuario = resultado.getInt("idusuario")
        empresa.nome = resultado.getString("nome")
        empresa.email = resultado.getString("email")
        empresa.estado = resultado.getString("estado")
        empresa.cep = resultado.getString("cep")
        empresa.descricao = resultado.getString("descricao")
        empresa.cidade = resultado.getString("cidade")
        empresa.senha = resultado.getString("senha")
        empresa.pais = resultado.getString("pais")
        empresa.cnpj = resultado.getString("cnpj")
    }
}