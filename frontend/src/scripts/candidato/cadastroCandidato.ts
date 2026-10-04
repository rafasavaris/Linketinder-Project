import { Candidato } from "../../models/Candidato.js";

const formulario = document.getElementById("form-candidato") as HTMLFormElement;

formulario.addEventListener("submit", (event) => {
    event.preventDefault();

    const nome = (document.getElementById("nome") as HTMLInputElement).value;
    const email = (document.getElementById("email") as HTMLInputElement).value;
    const cpf = (document.getElementById("cpf") as HTMLInputElement).value;
    const idade = Number((document.getElementById("idade") as HTMLInputElement).value);
    const estado = (document.getElementById("estado") as HTMLInputElement).value;
    const cep = (document.getElementById("cep") as HTMLInputElement).value;
    const descricao = (document.getElementById("descricao") as HTMLTextAreaElement).value;
    const formacao = (document.getElementById("formacao") as HTMLTextAreaElement).value;
    
    const competenciasTexto = (document.getElementById("competencias") as HTMLInputElement).value;
    const competencias = competenciasTexto.split(",").map(competencia => competencia.trim());

    const candidato = new Candidato(
        nome,
        email,
        estado,
        cep,
        descricao,
        competencias,
        cpf,
        idade,
        formacao
    );

    console.log(candidato);
    candidato.exibirDados();

    const candidatosSalvos = localStorage.getItem("candidatos");

    const candidatos = candidatosSalvos
        ? JSON.parse(candidatosSalvos)
        : [];

    candidatos.push(candidato);
    localStorage.setItem("candidatos", JSON.stringify(candidatos));

    formulario.reset();
    alert("Candidato cadastrado com sucesso!");
});
