package linketinder.model
import spock.lang.Specification

class CandidatoTest extends Specification{
    def "deve criar um candidato com os dados informados"(){
        given:
        def candidato = new Candidato(
                nome: "Bruno Souza",
                email: "bruno@email.com",
                cpf: "222.222.222-22",
                idade: 25,
                estado: "RS",
                cep: "90000-000",
                descricao: "Desenvolvedor interessado em aplicações web",
                competencias: ["Java", "Spring Framework", "SQL"]
        )
        expect:
        candidato.nome == "Bruno Souza"
        candidato.email == "bruno@email.com"
        candidato.cpf == "222.222.222-22"
        candidato.idade == 25
        candidato.estado == "RS"
        candidato.cep == "90000-000"
        candidato.descricao == "Desenvolvedor interessado em aplicações web"
        candidato.competencias == ["Java", "Spring Framework", "SQL"]
    }
}
