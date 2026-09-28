package linketinder.model

class Empresa extends Pessoa {
    String cnpj

    @Override
    void exibirDados() {
        println "Nome: ${nome}"
        println "E-mail: ${email}"
        println "CNPJ: ${cnpj}"
        println "Estado: ${estado}"
        println "CEP: ${cep}"
        println "Descrição: ${descricao}"
        println "Competências: ${competencias}"
    }
}