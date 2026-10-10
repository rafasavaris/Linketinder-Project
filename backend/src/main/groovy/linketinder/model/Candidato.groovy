package linketinder.model
import java.time.LocalDate

class Candidato extends Pessoa {
    String cpf
    LocalDate dataNascimento

    @Override
    exibirDados() {
        println "Nome: ${nome}"
        println "E-mail: ${email}"
        println "CPF: ${cpf}"
        println "Data nascimento: ${dataNascimento}"
        println "Estado: ${estado}"
        println "CEP: ${cep}"
        println "Descrição: ${descricao}"
        println "Competências: ${competencias}"
    }
}

