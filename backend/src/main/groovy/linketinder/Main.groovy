package linketinder

import linketinder.data.CandidatoData
import linketinder.data.EmpresaData
import linketinder.data.CompetenciaData
import linketinder.data.VagaData
import linketinder.dao.ConexaoBD
import linketinder.model.Candidato
import linketinder.model.Empresa
import linketinder.model.Competencia
import linketinder.model.Vaga

import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.InputMismatchException
import java.sql.SQLException

class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)

        CandidatoData candidatoData = new CandidatoData()
        EmpresaData empresaData = new EmpresaData()
        CompetenciaData competenciaData = new CompetenciaData()
        VagaData vagaData = new VagaData()

        try {
            def conexao = ConexaoBD.conectar()
            conexao.close()
            println "Conexão com o banco de dados estabelecida!"

            int opcao

            do {
                println """
                * ****************************** *
                *          LINKETINDER           *
                * ****************************** *
                * 1  - Gerenciar candidatos      *
                * 2  - Gerenciar empresas        *
                * 3  - Gerenciar competências    *
                * 4  - Gerenciar vagas           *
                * 0  - Sair                      *
                * ****************************** *
                """

                opcao = lerInteiro(scanner, "Escolha uma opção: ")

                try {
                    switch (opcao) {
                        case 1:
                            menuCandidatos(scanner, candidatoData, competenciaData)
                            break

                        case 2:
                            menuEmpresas(scanner, empresaData)
                            break

                        case 3:
                            menuCompetencias(scanner, competenciaData)
                            break

                        case 4:
                            menuVagas(scanner, vagaData, empresaData, competenciaData)
                            break

                        case 0:
                            println "\nEncerrando o Linketinder..."
                            break

                        default:
                            println "\nOpção inválida."
                    }
                } catch (SQLException e) {
                    println "\nErro ao executar operação no banco de dados:"
                    println e.message
                } catch (Exception e) {
                    println "\nErro ao executar operação:"
                    println e.message
                }
            } while (opcao != 0)
        } catch (Exception e) {
            println "\nNão foi possível conectar ao banco de dados."
            println e.message
        } finally {
            scanner.close()
        }
    }

    static String lerTexto(Scanner scanner, String mensagem) {
        String valor

        do {
            print mensagem
            valor = scanner.nextLine().trim()

            if (valor.isEmpty()) {
                println "Esse campo não pode ficar vazio."
            }
        } while (valor.isEmpty())
        return valor
    }

    static String lerTextoOpcional(Scanner scanner, String mensagem) {
        print mensagem
        return scanner.nextLine().trim()
    }

    static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            print mensagem
            String entrada = scanner.nextLine().trim()

            try {
                return Integer.parseInt(entrada)
            } catch (NumberFormatException e) {
                println "Digite um número inteiro válido."
            }
        }
    }

    static LocalDate lerData(Scanner scanner, String mensagem) {
        while (true) {
            print mensagem
            String entrada = scanner.nextLine().trim()

            try {
                return LocalDate.parse(entrada)
            } catch (DateTimeParseException e) {
                println "Data inválida. Utilize o formato AAAA-MM-DD."
            }
        }
    }

    static Integer lerIdOpcional(Scanner scanner, String mensagem) {
        while (true) {
            print mensagem
            String entrada = scanner.nextLine().trim()

            if (entrada.isEmpty()) {
                return null
            }

            try {
                return Integer.parseInt(entrada)
            } catch (NumberFormatException e) {
                println "Digite um ID inteiro válido ou deixe vazio."
            }
        }
    }

    static Map lerDadosUsuario(Scanner scanner) {
        return [
                nome     : lerTexto(scanner, "Nome: "),
                email    : lerTexto(scanner, "E-mail: "),
                senha    : lerTexto(scanner, "Senha: "),
                estado   : lerTexto(scanner, "Estado: "),
                cidade   : lerTexto(scanner, "Cidade: "),
                cep      : lerTexto(scanner, "CEP: "),
                pais     : lerTexto(scanner, "País: "),
                descricao: lerTexto(scanner, "Descrição: ")
        ]
    }

    static void preencherUsuario(Object usuario, Map dados) {
        usuario.nome = dados.nome
        usuario.email = dados.email
        usuario.senha = dados.senha
        usuario.estado = dados.estado
        usuario.cidade = dados.cidade
        usuario.cep = dados.cep
        usuario.pais = dados.pais
        usuario.descricao = dados.descricao
    }

    static List<Competencia> lerCompetencias(Scanner scanner, CompetenciaData competenciaData) {
        println "\nInforme as competências separadas por vírgula."
        println "Exemplo: Java, Python, PostgreSQL"

        String entrada = lerTexto(scanner, "Competências: ")

        List<String> titulos = entrada
                .split(",")
                .collect { it.trim() }
                .findAll { !it.isEmpty() }
                .unique { it.toLowerCase() }

        List<Competencia> competencias = []

        titulos.each { titulo ->
            Competencia competencia = competenciaData.buscarPorTitulo(titulo)

            if (competencia == null) {
                competencia = new Competencia(titulo)
                competenciaData.cadastrar(competencia)

                println "Nova competência cadastrada: ${titulo}"
            }
            competencias.add(competencia)
        }
        return competencias
    }

    static void menuCompetencias(Scanner scanner, CompetenciaData competenciaData) {
        int opcao

        do {
            println """
            * ****************************** *
            *       COMPETÊNCIAS             *
            * ****************************** *
            * 1 - Cadastrar                  *
            * 2 - Listar                     *
            * 3 - Buscar por ID              *
            * 4 - Buscar por título          *
            * 5 - Atualizar                  *
            * 6 - Excluir                    *
            * 0 - Voltar                     *
            * ****************************** *
            """

            opcao = lerInteiro(scanner, "Escolha uma opção: ")

            switch (opcao) {
                case 1:
                    Competencia competencia = new Competencia(lerTexto(scanner, "Título da competência: "))
                    competenciaData.cadastrar(competencia)
                    println "ID gerado: ${competencia.idCompetencia}"
                    break

                case 2:
                    println "\n* **** COMPETÊNCIAS CADASTRADAS **** *"

                    competenciaData.listar().each { item ->
                        println "ID: ${item.idCompetencia} | Título: ${item.titulo}"
                    }
                    break

                case 3:
                    int id = lerInteiro(scanner, "ID da competência: ")
                    Competencia encontrada = competenciaData.buscarPorId(id)

                    if (encontrada != null) {
                        println "ID: ${encontrada.idCompetencia}"
                        println "Título: ${encontrada.titulo}"
                    } else {
                        println "Competência não encontrada."
                    }
                    break

                case 4:
                    String titulo = lerTexto(scanner, "Título: ")
                    Competencia encontrada = competenciaData.buscarPorTitulo(titulo)

                    if (encontrada != null) {
                        println "ID: ${encontrada.idCompetencia}"
                        println "Título: ${encontrada.titulo}"
                    } else {
                        println "Competência não encontrada."
                    }
                    break

                case 5:
                    int id = lerInteiro(scanner, "ID da competência: ")
                    Competencia competencia = competenciaData.buscarPorId(id)

                    if (competencia != null) {
                        println "Título atual: ${competencia.titulo}"
                        competencia.titulo = lerTexto(scanner, "Novo título: ")

                        if (competenciaData.atualizar(competencia)) {
                            println "Competência atualizada."
                        }
                    } else {
                        println "Competência não encontrada."
                    }
                    break

                case 6:
                    int id = lerInteiro(scanner, "ID da competência: ")

                    if (competenciaData.excluir(id)) {
                        println "Competência excluída."
                    } else {
                        println "Competência não encontrada."
                    }
                    break

                case 0:
                    break

                default:
                    println "Opção inválida."
            }
        } while (opcao != 0)
    }

    static void menuCandidatos(
            Scanner scanner,
            CandidatoData candidatoData,
            CompetenciaData competenciaData
    ) {
        int opcao

        do {
            println """
            * ****************************** *
            *          CANDIDATOS             *
            * ****************************** *
            * 1 - Cadastrar                  *
            * 2 - Listar                     *
            * 3 - Buscar por ID              *
            * 4 - Atualizar                  *
            * 5 - Excluir                    *
            * 0 - Voltar                     *
            * ****************************** *
            """

            opcao = lerInteiro(scanner, "Escolha uma opção: ")

            switch (opcao) {
                case 1:
                    Candidato candidato = new Candidato()
                    preencherUsuario(candidato, lerDadosUsuario(scanner))
                    candidato.cpf = lerTexto(scanner, "CPF: ")
                    candidato.dataNascimento = lerData(scanner, "Data de nascimento (AAAA-MM-DD): ")
                    candidato.competencias = lerCompetencias(scanner, competenciaData)
                    candidatoData.cadastrar(candidato)
                    println "Candidato cadastrado com sucesso!"
                    println "ID gerado: ${candidato.idUsuario}"
                    break

                case 2:
                    println "\n* **** CANDIDATOS CADASTRADOS **** *"

                    candidatoData.listar().each { item ->
                        item.exibirDados()
                        println "ID: ${item.idUsuario}"
                        println "* ****************************** *"
                    }
                    break

                case 3:
                    int id = lerInteiro(scanner, "ID do candidato: ")
                    Candidato encontrado = candidatoData.buscarPorId(id)

                    if (encontrado != null) {
                        encontrado.exibirDados()
                        println "ID: ${encontrado.idUsuario}"
                    } else {
                        println "Candidato não encontrado."
                    }
                    break

                case 4:
                    int id = lerInteiro(scanner, "ID do candidato: ")
                    Candidato candidato = candidatoData.buscarPorId(id)

                    if (candidato != null) {
                        println "\nInforme os novos dados do candidato."
                        preencherUsuario(candidato, lerDadosUsuario(scanner))
                        candidato.cpf = lerTexto(scanner, "CPF: ")
                        candidato.dataNascimento = lerData(scanner, "Data de nascimento (AAAA-MM-DD): ")
                        candidato.competencias = lerCompetencias(scanner, competenciaData)

                        if (candidatoData.atualizar(candidato)) {
                            println "Candidato atualizado."
                        }
                    } else {
                        println "Candidato não encontrado."
                    }
                    break

                case 5:
                    int id = lerInteiro(scanner, "ID do candidato: ")
                    if (candidatoData.excluir(id)) {
                        println "Candidato excluído."
                    } else {
                        println "Candidato não encontrado."
                    }
                    break

                case 0:
                    break

                default:
                    println "Opção inválida."
            }
        } while (opcao != 0)
    }

    static void menuEmpresas(Scanner scanner, EmpresaData empresaData) {
        int opcao

        do {
            println """
            * ****************************** *
            *           EMPRESAS              *
            * ****************************** *
            * 1 - Cadastrar                  *
            * 2 - Listar                     *
            * 3 - Buscar por ID              *
            * 4 - Atualizar                  *
            * 5 - Excluir                    *
            * 0 - Voltar                     *
            * ****************************** *
            """

            opcao = lerInteiro(scanner, "Escolha uma opção: ")

            switch (opcao) {
                case 1:
                    Empresa empresa = new Empresa()
                    preencherUsuario(empresa, lerDadosUsuario(scanner))
                    empresa.cnpj = lerTexto(scanner, "CNPJ: ")
                    empresaData.cadastrar(empresa)
                    println "Empresa cadastrada com sucesso!"
                    println "ID gerado: ${empresa.idUsuario}"
                    break

                case 2:
                    println "\n* **** EMPRESAS CADASTRADAS **** *"

                    empresaData.listar().each { item ->
                        item.exibirDados()
                        println "ID: ${item.idUsuario}"
                        println "* ***************************** *"
                    }
                    break

                case 3:
                    int id = lerInteiro(scanner, "ID da empresa: ")
                    Empresa encontrada = empresaData.buscarPorId(id)

                    if (encontrada != null) {
                        encontrada.exibirDados()
                        println "ID: ${encontrada.idUsuario}"
                    } else {
                        println "Empresa não encontrada."
                    }
                    break

                case 4:
                    int id = lerInteiro(scanner, "ID da empresa: ")
                    Empresa empresa = empresaData.buscarPorId(id)

                    if (empresa != null) {
                        println "\nInforme os novos dados da empresa."
                        preencherUsuario(empresa, lerDadosUsuario(scanner))
                        empresa.cnpj = lerTexto(scanner, "CNPJ: ")

                        if (empresaData.atualizar(empresa)) {
                            println "Empresa atualizada."
                        }
                    } else {
                        println "Empresa não encontrada."
                    }
                    break

                case 5:
                    int id = lerInteiro(scanner, "ID da empresa: ")

                    if (empresaData.excluir(id)) {
                        println "Empresa excluída."
                    } else {
                        println "Empresa não encontrada ou não pode ser excluída."
                    }
                    break

                case 0:
                    break

                default:
                    println "Opção inválida."
            }
        } while (opcao != 0)
    }

    static void menuVagas(Scanner scanner, VagaData vagaData, EmpresaData empresaData, CompetenciaData competenciaData) {
        int opcao

        do {
            println """
            * ****************************** *
            *             VAGAS               *
            * ****************************** *
            * 1 - Cadastrar                  *
            * 2 - Listar                     *
            * 3 - Buscar por ID              *
            * 4 - Atualizar                  *
            * 5 - Excluir                    *
            * 0 - Voltar                     *
            * ****************************** *
            """

            opcao = lerInteiro(scanner, "Escolha uma opção: ")

            switch (opcao) {
                case 1:
                    int idEmpresa = lerInteiro(scanner, "ID da empresa responsável pela vaga: ")
                    Empresa empresa = empresaData.buscarPorId(idEmpresa)

                    if (empresa == null) {
                        println "Empresa não encontrada. Cadastre a empresa primeiro."
                        break
                    }

                    Vaga vaga = new Vaga()

                    vaga.idEmpresa = idEmpresa
                    vaga.titulo = lerTexto(scanner, "Título da vaga: ")
                    vaga.descricao = lerTexto(scanner, "Descrição: ")
                    vaga.cep = lerTexto(scanner, "CEP: ")
                    vaga.cidade = lerTexto(scanner, "Cidade: ")
                    vaga.estado = lerTexto(scanner, "Estado: ")
                    vaga.pais = lerTexto(scanner, "País: ")
                    vaga.competencias = lerCompetencias(scanner, competenciaData)

                    vagaData.cadastrar(vaga)
                    println "Vaga cadastrada com sucesso!"
                    println "ID gerado: ${vaga.idVaga}"
                    break

                case 2:
                    println "\n* **** VAGAS CADASTRADAS **** *"

                    vagaData.listar().each { item ->
                        item.exibirDados()
                        println "ID da vaga: ${item.idVaga}"
                        println "ID da empresa: ${item.idEmpresa}"
                        println "* ****************************** *"
                    }
                    break

                case 3:
                    int id = lerInteiro(scanner, "ID da vaga: ")
                    Vaga encontrada = vagaData.buscarPorId(id)

                    if (encontrada != null) {
                        encontrada.exibirDados()
                        println "ID da vaga: ${encontrada.idVaga}"
                        println "ID da empresa: ${encontrada.idEmpresa}"
                    } else {
                        println "Vaga não encontrada."
                    }
                    break

                case 4:
                    int id = lerInteiro(scanner, "ID da vaga: ")
                    Vaga vaga = vagaData.buscarPorId(id)

                    if (vaga != null) {
                        println "\nInforme os novos dados da vaga."
                        int novoIdEmpresa = lerInteiro(scanner, "ID da empresa responsável: ")
                        Empresa empresa = empresaData.buscarPorId(novoIdEmpresa)

                        if (empresa == null) {
                            println "Empresa não encontrada."
                            break
                        }

                        vaga.idEmpresa = novoIdEmpresa
                        vaga.titulo = lerTexto(scanner, "Título: ")
                        vaga.descricao = lerTexto(scanner, "Descrição: ")
                        vaga.cep = lerTexto(scanner, "CEP: ")
                        vaga.cidade = lerTexto(scanner, "Cidade: ")
                        vaga.estado = lerTexto(scanner, "Estado: ")
                        vaga.pais = lerTexto(scanner, "País: ")
                        vaga.competencias = lerCompetencias(scanner, competenciaData)

                        if (vagaData.atualizar(vaga)) {
                            println "Vaga atualizada."
                        }
                    } else {
                        println "Vaga não encontrada."
                    }
                    break
                case 5:
                    int id = lerInteiro(scanner, "ID da vaga: ")
                    if (vagaData.excluir(id)) {
                        println "Vaga excluída."
                    } else {
                        println "Vaga não encontrada."
                    }
                    break
                case 0:
                    break
                default:
                    println "Opção inválida."
            }
        } while (opcao != 0)
    }
}