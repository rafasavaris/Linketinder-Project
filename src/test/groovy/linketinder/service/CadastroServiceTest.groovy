package linketinder.service

import linketinder.model.Candidato
import linketinder.model.Empresa
import spock.lang.Specification

class CadastroTest extends Specification {
    def "tenta inserir um novo candidato na lista" () {
        given:
        List<Candidato> candidatos = []

        def candidato = new Candidato(
                nome: "Ana Silva",
                email: "ana@email.com",
                cpf: "123.456.789-00",
                idade: 22,
                estado: "SC",
                cep: "88800-000",
                descricao: "Estudante de Computação",
                competencias: ["Python", "Java"]
        )

        when:
        def service = new CadastroService()
        service.cadastroCandidato(candidatos, candidato)

        then:
        candidatos.size() == 1
        candidatos.contains(candidato)
    }

    def "tenta inserir uma nova empresa na lista" () {
        given:
        List<Empresa> empresas = []

        def empresa = new Empresa(
                nome: "Arroz-Gostoso",
                email: "contato@arrozgostoso.com",
                cnpj: "12.345.678/0001-00",
                pais: "Brasil",
                estado: "SC",
                cep: "88800-000",
                descricao: "Empresa do setor alimentício",
                competencias: ["Java", "Spring"]
        )

        when:
        def service = new CadastroService()
        service.cadastroEmpresa(empresas, empresa)

        then:
        empresas.size() == 1
        empresas.contains(empresa)
    }
}