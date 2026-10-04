export class Vaga {
    constructor(
        public titulo: string,
        public descricao: string,
        public competencias: string[],
        public empresa: string
    ) {}
}