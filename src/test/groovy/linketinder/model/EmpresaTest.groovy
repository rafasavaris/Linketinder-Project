package linketinder.model

import linketinder.model.Empresa
import spock.lang.Specification

class EmpresaTest extends Specification{
    def "deve criar uma empresa com os dados informados"(){
        given:
        def empresa = new Empresa(
            nome: "Arroz-Gostoso",
            email: "contato@arrozgostoso.com",
            cnpj: "11.111.111/0001-11",
            pais: "Brasil",
            estado: "SC",
            cep: "88800-000",
            descricao: "Empresa do setor alimentício",
            competencias: ["Java", "Spring Framework"]
        )
        expect:
        empresa.nome == "Arroz-Gostoso"
        empresa.email == "contato@arrozgostoso.com"
        empresa.cnpj == "11.111.111/0001-11"
        empresa.pais == "Brasil"
        empresa.estado == "SC"
        empresa.cep == "88800-000"
        empresa.descricao == "Empresa do setor alimentício"
        empresa.competencias == ["Java", "Spring Framework"]
    }
}
