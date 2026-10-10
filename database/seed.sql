INSERT INTO usuario
    (idusuario, nome, email, estado, descricao, cidade, senha, pais, cep)
OVERRIDING SYSTEM VALUE
VALUES
    (1, 'Ana Silva', 'ana.silva@example.com', 'SC',
     'Estudante de Engenharia de Computação.',
     'Araranguá', 'hash_teste_ana', 'Brasil', '88900000'),

    (2, 'Bruno Costa', 'bruno.costa@example.com', 'RS',
     'Desenvolvedor júnior interessado em dados.',
     'Porto Alegre', 'hash_teste_bruno', 'Brasil', '90000000'),

    (3, 'Pastelsoft', 'contato@pastelsoft.example', 'SC',
     'Empresa de desenvolvimento de sistemas.',
     'Criciúma', 'hash_teste_pastelsoft', 'Brasil', '88800000'),

    (4, 'CodeSul', 'contato@codesul.example', 'RS',
     'Empresa de tecnologia e soluções de dados.',
     'Porto Alegre', 'hash_teste_codesul', 'Brasil', '90010000'),

    (5, 'Carla Mendes', 'carla.mendes@example.com', 'SC',
     'Desenvolvedora web com interesse em acessibilidade.',
     'Içara', 'hash_teste_carla', 'Brasil', '88820000'),

    (6, 'Diego Oliveira', 'diego.oliveira@example.com', 'RS',
     'Estudante de Ciência da Computação com foco em inteligência artificial.',
     'Canoas', 'hash_teste_diego', 'Brasil', '92000000'),

    (7, 'Eduarda Lima', 'eduarda.lima@example.com', 'PR',
     'Analista de dados com experiência em SQL e Python.',
     'Curitiba', 'hash_teste_eduarda', 'Brasil', '80000000'),

    (8, 'TechSul', 'contato@techsul.example', 'SC',
     'Empresa especializada em inteligência artificial e automação.',
     'Florianópolis', 'hash_teste_techsul', 'Brasil', '88000000'),

    (9, 'DataMais', 'contato@datamais.example', 'PR',
     'Consultoria em análise de dados e soluções empresariais.',
     'Curitiba', 'hash_teste_datamais', 'Brasil', '80010000'),

    (10, 'Nuvem Digital', 'contato@nuvemdigital.example', 'RS',
     'Empresa de desenvolvimento web e computação em nuvem.',
     'Porto Alegre', 'hash_teste_nuvem', 'Brasil', '90020000');


INSERT INTO candidato (idusuario, cpf, datanascimento)
OVERRIDING SYSTEM VALUE
VALUES
    (1, '12345678901', DATE '2005-04-12'),
    (2, '23456789012', DATE '2003-09-25'),
    (5, '34567890123', DATE '2004-02-18'),
    (6, '45678901234', DATE '2002-11-07'),
    (7, '56789012345', DATE '2001-06-30');

INSERT INTO empresa (idusuario, cnpj)
OVERRIDING SYSTEM VALUE
VALUES
    (3, '12345678000195'),
    (4, '23456789000160'),
    (8, '34567890000171'),
    (9, '45678901000189'),
    (10, '56789012000190');

INSERT INTO competencia (idcompetencia, titulo)
OVERRIDING SYSTEM VALUE
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

INSERT INTO vaga
    (idvaga, idempresa, titulo, descricao, cep, cidade, estado, pais)
OVERRIDING SYSTEM VALUE
VALUES
    (1, 3, 'Desenvolvedor Java Júnior',
     'Desenvolvimento de APIs para sistemas ERP.',
     '88800000', 'Criciúma', 'SC', 'Brasil'),

    (2, 3, 'Desenvolvedor Front-end',
     'Desenvolvimento de interfaces web.',
     '88800000', 'Criciúma', 'SC', 'Brasil'),

    (3, 4, 'Estágio em Dados',
     'Consultas SQL e análise de dados.',
     '90010000', 'Porto Alegre', 'RS', 'Brasil'),

    (4, 8, 'Estágio em Inteligência Artificial',
     'Desenvolvimento e avaliação de soluções com inteligência artificial.',
     '88000000', 'Florianópolis', 'SC', 'Brasil'),

    (5, 9, 'Analista de Dados Júnior',
     'Construção de consultas SQL e análise de indicadores.',
     '80010000', 'Curitiba', 'PR', 'Brasil'),

    (6, 10, 'Desenvolvedor Web',
     'Desenvolvimento e manutenção de aplicações web.',
     '90020000', 'Porto Alegre', 'RS', 'Brasil');

INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
VALUES
    (1, 1),
    (1, 4),
    (1, 5),
    (1, 6);

INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
VALUES
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 8);

INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
VALUES
    (5, 6),
    (5, 7),
    (5, 4);

INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
VALUES
    (6, 1),
    (6, 2),
    (6, 9);

INSERT INTO candidatocompetencia (idcandidato, idcompetencia)
VALUES
    (7, 1),
    (7, 4),
    (7, 5),
    (7, 9);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (1, 2),
    (1, 8),
    (1, 4);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (2, 6),
    (2, 7);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (3, 4),
    (3, 1),
    (3, 9);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (4, 1),
    (4, 2),
    (4, 9);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (5, 4),
    (5, 5),
    (5, 9);

INSERT INTO vagacompetencia (idvaga, idcompetencia)
VALUES
    (6, 6),
    (6, 7);

INSERT INTO curt idacandidatovaga (idvaga, idcandidato)
VALUES (1, 1);

INSERT INTO curtidacandidatovaga (idvaga, idcandidato)
VALUES (3, 2);

INSERT INTO curtidacandidatovaga (idvaga, idcandidato)
VALUES (2, 5);

INSERT INTO curtidacandidatovaga (idvaga, idcandidato)
VALUES (5, 7);

INSERT INTO curtidaempresacandidato (idempresa, idcandidato)
VALUES (3, 1);

INSERT INTO curtidaempresacandidato (idempresa, idcandidato)
VALUES (4, 2);

INSERT INTO curtidaempresacandidato (idempresa, idcandidato)
VALUES (8, 6);

INSERT INTO curtidaempresacandidato (idempresa, idcandidato)
VALUES (9, 7);