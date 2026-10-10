# Linketinder

O **Linketinder** é um MVP de uma aplicação inspirada no LinkedIn e no Tinder, desenvolvido com **Groovy** no backend e **TypeScript** no frontend. O sistema tem como objetivo aproximar candidatos e empresas a partir de suas informações, competências e oportunidades disponíveis.

O projeto é dividido em duas partes:

- **Backend:** desenvolvido em Groovy, utilizando Gradle e Spock Framework;
- **Frontend:** desenvolvido em TypeScript, HTML e CSS, com Bootstrap para a interface.

O backend possui funcionalidades de cadastro, listagem e validação de candidatos e empresas, além de testes automatizados. O frontend disponibiliza uma interface web para cadastro e visualização de candidatos, empresas e vagas.

Atualmente, o frontend possui funcionamento independente do backend, utilizando `localStorage` do navegador para armazenar os dados durante a utilização da aplicação.

---

## Funcionalidades

### Backend

- Listar candidatos cadastrados;
- Listar empresas cadastradas;
- Cadastrar novos candidatos;
- Cadastrar novas empresas;
- Inserir novos candidatos na lista de candidatos;
- Inserir novas empresas na lista de empresas;
- Validação de campos obrigatórios;
- Validação da idade do candidato;
- Testes automatizados para os cadastros;
- Testes automatizados para criação e preenchimento dos objetos;
- Utilização de TDD no desenvolvimento das funcionalidades de cadastro.

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

O backend é responsável pelas classes e regras principais do sistema, utilizando **Groovy** e conceitos de orientação a objetos.

### Principais classes

- **PessoaInterface**: define o comportamento comum das pessoas do sistema;
- **Pessoa**: classe abstrata que reúne os atributos compartilhados entre candidatos e empresas;
- **Candidato**: representa um candidato e possui informações específicas como CPF e idade;
- **Empresa**: representa uma empresa e possui informações específicas como CNPJ e país;
- **CandidatoData**: contém os candidatos pré-cadastrados;
- **EmpresaData**: contém as empresas pré-cadastradas;
- **CadastroService**: contém as operações responsáveis pela inserção de novos candidatos e empresas nas respectivas listas;
- **Main**: responsável pela execução do sistema e interação com o usuário por meio do menu.

---

## Organização dos dados

Os candidatos e empresas são representados por objetos das classes `Candidato` e `Empresa`.

A classe `Pessoa` reúne os atributos comuns entre os dois tipos de cadastro, enquanto `Candidato` e `Empresa` possuem os atributos específicos de cada um.

### Candidato

Um candidato possui:

- Nome;
- E-mail;
- CPF;
- Idade;
- Estado;
- CEP;
- Descrição pessoal;
- Competências.

### Empresa

Uma empresa possui:

- Nome;
- E-mail corporativo;
- CNPJ;
- País;
- Estado;
- CEP;
- Descrição;
- Competências desejadas.

---

## Cadastro de novos usuários

O backend permite realizar dois tipos de cadastro.

### Cadastro de candidato

Durante o cadastro, são solicitadas as seguintes informações:

- Nome;
- E-mail;
- CPF;
- Idade;
- Estado;
- CEP;
- Descrição;
- Competências.

As competências devem ser informadas separadas por vírgulas. Após o preenchimento dos dados, um novo objeto `Candidato` é criado e inserido na lista de candidatos por meio da classe `CadastroService`.

### Cadastro de empresa

Durante o cadastro, são solicitadas as seguintes informações:

- Nome;
- E-mail corporativo;
- CNPJ;
- País;
- Estado;
- CEP;
- Descrição;
- Competências desejadas.

As competências também devem ser informadas separadas por vírgulas. Após o preenchimento dos dados, um novo objeto `Empresa` é criado e inserido na lista de empresas por meio da classe `CadastroService`.

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

---

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java;
- Node.js;
- npm.

O backend pode ser executado utilizando o Gradle Wrapper incluído no projeto, não sendo necessário instalar o Gradle separadamente.

---

## Como executar o backend

Clone o repositório:

`git clone https://github.com/rafasavaris/Linketinder-Project`

Entre na pasta do projeto:

`cd Linketinder-Project`

Execute a aplicação utilizando o Gradle Wrapper:

`./gradlew run`

No Windows:

`gradlew.bat run`

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

Ao executar o backend, será apresentado um menu com as seguintes funcionalidades:

- **Listar candidatos**: exibe todos os candidatos pré-cadastrados e os candidatos adicionados durante a execução;
- **Listar empresas**: exibe todas as empresas pré-cadastradas e as empresas adicionadas durante a execução;
- **Cadastro de candidato**: permite cadastrar um novo candidato informando seus dados pessoais e competências;
- **Cadastro de empresa**: permite cadastrar uma nova empresa informando seus dados e competências desejadas;
- **Sair**: encerra a execução do sistema.

No frontend, as funcionalidades são acessadas por meio da página inicial e das páginas de candidatos, empresas e vagas.

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
| `Usuario` | Armazena os dados comuns dos usuários cadastrados. |
| `Candidato` | Armazena os dados específicos dos candidatos, utilizando o identificador do usuário como PK e FK. |
| `Empresa` | Armazena os dados específicos das empresas, utilizando o identificador do usuário como PK e FK. |
| `Formacao` | Armazena as formações vinculadas aos usuários. |
| `Competencia` | Armazena as competências disponíveis para associação a candidatos e vagas. |
| `Vaga` | Armazena as vagas cadastradas pelas empresas. |
| `CandidatoCompetencia` | Tabela associativa entre candidatos e competências. |
| `VagaCompetencia` | Tabela associativa entre vagas e competências. |
| `CurtidaCandidatoVaga` | Registra os interesses dos candidatos por vagas. |
| `CurtidaEmpresaCandidato` | Registra os interesses das empresas por candidatos. |

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

Os dados cadastrados no backend durante a execução são armazenados apenas em memória. Dessa forma, os novos cadastros não são mantidos após o encerramento do programa.

Os candidatos e empresas pré-cadastrados são definidos nas classes `CandidatoData` e `EmpresaData`.

No frontend, os dados são armazenados no `localStorage` do navegador.

O frontend e o backend ainda não possuem comunicação direta entre si. A integração entre as duas partes poderá ser implementada posteriormente.

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