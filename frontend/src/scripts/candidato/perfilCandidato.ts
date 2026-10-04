const candidatoJSON = localStorage.getItem("candidatos");

if (candidatoJSON !== null) {
    const candidatos = JSON.parse(candidatoJSON);
    const candidato = candidatos[0];

    document.getElementById("nome")!.textContent = candidato.nome;
    document.getElementById("email")!.textContent = candidato.email;
    document.getElementById("cpf")!.textContent = candidato.cpf;
    document.getElementById("idade")!.textContent = candidato.idade;
    document.getElementById("estado")!.textContent = candidato.estado;
    document.getElementById("cep")!.textContent = candidato.cep;
    document.getElementById("descricao")!.textContent = candidato.descricao;
    document.getElementById("competencias")!.textContent = candidato.competencias.join(", ");
}