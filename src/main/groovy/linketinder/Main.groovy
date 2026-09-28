package linketinder

import linketinder.data.CandidatoData
import linketinder.data.EmpresaData

def candidatos = CandidatoData.candidatos
def empresas = EmpresaData.empresas

def scanner = new Scanner(System.in)
def opcao

do {
    println "\n===== LINKETINDER ====="
    println "1 - Listar candidatos"
    println "2 - Listar empresas"
    println "0 - Sair"
    print "Escolha uma opção: "

    opcao = scanner.nextInt()

    switch (opcao) {
        case 1:
            println "\n===== CANDIDATOS ====="
            candidatos.each { candidato ->
                candidato.exibirDados()
                println "----------------------"
            }
            break
        case 2:
            println "\n===== EMPRESAS ====="
            empresas.each { empresa ->
                empresa.exibirDados()
                println "----------------------"
            }
            break
        case 0:
            println "\nEncerrando o Linketinder..."
            break
        default:
            println "\nOpção inválida."
    }
} while (opcao != 0)

scanner.close()