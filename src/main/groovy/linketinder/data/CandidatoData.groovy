package linketinder.data
import linketinder.model.Candidato

class CandidatoData {
    static List<Candidato> candidatos = [
            new Candidato(
                    nome: "Ana Silva",
                    email: "ana@email.com",
                    cpf: "111.111.111-11",
                    idade: 22,
                    estado: "SC",
                    cep: "88800-000",
                    descricao: "Estudante de Engenharia de Computação",
                    competencias: ["Python", "Java"]
            ),

            new Candidato(
                    nome: "Bruno Souza",
                    email: "bruno@email.com",
                    cpf: "222.222.222-22",
                    idade: 25,
                    estado: "RS",
                    cep: "90000-000",
                    descricao: "Desenvolvedor interessado em aplicações web",
                    competencias: ["Java", "Spring Framework", "SQL"]
            ),

            new Candidato(
                    nome: "Carla Oliveira",
                    email: "carla@email.com",
                    cpf: "333.333.333-33",
                    idade: 21,
                    estado: "PR",
                    cep: "80000-000",
                    descricao: "Estudante interessada em inteligência artificial",
                    competencias: ["Python", "Machine Learning"]
            ),

            new Candidato(
                    nome: "Daniel Costa",
                    email: "daniel@email.com",
                    cpf: "444.444.444-44",
                    idade: 28,
                    estado: "SC",
                    cep: "88000-000",
                    descricao: "Desenvolvedor de sistemas",
                    competencias: ["Java", "Angular", "Spring Framework"]
            ),

            new Candidato(
                    nome: "Eduarda Martins",
                    email: "eduarda@email.com",
                    cpf: "555.555.555-55",
                    idade: 24,
                    estado: "SP",
                    cep: "01000-000",
                    descricao: "Desenvolvedora com interesse em aplicações web",
                    competencias: ["JavaScript", "Angular", "Python"]
            )
    ]
}