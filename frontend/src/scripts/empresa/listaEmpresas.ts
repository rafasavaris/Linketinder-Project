const listaEmpresas = document.getElementById("lista-empresas")!;

function carregarEmpresas(): void {
    listaEmpresas.innerHTML = "";

    const empresasJSON = localStorage.getItem("empresas");

    if (empresasJSON === null) {
        listaEmpresas.innerHTML = "<p>Nenhuma empresa cadastrada.</p>";
        return;
    }

    const empresas = JSON.parse(empresasJSON);

    if(empresas.length === 0) {
        listaEmpresas.innerHTML = "<p>Nenhuma empresa cadastrada.</p>";
        return;
    }

    empresas.forEach((empresa: any, index: number) => {
        const empresaDiv = document.createElement("div");
        empresaDiv.className = "col-12 col-md-6 col-lg-4";
        empresaDiv.innerHTML = `
            <div class="card-pessoa">
                <h2>Empresa ${index + 1}</h2>
                <p>
                    <strong>Descrição:</strong>
                    ${empresa.descricao}
                </p>
                <p>
                    <strong>Competências:</strong>
                </p>

                <div class="competencias">
                    ${empresa.competencias.map((competencia: string) => `
                        <span class="competencia">${competencia}</span>
                    `).join("")}
                </div>

                <div class="informacoes-empresa" style="display: none;">
                    <hr>

                    <p>
                        <strong>E-mail:</strong>
                        ${empresa.email}
                    </p>
                    <p>
                        <strong>Estado:</strong>
                        ${empresa.estado}
                    </p>
                    <p>
                        <strong>CEP:</strong>
                        ${empresa.cep}
                    </p>
                </div>

                <button id="excluir-${index}" class="btn-excluir mt-2">Excluir</button>
            </div>
        `;

        listaEmpresas.appendChild(empresaDiv);

        const informacoes = empresaDiv.querySelector(".informacoes-empresa") as HTMLDivElement;

        empresaDiv.addEventListener("mouseenter", () => {
            informacoes.style.display = "block";
        });

        empresaDiv.addEventListener("mouseleave", () => {
            informacoes.style.display = "none";
        });

        const botaoExcluir = document.getElementById(`excluir-${index}`)!;

        botaoExcluir.addEventListener("click", () => {
            excluirEmpresa(index);
        });
    });
}

function excluirEmpresa(index: number): void {
    const empresasJSON = localStorage.getItem("empresas");

    if (empresasJSON === null) {
        return;
    }

    const empresas = JSON.parse(empresasJSON);
    empresas.splice(index, 1);

    localStorage.setItem("empresas", JSON.stringify(empresas));

    carregarEmpresas();
}

carregarEmpresas();