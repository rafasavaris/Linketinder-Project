import { Vaga } from "../../models/Vagas.js";

const formulario = document.getElementById("form-vaga") as HTMLFormElement;

formulario.addEventListener("submit", (event) => {

    event.preventDefault();

    const titulo = (document.getElementById("titulo") as HTMLInputElement).value;
    const empresa = (document.getElementById("empresa") as HTMLInputElement).value;
    const descricao = (document.getElementById("descricao") as HTMLTextAreaElement).value;
    const competenciasTexto = (document.getElementById("competencias") as HTMLInputElement).value;
    const competencias = competenciasTexto.split(",").map(competencia => competencia.trim());

    const vaga = new Vaga(
        titulo,
        descricao,
        competencias,
        empresa
    );

    const vagasSalvas = localStorage.getItem("vagas");

    const vagas = vagasSalvas
        ? JSON.parse(vagasSalvas)
        : [];

    vagas.push(vaga);

    localStorage.setItem("vagas", JSON.stringify(vagas));

    console.log(vaga);
    formulario.reset();
    alert("Vaga cadastrada com sucesso!");
});