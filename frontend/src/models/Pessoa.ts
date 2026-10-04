export abstract class Pessoa {
    constructor(
        public nome: string,
        public email: string,
        public estado: string,
        public cep: string,
        public descricao: string,
        public competencias: string[]
    ) {}

    abstract exibirDados(): void
}