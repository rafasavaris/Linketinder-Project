package linketinder

import linketinder.data.CandidatoData
import linketinder.data.EmpresaData
import linketinder.model.Candidato
import linketinder.model.Empresa
import linketinder.service.CadastroService

def candidatos = CandidatoData.candidatos
def empresas = EmpresaData.empresas
def cadastroService = new CadastroService()

def scanner = new Scanner(System.in)
def opcao

do {
    println "\n* ****** LINKETINDER ***** *"
    println "* 1 - Listar candidatos     *"
    println "* 2 - Listar empresas       *"
    println "* 3 - Cadastro de candidato *"
    println "* 4 - Cadastro de empresa   *"
    println "* 0 - Sair                  *"
    print "* Escolha uma opção: "

    opcao = scanner.nextInt()
    scanner.nextLine()

    switch (opcao) {
        case 1:
            println "\n* *** CANDIDATOS *** *"
            candidatos.each { candidato ->
                candidato.exibirDados()
                println "* ********************** *"
            }
            break
        case 2:
            println "\n* *** EMPRESAS *** *"
            empresas.each { empresa ->
                empresa.exibirDados()
                println "* ********************** *"
            }
            break
        case 3:
            cadastrarCandidato(scanner, candidatos, cadastroService)
            break
        case 4:
            cadastrarEmpresa(scanner, empresas, cadastroService)
            break
        case 0:
            println "\nEncerrando o Linketinder..."
            break
        default:
            println "\nOpção inválida."
    }
} while (opcao != 0)

scanner.close()

def lerTexto(scanner, mensagem) {
    String valor

    do {
        print mensagem
        valor = scanner.nextLine().trim()

        if (valor.isEmpty()) println "Esse campo não pode ficar vazio."
    } while (valor.isEmpty())
    return valor
}

int lerIdade(scanner) {
    while (true) {
        try {
            print "Idade: "
            def idade = scanner.nextInt()
            scanner.nextLine()

            if (idade <= 0 || idade > 120) println "Digite uma idade entre 1 e 120."
            else return idade

        } catch (InputMismatchException) {
            println "Digite uma idade válida."
            scanner.nextLine()
        }
    }
}

// lê os dados que são comuns a candidatos e empresas
def lerDadosPessoa(scanner) {
    def dados = [:]

    dados.nome = lerTexto(scanner, "Nome: ")
    dados.email = lerTexto(scanner, "E-mail: ")
    dados.estado = lerTexto(scanner, "Estado: ")
    dados.cep = lerTexto(scanner, "CEP: ")
    dados.descricao = lerTexto(scanner, "Descrição: ")

    def competencias

    do {
        print "Competências (separe por vírgula): "
        competencias = scanner.nextLine()
                .split(",")
                .collect { it.trim() }
                .findAll { !it.isEmpty() }

        if (competencias.isEmpty()) {
            println "Informe pelo menos uma competência."
        }

    } while (competencias.isEmpty())
    dados.competencias = competencias
    return dados
}

// cadastro de candidato
def cadastrarCandidato(scanner, candidatos, cadastroService) {
    println "\n===== CADASTRO DE CANDIDATO ====="

    def dados = lerDadosPessoa(scanner)
    def cpf = lerTexto(scanner, "CPF: ")
    def idade = lerIdade(scanner)

    def candidato = new Candidato(
            nome: dados.nome,
            email: dados.email,
            cpf: cpf,
            idade: idade,
            estado: dados.estado,
            cep: dados.cep,
            descricao: dados.descricao,
            competencias: dados.competencias
    )
    cadastroService.cadastroCandidato(candidatos, candidato)
    println "\nCandidato cadastrado com sucesso!"
}

// cadastro de empresa
def cadastrarEmpresa(scanner, empresas, cadastroService) {
    println "\n===== CADASTRO DE EMPRESA ====="

    def dados = lerDadosPessoa(scanner)
    def cnpj = lerTexto(scanner, "CNPJ: ")
    def pais = lerTexto(scanner, "País: ")

    def empresa = new Empresa(
            nome: dados.nome,
            email: dados.email,
            cnpj: cnpj,
            pais: pais,
            estado: dados.estado,
            cep: dados.cep,
            descricao: dados.descricao,
            competencias: dados.competencias
    )
    cadastroService.cadastroEmpresa(empresas, empresa)
    println "\nEmpresa cadastrada com sucesso!"
}