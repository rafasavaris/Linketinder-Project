package linketinder.model

class Vaga {
    Integer idVaga
    Integer idEmpresa
    String titulo
    String descricao
    String cep
    String cidade
    String estado
    String pais
    List<Competencia> competencias = []

    def exibirDados() {
        println "Titulo: ${titulo}"
        println "Estado: ${estado}"
        println "Cidade: ${cidade}"
        println "CEP: ${cep}"
        println "País: ${pais}"
        println "Descrição: ${descricao}"
        println "Competências: ${competencias}"
    }
}