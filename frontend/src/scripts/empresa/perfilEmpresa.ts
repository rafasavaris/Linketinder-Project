const empresasJSON = localStorage.getItem("empresas");

if (empresasJSON !== null) {
    const empresas = JSON.parse(empresasJSON);
    const empresa = empresas[0];

    if (empresa !== undefined) {
        document.getElementById("nome")!.textContent = empresa.nome;
        document.getElementById("email")!.textContent = empresa.email;
        document.getElementById("cnpj")!.textContent = empresa.cnpj;
        document.getElementById("pais")!.textContent = empresa.pais;
        document.getElementById("estado")!.textContent = empresa.estado;
        document.getElementById("cep")!.textContent = empresa.cep;
        document.getElementById("descricao")!.textContent = empresa.descricao;
        document.getElementById("competencias")!.textContent = empresa.competencias.join(", ");
    }
}

const canvas = document.getElementById("grafico-competencias") as HTMLCanvasElement;
const contexto = canvas.getContext("2d")!;
const candidatosJSON = localStorage.getItem("candidatos");

if (candidatosJSON === null) {
    contexto.font = "18px Arial";
    contexto.fillText(
        "Nenhum candidato cadastrado.",
        50,
        50
    );
} else {
    const candidatos = JSON.parse(candidatosJSON);

    const quantidadeCompetencias: {
        [competencia: string]: number
    } = {};

    candidatos.forEach((candidato: any) => {
        candidato.competencias.forEach((competencia: string) => {
            if (quantidadeCompetencias[competencia] === undefined) {
                quantidadeCompetencias[competencia] = 1;
            } else {
                quantidadeCompetencias[competencia]++;
            }
        });
    });

    const competencias = Object.keys(quantidadeCompetencias);
    const quantidades = Object.values(quantidadeCompetencias);

    if (competencias.length === 0) {
        contexto.font = "18px Arial";
        contexto.fillText(
            "Nenhuma competência cadastrada.",
            50,
            50
        );
    } else {
        const maiorQuantidade = Math.max(...quantidades);

        const larguraBarra = 45;
        const espaco = 90;
        const alturaMaxima = 250;

        const margemEsquerda = 60;
        const margemInferior = 60;
        const margemSuperior = 40;

        contexto.clearRect(0, 0, canvas.width,canvas.height);
        contexto.fillStyle = "#ffffff";
        contexto.fillRect(0, 0, canvas.width,canvas.height);
        contexto.fillStyle = "#212529";
        contexto.font = "bold 18px Arial";
        contexto.fillText("Candidatos por competência", 30,25);
        contexto.strokeStyle = "#dee2e6";
        contexto.lineWidth = 1;

        contexto.beginPath();
        contexto.moveTo(margemEsquerda, margemSuperior);
        contexto.lineTo(margemEsquerda, 330);
        contexto.stroke();

        contexto.beginPath();
        contexto.moveTo(margemEsquerda, 330);
        contexto.lineTo(canvas.width - 20, 330);
        contexto.stroke();

        const barras: {
            x: number;
            y: number;
            largura: number;
            altura: number;
            competencia: string;
            quantidade: number;
        }[] = [];

        competencias.forEach((competencia, index) => {
            const quantidade = quantidadeCompetencias[competencia];
            const alturaBarra = (quantidade / maiorQuantidade) * alturaMaxima;
            const x = margemEsquerda + 25 + index * espaco;
            const y = 330 - alturaBarra;

            barras.push({
                x: x,
                y: y,
                largura: larguraBarra,
                altura: alturaBarra,
                competencia: competencia,
                quantidade: quantidade
            });

            contexto.fillStyle = "#d63384";
            contexto.beginPath();
            contexto.fillRect(
                x,
                y,
                larguraBarra,
                alturaBarra
            );

            contexto.fillStyle = "#212529";
            contexto.font = "bold 14px Arial";
            contexto.textAlign = "center";
            contexto.fillText(String(quantidade), x + larguraBarra / 2,y - 8);
            contexto.font = "12px Arial";
            contexto.fillText(competencia, x + larguraBarra / 2, 350);
        });

        contexto.textAlign = "start";

        canvas.addEventListener("mousemove", (evento) => {
            const retangulo = canvas.getBoundingClientRect();
            const mouseX = evento.clientX - retangulo.left;
            const mouseY = evento.clientY - retangulo.top;

            const barraEncontrada = barras.find((barra) => {
                return (
                    mouseX >= barra.x &&
                    mouseX <= barra.x + barra.largura &&
                    mouseY >= barra.y &&
                    mouseY <= barra.y + barra.altura
                );
            });

            if (barraEncontrada) {
                canvas.title =
                    `${barraEncontrada.competencia}: ${barraEncontrada.quantidade} candidato(s)`;

                canvas.style.cursor = "pointer";
            } else {
                canvas.title = "";
                canvas.style.cursor = "default";
            }
        });
    }
}