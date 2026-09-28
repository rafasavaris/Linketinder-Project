package linketinder.model

class Candidato extends Pessoa {
    String cpf
    int idade

    @Override
    void exibirDados() {
        println "Nome: ${nome}"
        println "E-mail: ${email}"
        println "CPF: ${cpf}"
        println "Idade: ${idade}"
        println "Estado: ${estado}"
        println "CEP: ${cep}"
        println "Descrição: ${descricao}"
        println "Competências: ${competencias}"
    }
}

