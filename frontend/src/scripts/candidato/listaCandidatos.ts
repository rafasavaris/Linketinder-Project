const listaCandidatos = document.getElementById("lista-candidatos")!;

function carregarCandidatos(): void {
    listaCandidatos.innerHTML = "";

    const candidatosJSON = localStorage.getItem("candidatos");

    if (candidatosJSON === null) {
        listaCandidatos.innerHTML = "<p>Nenhum candidato cadastrado.</p>";
        return;
    }

    const candidatos = JSON.parse(candidatosJSON);

    if (candidatos.length === 0) {
        listaCandidatos.innerHTML = "<p>Nenhum candidato cadastrado.</p>";
        return;
    }

    candidatos.forEach((candidato: any, index: number) => {
        const candidatoDiv = document.createElement("div");

        candidatoDiv.className = "col-12 col-md-6 col-lg-4";

        candidatoDiv.innerHTML = `
            <div class="card-pessoa">
                <div class="cabecalho-candidato">
                    <h2>Candidato ${index + 1}</h2>
                    <button id="excluir-${index}" class="btn-excluir">Excluir</button>
                </div>

                <p><strong>Competências:</strong></p>

                <div class="competencias">
                    ${candidato.competencias.map((competencia: string) => `
                        <span class="competencia">${competencia}</span>
                    `).join("")}
                </div>

                <p class="mt-3">
                    <strong>Formação:</strong>
                    ${candidato.formacao}
                </p>

                <p>
                    <strong>Descrição:</strong>
                    ${candidato.descricao}
                </p>

                <div class="informacoes-candidato" style="display: none;">
                    <hr>
                    <p>
                        <strong>Idade:</strong>
                        ${candidato.idade}
                    </p>
                    <p>
                        <strong>Estado:</strong>
                        ${candidato.estado}
                    </p>
                    <p>
                        <strong>E-mail:</strong>
                        ${candidato.email}
                    </p>
                </div>
            </div>
        `;

        listaCandidatos.appendChild(candidatoDiv);

        const informacoes = candidatoDiv.querySelector(
            ".informacoes-candidato"
        ) as HTMLDivElement;

        candidatoDiv.addEventListener("mouseenter", () => {
            informacoes.style.display = "block";
        });

        candidatoDiv.addEventListener("mouseleave", () => {
            informacoes.style.display = "none";
        });

        const botaoExcluir = document.getElementById(
            `excluir-${index}`
        )!;

        botaoExcluir.addEventListener("click", () => {
            excluirCandidato(index);
        });
    });
}

function excluirCandidato(index: number): void {
    const candidatosJSON = localStorage.getItem("candidatos");

    if (candidatosJSON === null) {
        return;
    }

    const candidatos = JSON.parse(candidatosJSON);

    candidatos.splice(index, 1);

    localStorage.setItem("candidatos", JSON.stringify(candidatos));

    carregarCandidatos();
}

carregarCandidatos();