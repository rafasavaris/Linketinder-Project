import { Pessoa } from "./Pessoa.js"

export class Empresa extends Pessoa {
    constructor(
    nome: string,
    email: string,
    estado: string,
    cep: string,
    descricao: string,
    competencias: string[],
    public cnpj: string,
    public pais: string
    ) {
        super(nome, email, estado, cep, descricao, competencias)
    }

    exibirDados(): void {
        console.log(`Nome: ${this.nome}`);
        console.log(`E-mail: ${this.email}`);
        console.log(`CNPJ: ${this.cnpj}`);
        console.log(`País: ${this.pais}`);
        console.log(`Estado: ${this.estado}`);
        console.log(`CEP: ${this.cep}`);
        console.log(`Descrição: ${this.descricao}`);
        console.log(`Competências: ${this.competencias}`);
    }
}