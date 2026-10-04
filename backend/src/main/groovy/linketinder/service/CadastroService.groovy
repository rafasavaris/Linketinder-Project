package linketinder.service

import linketinder.model.Candidato
import linketinder.model.Empresa

class CadastroService {
    void cadastroCandidato(List<Candidato> candidatos, Candidato candidato) {
        candidatos.add(candidato)
    }
    void cadastroEmpresa(List<Empresa> empresas, Empresa empresa) {
        empresas.add(empresa)
    }
}
