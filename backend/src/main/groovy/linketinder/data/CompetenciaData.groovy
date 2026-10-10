package linketinder.data

import linketinder.dao.ConexaoBD
import linketinder.model.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaData {
    Integer cadastrar(Competencia competencia) {
        String sql = """
            INSERT INTO competencia (titulo)
            VALUES (?)
            RETURNING idcompetencia
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setString(1, competencia.titulo)

            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    Integer id = resultado.getInt("idcompetencia")
                    competencia.idCompetencia = id

                    println "Competência cadastrada com sucesso! ID: ${id}"

                    return id
                }
            } finally {
                resultado.close()
            }
        } finally {
            stmt.close()
            conexao.close()
        }

        return null
    }

    List<Competencia> listar() {
        String sql = """
            SELECT idcompetencia, titulo
            FROM competencia
            ORDER BY idcompetencia
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)
        ResultSet resultado = null

        List<Competencia> competencias = []

        try {
            resultado = stmt.executeQuery()

            while (resultado.next()) {
                Integer id = resultado.getInt("idcompetencia")
                String titulo = resultado.getString("titulo")
                competencias.add(new Competencia(id, titulo))
            }
            return competencias
        } finally {
            resultado?.close()
            stmt.close()
            conexao.close()
        }
    }

    Competencia buscarPorId(Integer idCompetencia) {
        String sql = """
            SELECT idcompetencia, titulo
            FROM competencia
            WHERE idcompetencia = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idCompetencia)
            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    return new Competencia(
                            resultado.getInt("idcompetencia"),
                            resultado.getString("titulo")
                    )
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

    Competencia buscarPorTitulo(String titulo) {
        String sql = """
            SELECT idcompetencia, titulo
            FROM competencia
            WHERE LOWER(titulo) = LOWER(?)
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setString(1, titulo.trim())
            ResultSet resultado = stmt.executeQuery()

            try {
                if (resultado.next()) {
                    return new Competencia(
                            resultado.getInt("idcompetencia"),
                            resultado.getString("titulo")
                    )
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

    boolean atualizar(Competencia competencia) {
        String sql = """
            UPDATE competencia
            SET titulo = ?
            WHERE idcompetencia = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setString(1, competencia.titulo)
            stmt.setInt(2, competencia.idCompetencia)
            int linhasAfetadas = stmt.executeUpdate()

            if (linhasAfetadas > 0) {
                println "Competência atualizada com sucesso!"
                return true
            }

            println "Competência não encontrada."
            return false
        } finally {
            stmt.close()
            conexao.close()
        }
    }

    boolean excluir(Integer idCompetencia) {
        String sql = """
            DELETE FROM competencia
            WHERE idcompetencia = ?
        """

        Connection conexao = ConexaoBD.conectar()
        PreparedStatement stmt = conexao.prepareStatement(sql)

        try {
            stmt.setInt(1, idCompetencia)
            int linhasAfetadas = stmt.executeUpdate()

            if (linhasAfetadas > 0) {
                println "Competência excluída com sucesso!"
                return true
            }

            println "Competência não encontrada."
            return false
        } finally {
            stmt.close()
            conexao.close()
        }
    }
}