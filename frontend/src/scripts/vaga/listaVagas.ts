const listaVagas = document.getElementById("lista-vagas")!;

function carregarVagas(): void {
    listaVagas.innerHTML = "";

    const vagasJSON = localStorage.getItem("vagas");

    if (vagasJSON === null) {
        listaVagas.innerHTML = "<p>Nenhuma vaga cadastrada.</p>";
        return;
    }

    const vagas = JSON.parse(vagasJSON);

    if(vagas.length === 0) {
        listaVagas.innerHTML = "<p>Nenhuma vaga cadastrada.</p>";
        return;
    }

    vagas.forEach((vaga: any, index: number) => {
        const vagaDiv = document.createElement("div");
        vagaDiv.innerHTML = `
            <div class="card-vaga">
                <h2>${vaga.titulo}</h2>
                <p>${vaga.descricao}</p>
                <p>
                    <strong>Competências:</strong>
                </p>

                <div class="competencias">
                    ${vaga.competencias
                        .map(
                            (competencia: string) =>
                                `<span class="competencia">${competencia}</span>`
                        )
                        .join("")}
                </div>

                <button class="btn-excluir mt-3" id="excluir-${index}">Excluir</button>
            </div>
        `;

        listaVagas.appendChild(vagaDiv);

        const botaoExcluir = document.getElementById(`excluir-${index}`)!;

        botaoExcluir.addEventListener("click", () => {
            excluirVaga(index);
        });
    });
}

function excluirVaga(index: number): void {
    const vagasJSON = localStorage.getItem("vagas");

    if (vagasJSON === null) {
        return;
    }

    const vagas = JSON.parse(vagasJSON);
    vagas.splice(index, 1);

    localStorage.setItem("vagas", JSON.stringify(vagas));

    carregarVagas();
}

carregarVagas();