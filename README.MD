# Linketinder

# Linketinder

O **Linketinder** é um MVP de uma aplicação inspirada no LinkedIn e no Tinder, desenvolvido com **Groovy** no backend e **TypeScript** no frontend. O sistema tem como objetivo aproximar candidatos e empresas a partir de suas informações, competências e oportunidades disponíveis.

O projeto é dividido em duas partes:

- **Backend:** desenvolvido em Groovy, utilizando Gradle, PostgreSQL e JDBC para persistência de dados;
- **Frontend:** desenvolvido em TypeScript, HTML e CSS, com Bootstrap para a interface.

O backend possui funcionalidades de cadastro, consulta, atualização e exclusão de candidatos, empresas, competências e vagas, utilizando um banco de dados relacional PostgreSQL. Também possui testes automatizados e validações para os dados informados.

O frontend disponibiliza uma interface web para cadastro e visualização de candidatos, empresas e vagas.

Atualmente, o frontend funciona de maneira independente do backend, utilizando `localStorage` do navegador para armazenar os dados durante a utilização da aplicação. A integração entre o frontend e o backend ainda não foi implementada.

---

## Funcionalidades

### Backend

- CRUD para Candidato, Empresa, Vaga e Competencia;
- Associação entre candidatos e competências;
- Associação entre vagas e competências;
- Persistência dos dados em banco PostgreSQL;
- Acesso ao banco de dados por meio de JDBC;
- Validação dos dados informados;
- Testes automatizados com Spock Framework.

### Frontend

- Página inicial de navegação;
- Cadastro de candidatos;
- Cadastro de empresas;
- Cadastro de vagas;
- Listagem de candidatos;
- Listagem de empresas;
- Listagem de vagas;
- Visualização do perfil do candidato;
- Visualização do perfil da empresa;
- Exclusão de candidatos e empresas cadastrados;
- Exibição anônima de candidatos e empresas nas listas;
- Exibição das competências e formação dos candidatos;
- Exibição das competências das empresas;
- Gráfico de competências dos candidatos no perfil da empresa;
- Armazenamento dos dados utilizando `localStorage`;
- Interface responsiva utilizando Bootstrap;
- Organização do frontend utilizando TypeScript e orientação a objetos.

---

## Backend

O backend é responsável pelas regras de negócio, pelo gerenciamento dos dados e pela interação com o banco de dados. Foi desenvolvido utilizando **Groovy**, **Gradle** e **JDBC**, com persistência em um banco de dados **PostgreSQL**.

A aplicação utiliza classes específicas para representar as entidades do sistema e classes de acesso a dados para executar operações SQL.

### Principais classes

- **PessoaInterface:** define o comportamento comum das pessoas do sistema;
- **Pessoa:** classe abstrata que reúne os atributos compartilhados entre candidatos e empresas;
- **Candidato:** representa um candidato e possui informações específicas, como CPF e data de nascimento;
- **Empresa:** representa uma empresa e possui informações específicas, como CNPJ;
- **Competencia:** representa as competências disponíveis no sistema;
- **Vaga:** representa uma oportunidade de emprego e suas competências exigidas;
- **CandidatoData:** realiza operações de persistência e consulta relacionadas aos candidatos;
- **EmpresaData:** realiza operações de persistência e consulta relacionadas às empresas;
- **CompetenciaData:** realiza operações de persistência e consulta relacionadas às competências;
- **VagaData:** realiza operações de persistência e consulta relacionadas às vagas;
- **ConexaoBD:** gerencia a conexão com o banco de dados PostgreSQL;
- **Main:** responsável pela execução do sistema e pela interação com o usuário por meio de menus.

As classes de acesso a dados implementam operações CRUD (*Create, Read, Update, Delete*), permitindo cadastrar, consultar, atualizar e excluir registros.

### Persistência de dados

Os dados do backend são armazenados no PostgreSQL, permitindo que os registros permaneçam no banco após o encerramento da aplicação.

O acesso é realizado utilizando JDBC, com comandos SQL executados pelas classes de acesso a dados. O projeto não utiliza ORM para realizar o mapeamento entre os objetos Groovy e as tabelas do banco.

---

## Organização dos dados

O sistema utiliza classes para representar as entidades e tabelas relacionais para armazenar suas informações.

A classe `Pessoa` reúne os atributos comuns entre candidatos e empresas, enquanto `Candidato` e `Empresa` possuem atributos específicos.

### Candidato

Um candidato possui:

- Identificador do usuário;
- Nome;
- E-mail;
- CPF;
- Data de nascimento;
- Estado;
- CEP;
- Cidade;
- País;
- Descrição pessoal;
- Senha;
- Competências.

As competências são associadas ao candidato por meio de uma tabela de relacionamento no banco de dados.

### Empresa

Uma empresa possui:

- Identificador do usuário;
- Nome;
- E-mail;
- CNPJ;
- Estado;
- CEP;
- Cidade;
- País;
- Descrição;
- Senha.

### Competência

Uma competência possui:

- Identificador;
- Título.

As competências podem ser associadas a candidatos e vagas.

### Vaga

Uma vaga possui:

- Identificador;
- Identificador da empresa responsável;
- Título;
- Descrição;
- CEP;
- Cidade;
- Estado;
- País;
- Competências exigidas.

Cada vaga está vinculada a uma empresa e pode possuir várias competências associadas.

---

## Gerenciamento de dados

O backend permite gerenciar candidatos, empresas, competências e vagas por meio de operações CRUD.

### Candidatos

É possível cadastrar, listar, consultar, atualizar e excluir candidatos.

Durante o cadastro, são armazenadas as informações pessoais do candidato e suas competências. As competências são relacionadas por meio da tabela `candidatocompetencia`.

### Empresas

É possível cadastrar, listar, consultar, atualizar e excluir empresas.

Cada empresa possui seus dados específicos e pode cadastrar vagas associadas ao seu identificador.

### Competências

É possível cadastrar, listar, consultar, atualizar e excluir competências.

As competências cadastradas podem ser associadas a candidatos e vagas. O sistema utiliza as tabelas `candidatocompetencia` e `vagacompetencia` para representar esses relacionamentos.

### Vagas

É possível cadastrar, listar, consultar, atualizar e excluir vagas.

Cada vaga está vinculada a uma empresa e pode exigir várias competências. As associações são armazenadas na tabela `vagacompetencia`.

As operações de persistência são realizadas pelas respectivas classes de acesso a dados, que executam comandos SQL por meio de JDBC.

---

## Frontend

O frontend do Linketinder foi desenvolvido utilizando **TypeScript**, **HTML**, **CSS** e **Bootstrap**.

A aplicação possui uma página inicial que permite navegar entre as funcionalidades disponíveis para candidatos e empresas.

### Candidato

O candidato pode:

- Cadastrar seu perfil;
- Informar dados pessoais;
- Informar sua formação;
- Informar suas competências;
- Visualizar as vagas disponíveis;
- Visualizar seu perfil.

As vagas são apresentadas sem a identificação da empresa, mantendo a visualização das oportunidades de forma anônima.

### Empresa

A empresa pode:

- Cadastrar seu perfil;
- Informar suas competências desejadas;
- Cadastrar vagas;
- Visualizar candidatos cadastrados;
- Visualizar seu perfil.

A lista de candidatos apresenta as informações de forma anônima, exibindo principalmente competências, formação e descrição.

### Vagas

As vagas possuem:

- Título;
- Descrição;
- Competências necessárias;
- Empresa responsável.

Na listagem de vagas, a identificação da empresa não é exibida ao candidato.

---

## Armazenamento no frontend

Atualmente, o frontend utiliza o `localStorage` do navegador para armazenar os dados cadastrados.

São utilizados diferentes registros para candidatos, empresas e vagas.

Por exemplo, os candidatos são armazenados utilizando a chave `candidatos`.

Os dados permanecem disponíveis enquanto estiverem armazenados no navegador, mas não existe ainda uma comunicação entre o frontend e o backend.

---

## Interface

A interface foi desenvolvida utilizando **Bootstrap 5** juntamente com um arquivo CSS próprio.

O frontend possui:

- Layout responsivo;
- Cards para apresentação dos dados;
- Formulários de cadastro;
- Botões personalizados;
- Identidade visual baseada nas cores do projeto;
- Organização das páginas por tipo de usuário;
- Exibição de informações adicionais ao passar o mouse sobre alguns elementos;
- Gráfico de competências no perfil da empresa.

---

## Validações

O backend possui algumas validações para evitar cadastros incompletos ou inválidos.

Entre elas estão:

- Campos de texto obrigatórios não podem ficar vazios;
- A idade deve ser um valor numérico;
- A idade deve estar entre 1 e 120 anos;
- É necessário informar pelo menos uma competência;
- Entradas inválidas para a idade são tratadas sem interromper a execução do programa.

No frontend, os formulários também possuem campos obrigatórios para auxiliar no preenchimento dos dados.

---

## Testes

O projeto utiliza o **Spock Framework** para a implementação dos testes automatizados do backend.

Os testes verificam principalmente:

- Criação de candidatos com os dados informados;
- Criação de empresas com os dados informados;
- Inserção de um novo candidato na lista;
- Inserção de uma nova empresa na lista;
- Verificação do tamanho da lista após a inserção;
- Verificação de que o objeto cadastrado está presente na lista.

Os testes de cadastro utilizam uma lista vazia e verificam se, após a execução do método de cadastro, o novo elemento foi realmente inserido.

---

## TDD

O desenvolvimento das funcionalidades de cadastro utiliza o conceito de **TDD (Test-Driven Development)**.

O processo consiste em:

1. Criar o teste para a funcionalidade desejada;
2. Executar o teste e verificar a falha;
3. Implementar o código necessário para atender ao teste;
4. Executar novamente os testes;
5. Verificar se todos os testes foram aprovados.

No cadastro, os testes verificam se um novo candidato ou empresa é corretamente inserido na lista correspondente.

---

## Tecnologias utilizadas

### Backend

- Groovy;
- Gradle;
- PostgreSQL;
- JDBC;
- Spock Framework;
- JUnit Platform;
- IntelliJ IDEA.

### Frontend

- TypeScript;
- HTML5;
- CSS3;
- Bootstrap 5;
- Node.js;
- npm;
- TypeScript Compiler.

### Banco de dados

- PostgreSQL;
- DBeaver;
- brModelo.

---

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java compatível com a versão do projeto;
- PostgreSQL;
- Node.js;
- npm.

O backend utiliza o Gradle Wrapper incluído no projeto, não sendo necessário instalar o Gradle separadamente.

Para gerenciar o banco de dados, pode ser utilizado o DBeaver ou outra ferramenta compatível com PostgreSQL.

---

## Como executar o backend

Clone o repositório:

`git clone https://github.com/rafasavaris/Linketinder-Project`

Entre na pasta do backend:

`cd Linketinder-Project/backend`

### 1. Configure o banco de dados

Certifique-se de que o PostgreSQL esteja instalado e em execução.

Utilize os scripts SQL disponíveis na pasta `database` para criar e popular as tabelas.

- `createDB.sql`: cria as tabelas e seus relacionamentos;
- `seed.sql`: insere dados iniciais para testes.

Execute primeiro o script `createDB.sql` e, em seguida, o `seed.sql`.

### 2. Configure a conexão

Verifique as configurações utilizadas pela classe `ConexaoBD`, incluindo:

- Endereço do servidor;
- Porta do PostgreSQL;
- Nome do banco de dados;
- Usuário;
- Senha.

Certifique-se de que os dados de conexão correspondam ao ambiente em que o PostgreSQL está sendo executado.

### 3. Execute a aplicação

No Linux:

`./gradlew run`

No Windows:

`gradlew.bat run`

A aplicação será iniciada no terminal e apresentará os menus disponíveis para interação com o usuário.

---

## Como executar os testes

Os testes automatizados podem ser executados utilizando o Gradle Wrapper:

`./gradlew test`

No Windows:

`gradlew.bat test`

Após a execução, o Gradle informa se os testes foram executados com sucesso.

---

## Como executar o frontend

Entre na pasta do frontend:

`cd frontend`

Instale as dependências:

`npm install`

Compile os arquivos TypeScript:

`npx tsc`

Após a compilação, os arquivos JavaScript serão gerados na pasta `dist`.

Para visualizar as páginas, pode ser utilizado um servidor local, como a extensão **Live Server** do Visual Studio Code.

Abra o arquivo `frontend/pages/index.html` utilizando o servidor local.

---

## Compilação do TypeScript

O frontend utiliza o TypeScript Compiler para transformar os arquivos `.ts` em arquivos `.js`.

A configuração principal está no arquivo `tsconfig.json`.

A compilação é realizada com:

`npx tsc`

Os arquivos compilados são gerados na pasta `dist`, mantendo a organização dos diretórios presentes em `src`.

Por exemplo, `src/scripts/candidato/listaCandidatos.ts` é compilado para `dist/scripts/candidato/listaCandidatos.js`.

---

## Menu do sistema

Ao executar o backend, a aplicação apresenta menus para gerenciar os dados armazenados no banco PostgreSQL.

As funcionalidades incluem:

- **Candidatos:** cadastro, listagem, consulta, atualização e exclusão;
- **Empresas:** cadastro, listagem, consulta, atualização e exclusão;
- **Competências:** cadastro, listagem, consulta, atualização e exclusão;
- **Vagas:** cadastro, listagem, consulta, atualização e exclusão;
- **Sair:** encerra a execução do sistema.

As operações são realizadas por meio das classes de acesso a dados, responsáveis pela comunicação com o banco PostgreSQL. No frontend, as funcionalidades são acessadas por meio da página inicial e das páginas de candidatos, empresas e vagas.

---

## Funcionamento do cadastro

No backend, os dados informados pelo usuário são utilizados para criar um novo objeto de `Candidato` ou `Empresa`. Depois disso, o objeto é enviado para o `CadastroService`, que realiza a inserção na lista correspondente.

No frontend, os dados preenchidos nos formulários são transformados em objetos e armazenados no `localStorage` do navegador.

Dessa forma, os cadastros realizados no frontend podem ser utilizados pelas páginas de listagem e perfil durante a utilização da aplicação.

---

## Modelo Conceitual e Lógico do Banco de Dados

O banco de dados do **Linketinder** tem como objetivo gerenciar usuários, candidatos, empresas, formações, competências e vagas de emprego, além de registrar as manifestações de interesse entre candidatos e empresas.

O projeto utiliza uma estrutura que permite organizar as informações dos usuários, relacionar suas habilidades às oportunidades disponíveis e registrar as interações realizadas na plataforma.

Ambos os modelos foram desenvolvidos utilizando o **brModelo**.

### Diagramas

- [Modelo Conceitual](docs/modeloConceitual.png)
- [Modelo Lógico](docs/modeloLogico.png)

### 1. Modelo Conceitual

O modelo conceitual apresenta as principais entidades do sistema, seus atributos e os relacionamentos entre elas, sem se aprofundar nos detalhes de implementação do banco de dados.

#### Entidades

- **Usuario:** representa os usuários cadastrados na plataforma, armazenando informações como nome, e-mail, senha, descrição e endereço;
- **Candidato:** especialização de `Usuario` que representa os usuários interessados em oportunidades de emprego. Possui atributos específicos, como CPF e data de nascimento;
- **Empresa:** especialização de `Usuario` que representa as empresas cadastradas na plataforma, com CNPJ como atributo específico;
- **Formacao:** armazena as formações acadêmicas e profissionais dos usuários;
- **Competencia:** representa as competências e habilidades que podem ser associadas aos candidatos e às vagas;
- **Vaga:** representa as oportunidades de emprego cadastradas pelas empresas, contendo informações como título, descrição, CEP, cidade, estado e país.

#### Relacionamentos principais

- **CandidatoCompetencia:** associa candidatos às competências que possuem;
- **VagaCompetencia:** associa vagas às competências exigidas ou desejadas;
- **CurtidaCandidatoVaga:** registra o interesse de um candidato por uma vaga;
- **CurtidaEmpresaCandidato:** registra o interesse de uma empresa por um candidato.

O modelo também apresenta uma generalização e especialização entre `Usuario`, `Candidato` e `Empresa`. Dessa forma, os atributos comuns ficam centralizados na entidade `Usuario`, enquanto os atributos específicos são definidos nas entidades especializadas.

### 2. Modelo Lógico

O modelo lógico transforma as entidades e os relacionamentos do modelo conceitual em tabelas, definindo suas chaves primárias (PKs), chaves estrangeiras (FKs), atributos e tipos de dados.

O banco de dados é composto pelas seguintes tabelas:

| Tabela | Descrição |
|---|---|
| `usuario` | Armazena os dados comuns dos usuários cadastrados. |
| `candidato` | Armazena os dados específicos dos candidatos, utilizando `idusuario` como PK e FK. |
| `empresa` | Armazena os dados específicos das empresas, utilizando `idusuario` como PK e FK. |
| `formacao` | Armazena as formações acadêmicas e profissionais vinculadas aos usuários. |
| `competencia` | Armazena as competências disponíveis para associação a candidatos e vagas. |
| `vaga` | Armazena as vagas cadastradas pelas empresas. |
| `candidatocompetencia` | Relaciona candidatos às competências que possuem. |
| `vagacompetencia` | Relaciona vagas às competências exigidas. |
| `curtidacandidatovaga` | Registra o interesse dos candidatos por vagas. |
| `curtidaempresacandidato` | Registra o interesse das empresas por candidatos. |

### Chaves primárias e estrangeiras

- **`usuario`:** possui `idusuario` como chave primária.
- **`candidato`:** utiliza `idusuario` como chave primária e estrangeira, referenciando `usuario`.
- **`empresa`:** utiliza `idusuario` como chave primária e estrangeira, referenciando `usuario`.
- **`formacao`:** possui `idformacao` como chave primária e `idusuario` como chave estrangeira.
- **`competencia`:** possui `idcompetencia` como chave primária.
- **`vaga`:** possui `idvaga` como chave primária e `idempresa` como chave estrangeira.
- **`candidatocompetencia`:** possui `idcandidatocompetencia` como chave primária, além das chaves estrangeiras `idcandidato` e `idcompetencia`.
- **`vagacompetencia`:** possui `idvagacompetencia` como chave primária, além das chaves estrangeiras `idcompetencia` e `idvaga`.
- **`curtidacandidatovaga`:** possui `idcurtidacandidatovaga` como chave primária, além das chaves estrangeiras `idvaga` e `idcandidato`.
- **`curtidaempresacandidato`:** possui `idcurtidaempresacandidato` como chave primária, além das chaves estrangeiras `idempresa` e `idcandidato`.

### 3. Scripts SQL

O projeto possui dois arquivos SQL responsáveis pela criação e pelo preenchimento do banco de dados:

- **`createDB.sql`:** cria a estrutura do banco de dados, incluindo tabelas, colunas, chaves primárias, chaves estrangeiras e relacionamentos;
- **`seed.sql`:** insere dados iniciais para testes, incluindo usuários, candidatos, empresas, competências, vagas, associações entre competências e registros de interesse.

#### Chaves primárias e estrangeiras

As chaves primárias identificam unicamente os registros de cada tabela, enquanto as chaves estrangeiras estabelecem os relacionamentos entre elas.

- **Usuario:** possui `idUsuario` como chave primária.
- **Candidato:** utiliza `idUsuario` como chave primária e estrangeira, referenciando `Usuario`.
- **Empresa:** utiliza `idUsuario` como chave primária e estrangeira, referenciando `Usuario`.
- **Formacao:** possui `idFormacao` como chave primária e `idUsuario` como chave estrangeira.
- **Competencia:** possui `idCompetencia` como chave primária.
- **Vaga:** possui `idVaga` como chave primária e `idEmpresa` como chave estrangeira.
- **CandidatoCompetencia:** possui `idCandidatoCompetencia` como chave primária, além das chaves estrangeiras `idCandidato` e `idCompetencia`.
- **VagaCompetencia:** possui `idVagaCompetencia` como chave primária, além das chaves estrangeiras `idCompetencia` e `idVaga`.
- **CurtidaCandidatoVaga:** possui `idCurtidaCandidatoVaga` como chave primária, além das chaves estrangeiras `idVaga` e `idCandidato`.
- **CurtidaEmpresaCandidato:** possui `idCurtidaEmpresaCandidato` como chave primária, além das chaves estrangeiras `idEmpresa` e `idCandidato`.

### 3. Scripts SQL

O projeto possui dois arquivos SQL responsáveis pela configuração e pelo preenchimento do banco de dados:

- **`createDB.sql`:** cria a estrutura do banco de dados, incluindo as tabelas, suas colunas, chaves primárias e chaves estrangeiras, além dos relacionamentos entre as entidades;
- **`seed.sql`:** insere dados iniciais no banco de dados, permitindo testar as funcionalidades da aplicação com usuários, candidatos, empresas, competências, vagas e registros de interesse.

---

## Estrutura do projeto

Linketinder-Project/
├── backend/
│   ├── gradle/
│   ├── src/
│   ├── bin/
│   └── build/
│
├── frontend/
│   ├── src/
│   │   ├── models/
│   │   │   ├── Pessoa.ts
│   │   │   ├── Candidato.ts
│   │   │   ├── Empresa.ts
│   │   │   └── Vaga.ts
│   │   │
│   │   └── scripts/
│   │       ├── candidato/
│   │       │   ├── cadastroCandidato.ts
│   │       │   ├── listaCandidatos.ts
│   │       │   └── perfilCandidato.ts
│   │       │
│   │       ├── empresa/
│   │       │   ├── cadastroEmpresa.ts
│   │       │   ├── listaEmpresas.ts
│   │       │   └── perfilEmpresa.ts
│   │       │
│   │       └── vaga/
│   │           ├── cadastroVaga.ts
│   │           └── listaVagas.ts
│   │
│   ├── pages/
│   │   ├── candidato/
│   │   │   ├── cadastroCandidato.html
│   │   │   ├── listaCandidatos.html
│   │   │   └── perfilCandidato.html
│   │   │
│   │   ├── empresa/
│   │   │   ├── cadastroEmpresa.html
│   │   │   ├── listaEmpresas.html
│   │   │   └── perfilEmpresa.html
│   │   │
│   │   ├── vaga/
│   │   │   ├── cadastroVaga.html
│   │   │   └── listaVagas.html
│   │   │
│   │   └── index.html
│   │
│   ├── dist/
│   ├── node_modules/
│   ├── package.json
│   ├── package-lock.json
│   ├── style.css
│   └── tsconfig.json
│
├── docs/
│   ├── modeloConceitual.png
│   └── modeloLogico.png
|
├── database/
│   ├── createDB.sql
│   └── seed.sql
|
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle

---

## Observações


O backend utiliza PostgreSQL para persistir os dados de candidatos, empresas, competências e vagas. Dessa forma, os registros permanecem disponíveis após o encerramento da aplicação.

O frontend utiliza `localStorage` do navegador para armazenar os dados cadastrados durante a utilização da interface.

Atualmente, o frontend e o backend funcionam de maneira independente, sem comunicação direta entre si. A integração entre as duas partes poderá ser implementada posteriormente.

---

## Autora

<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/rafasavaris">
        <img src="https://github.com/rafasavaris.png" width="120px" alt="Rafaela Fernandes Savaris">
        <br>
        <b>Rafaela Fernandes Savaris</b>
      </a>
      <br>
      <a href="https://github.com/rafasavaris">
        <img src="https://img.shields.io/badge/GitHub-rafasavaris-181717?style=for-the-badge&logo=github" alt="GitHub">
      </a>
    </td>
  </tr>
</table>

---