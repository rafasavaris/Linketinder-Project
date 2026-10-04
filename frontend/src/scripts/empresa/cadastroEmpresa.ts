import { Empresa } from "../../models/Empresa.js";

const formulario = document.getElementById("form-empresa") as HTMLFormElement;

formulario.addEventListener("submit", (event) => {
    event.preventDefault();

    const nome = (document.getElementById("nome") as HTMLInputElement).value;
    const email = (document.getElementById("email") as HTMLInputElement).value;
    const cnpj = (document.getElementById("cnpj") as HTMLInputElement).value;
    const pais = ((document.getElementById("pais") as HTMLInputElement).value);
    const estado = (document.getElementById("estado") as HTMLInputElement).value;
    const cep = (document.getElementById("cep") as HTMLInputElement).value;
    const descricao = (document.getElementById("descricao") as HTMLTextAreaElement).value;

    const competenciasTexto = (document.getElementById("competencias") as HTMLInputElement).value;
    const competencias = competenciasTexto.split(",").map(competencia => competencia.trim());

    const empresa = new Empresa(
        nome,
        email,
        estado,
        cep,
        descricao,
        competencias,
        cnpj,
        pais
    );

    console.log(empresa);
    empresa.exibirDados();

    localStorage.setItem("empresa", JSON.stringify(empresa));

    formulario.reset();
    alert("Empresa cadastrado com sucesso!");
});
