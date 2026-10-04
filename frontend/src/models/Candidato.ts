import { Pessoa } from "./Pessoa.js"

export class Candidato extends Pessoa {
    constructor(
    nome: string,
    email: string,
    estado: string,
    cep: string,
    descricao: string,
    competencias: string[],
    public cpf: string,
    public idade: number,
    public formacao: string
    ) {
        super(nome, email, estado, cep, descricao, competencias)
    }

    exibirDados(): void {
        console.log(`Nome: ${this.nome}`);
        console.log(`E-mail: ${this.email}`);
        console.log(`CPF: ${this.cpf}`);
        console.log(`Idade: ${this.idade}`);
        console.log(`Estado: ${this.estado}`);
        console.log(`CEP: ${this.cep}`);
        console.log(`Descrição: ${this.descricao}`);
        console.log(`Competências: ${this.competencias}`);
        console.log(`Formação: ${this.formacao}`);
    }
}