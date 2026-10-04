package linketinder.data

import linketinder.model.Empresa

class EmpresaData {
    static List<Empresa> empresas = [
            new Empresa(
                    nome: "Arroz-Gostoso",
                    email: "contato@arrozgostoso.com",
                    cnpj: "11.111.111/0001-11",
                    pais: "Brasil",
                    estado: "SC",
                    cep: "88800-000",
                    descricao: "Empresa do setor alimentício",
                    competencias: ["Java", "Spring Framework"]
            ),

            new Empresa(
                    nome: "Império do Boliche",
                    email: "contato@imperiodoboliche.com",
                    cnpj: "22.222.222/0001-22",
                    pais: "Brasil",
                    estado: "SC",
                    cep: "88800-001",
                    descricao: "Empresa especializada em entretenimento e boliche",
                    competencias: ["Python", "SQL"]
            ),

            new Empresa(
                    nome: "Tech Solutions",
                    email: "contato@techsolutions.com",
                    cnpj: "33.333.333/0001-33",
                    pais: "Brasil",
                    estado: "SP",
                    cep: "01000-001",
                    descricao: "Empresa de desenvolvimento de software",
                    competencias: ["JavaScript", "Angular", "Python"]
            ),

            new Empresa(
                    nome: "Inova Sistemas",
                    email: "contato@inovasistemas.com",
                    cnpj: "44.444.444/0001-44",
                    pais: "Brasil",
                    estado: "PR",
                    cep: "80000-001",
                    descricao: "Empresa focada em soluções tecnológicas",
                    competencias: ["Java", "Spring Framework", "SQL"]
            ),

            new Empresa(
                    nome: "DataVision",
                    email: "contato@datavision.com",
                    cnpj: "55.555.555/0001-55",
                    pais: "Brasil",
                    estado: "RS",
                    cep: "90000-001",
                    descricao: "Empresa especializada em análise de dados",
                    competencias: ["Python", "Machine Learning", "SQL"]
            )
    ]
}