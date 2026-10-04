package linketinder.model

class Empresa extends Pessoa {
    String cnpj
    String pais

    @Override
    exibirDados() {
        println "Nome: ${nome}"
        println "E-mail: ${email}"
        println "CNPJ: ${cnpj}"
        println "Estado: ${estado}"
        println "CEP: ${cep}"
        println "País: ${pais}"
        println "Descrição: ${descricao}"
        println "Competências: ${competencias}"
    }
}