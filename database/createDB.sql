CREATE TABLE usuario (
    idusuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome TEXT NOT NULL,
    email VARCHAR(50) NOT NULL,
    estado VARCHAR(50),
    descricao VARCHAR(255),
    cidade VARCHAR(100),
    senha VARCHAR(255) NOT NULL,
    pais VARCHAR(100),
    cep VARCHAR(8)
);

CREATE TABLE candidato (
    idusuario INTEGER PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    datanascimento DATE,

    CONSTRAINT fk_candidato_usuario
        FOREIGN KEY (idusuario) REFERENCES usuario(idusuario) ON DELETE CASCADE
);

CREATE TABLE empresa (
    idusuario INTEGER PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL,

    CONSTRAINT fk_empresa_usuario
        FOREIGN KEY (idusuario) REFERENCES usuario(idusuario) ON DELETE CASCADE
);

CREATE TABLE formacao (
    idformacao INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idusuario INTEGER NOT NULL,
    titulo VARCHAR(50),
    descricao TEXT,
    datainicio DATE,
    datafim TIMESTAMP,
    nivel VARCHAR(50),
    instituicao VARCHAR(100),

    CONSTRAINT fk_formacao_usuario
        FOREIGN KEY (idusuario) REFERENCES usuario(idusuario) ON DELETE CASCADE
);

CREATE TABLE competencia (
    idcompetencia INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(30) NOT NULL
);

CREATE TABLE vaga (
    idvaga INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idempresa INTEGER NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    cep VARCHAR(8),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(100),

    CONSTRAINT fk_vaga_empresa
        FOREIGN KEY (idempresa) REFERENCES empresa(idusuario) ON DELETE CASCADE
);

CREATE TABLE candidatocompetencia (
    idcandidatocompetencia INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idcandidato INTEGER NOT NULL,
    idcompetencia INTEGER NOT NULL,

    CONSTRAINT fk_candidatocompetencia_candidato
        FOREIGN KEY (idcandidato) REFERENCES candidato(idusuario) ON DELETE CASCADE,

    CONSTRAINT fk_candidatocompetencia_competencia
        FOREIGN KEY (idcompetencia) REFERENCES competencia(idcompetencia) ON DELETE CASCADE
);

CREATE TABLE vagacompetencia (
    idvagacompetencia INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idcompetencia INTEGER NOT NULL,
    idvaga INTEGER NOT NULL,

    CONSTRAINT fk_vagacompetencia_competencia
        FOREIGN KEY (idcompetencia) REFERENCES competencia(idcompetencia) ON DELETE CASCADE,

    CONSTRAINT fk_vagacompetencia_vaga
        FOREIGN KEY (idvaga) REFERENCES vaga(idvaga) ON DELETE CASCADE
);

CREATE TABLE curtidacandidatovaga (
    idcurtidacandidatovaga INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idvaga INTEGER NOT NULL,
    idcandidato INTEGER NOT NULL,
    datahora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curtidacandidatovaga_vaga
        FOREIGN KEY (idvaga) REFERENCES vaga(idvaga) ON DELETE CASCADE,

    CONSTRAINT fk_curtidacandidatovaga_candidato
        FOREIGN KEY (idcandidato) REFERENCES candidato(idusuario) ON DELETE CASCADE
);

CREATE TABLE curtidaempresacandidato (
    idcurtidaempresacandidato INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    idempresa INTEGER NOT NULL,
    idcandidato INTEGER NOT NULL,
    datahora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curtidaempresacandidato_empresa
        FOREIGN KEY (idempresa) REFERENCES empresa(idusuario) ON DELETE CASCADE,

    CONSTRAINT fk_curtidaempresacandidato_candidato
        FOREIGN KEY (idcandidato) REFERENCES candidato(idusuario) ON DELETE CASCADE
);