-- Usuários
INSERT INTO Usuario
    (idUsuario, nome, email, estado, descricao, cidade, senha, pais, cep)
VALUES
    (1, 'Ana Silva', 'ana.silva@example.com', 'SC', 'Estudante de Engenharia de Computação.',
     'Araranguá', 'hash_teste_ana', 'Brasil', '88900000'),

    (2, 'Bruno Costa', 'bruno.costa@example.com', 'RS', 'Desenvolvedor júnior interessado em dados.',
     'Porto Alegre', 'hash_teste_bruno', 'Brasil', '90000000'),

    (3, 'Pastelsoft', 'contato@pastelsoft.example', 'SC', 'Empresa de desenvolvimento de sistemas.',
     'Criciúma', 'hash_teste_pastelsoft', 'Brasil', '88800000'),

    (4, 'CodeSul', 'contato@codesul.example', 'RS', 'Empresa de tecnologia e soluções de dados.',
     'Porto Alegre', 'hash_teste_codesul', 'Brasil', '90010000');

-- Candidatos
INSERT INTO Candidato (idUsuario, cpf, dataNascimento)
VALUES
    (1, '12345678901', DATE '2005-04-12'),
    (2, '23456789012', DATE '2003-09-25');

-- Empresas
INSERT INTO Empresa (idUsuario, cnpj)
VALUES
    (3, '12345678000195'),
    (4, '23456789000160');

-- Competências
INSERT INTO Competencia (idCompetencia, titulo)
VALUES
    (1, 'Python'),
    (2, 'Java'),
    (3, 'Groovy'),
    (4, 'SQL'),
    (5, 'PostgreSQL'),
    (6, 'JavaScript'),
    (7, 'Angular'),
    (8, 'Spring'),
    (9, 'Análise de dados');

-- Vagas
INSERT INTO Vaga
    (idVaga, idEmpresa, titulo, descricao, cep, cidade, estado, pais)
VALUES
    (1, 3, 'Desenvolvedor Java Júnior', 'Desenvolvimento de APIs para sistemas ERP.',
     '88800000', 'Criciúma', 'SC', 'Brasil'),

    (2, 3, 'Desenvolvedor Front-end', 'Desenvolvimento de interfaces web.',
     '88800000', 'Criciúma', 'SC', 'Brasil'),

    (3, 4, 'Estágio em Dados', 'Consultas SQL e análise de dados.',
     '90010000', 'Porto Alegre', 'RS', 'Brasil');

-- Competências dos candidatos
-- Ana
INSERT INTO CandidatoCompetencia (idCandidato, idCompetencia)
VALUES
    (1, 1),
    (1, 4),
    (1, 5),
    (1, 6);

-- Bruno
INSERT INTO CandidatoCompetencia (idCandidato, idCompetencia)
VALUES
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 8);

-- Competências exigidas pelas vagas
-- Desenvolvedor Java Júnior
INSERT INTO VagaCompetencia (idVaga, idCompetencia)
VALUES
    (1, 2),
    (1, 8),
    (1, 4);

-- Desenvolvedor Front-end
INSERT INTO VagaCompetencia (idVaga, idCompetencia)
VALUES
    (2, 6),
    (2, 7);

-- Estágio em Dados
INSERT INTO VagaCompetencia (idVaga, idCompetencia)
VALUES
    (3, 4),
    (3, 1),
    (3, 9);

-- Curtidas dos candidatos nas vagas
-- Ana curtiu Desenvolvedor Java Júnior
INSERT INTO CurtidaCandidatoVaga (idVaga, idCandidato)
VALUES (1, 1);

-- Bruno curtiu Estágio em Dados
INSERT INTO CurtidaCandidatoVaga (idVaga, idCandidato)
VALUES (3, 2);

-- Curtida da empresa no candidato
-- Pastelsoft demonstrou interesse em Ana
INSERT INTO CurtidaEmpresaCandidato (idEmpresa, idCandidato)
VALUES (3, 1);