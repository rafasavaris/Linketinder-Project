package linketinder.dao

import java.sql.Connection
import java.sql.DriverManager

class ConexaoBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USUARIO = "postgres"
    private static final String SENHA = System.getenv("LINKETINDER_DB_PASSWORD")

    static Connection conectar() {
        if (SENHA == null || SENHA.isBlank()) {
            throw new IllegalStateException(
                    "Defina a variável de ambiente LINKETINDER_DB_PASSWORD."
            )
        }

        return DriverManager.getConnection(URL, USUARIO, SENHA)
    }
}