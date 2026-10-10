CREATE TABLE Usuario (
    idUsuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome TEXT NOT NULL,
    email VARCHAR(50) NOT NULL,
    estado VARCHAR(50),
    descricao VARCHAR(255),
    cidade VARCHAR(100),
    senha VARCHAR(255) NOT NULL,
    pais VARCHAR(100),
    cep VARCHAR(8)
);

CREATE TABLE Candidato (
    idUsuario INTEGER PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    dataNascimento DATE,

    CONSTRAINT fk_candidato_usuario
        FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario) ON DELETE CASCADE
);

CREATE TABLE Empresa (
    idUsuario INTEGER PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL,

    CONSTRAINT fk_empresa_usuario
        FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario) ON DELETE CASCADE
);

CREATE TABLE Formacao (
    idFormacao INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idUsuario INTEGER NOT NULL,
    titulo VARCHAR(50),
    descricao TEXT,
    dataInicio DATE,
    dataFim TIMESTAMP,
    nivel VARCHAR(50),
    instituicao VARCHAR(100),

    CONSTRAINT fk_formacao_usuario
        FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario) ON DELETE CASCADE
);

CREATE TABLE Competencia (
    idCompetencia INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(30) NOT NULL
);

CREATE TABLE Vaga (
    idVaga INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idEmpresa INTEGER NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    cep VARCHAR(8),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(100),

    CONSTRAINT fk_vaga_empresa
        FOREIGN KEY (idEmpresa) REFERENCES Empresa(idUsuario) ON DELETE CASCADE
);

CREATE TABLE CandidatoCompetencia (
    idCandidatoCompetencia INTEGER
        GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idCandidato INTEGER NOT NULL,
    idCompetencia INTEGER NOT NULL,

    CONSTRAINT fk_candidatocompetencia_candidato
        FOREIGN KEY (idCandidato) REFERENCES Candidato(idUsuario) ON DELETE CASCADE,

    CONSTRAINT fk_candidatocompetencia_competencia
        FOREIGN KEY (idCompetencia) REFERENCES Competencia(idCompetencia) ON DELETE CASCADE
);

CREATE TABLE VagaCompetencia (
    idVagaCompetencia INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idCompetencia INTEGER NOT NULL,
    idVaga INTEGER NOT NULL,

    CONSTRAINT fk_vagacompetencia_competencia
        FOREIGN KEY (idCompetencia) REFERENCES Competencia(idCompetencia) ON DELETE CASCADE,

    CONSTRAINT fk_vagacompetencia_vaga
        FOREIGN KEY (idVaga) REFERENCES Vaga(idVaga) ON DELETE CASCADE
);

CREATE TABLE CurtidaCandidatoVaga (
    idCurtidaCandidatoVaga INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idVaga INTEGER NOT NULL,
    idCandidato INTEGER NOT NULL,
    dataHora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curtidacandidatovaga_vaga
        FOREIGN KEY (idVaga) REFERENCES Vaga(idVaga) ON DELETE CASCADE,

    CONSTRAINT fk_curtidacandidatovaga_candidato
        FOREIGN KEY (idCandidato) REFERENCES Candidato(idUsuario) ON DELETE CASCADE
);

CREATE TABLE CurtidaEmpresaCandidato (
    idCurtidaEmpresaCandidato INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idEmpresa INTEGER NOT NULL,
    idCandidato INTEGER NOT NULL,
    dataHora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curtidaempresacandidato_empresa
        FOREIGN KEY (idEmpresa) REFERENCES Empresa(idUsuario) ON DELETE CASCADE,

    CONSTRAINT fk_curtidaempresacandidato_candidato
        FOREIGN KEY (idCandidato) REFERENCES Candidato(idUsuario) ON DELETE CASCADE
);