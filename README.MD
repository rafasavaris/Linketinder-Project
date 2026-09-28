# Linketinder

O **Linketinder** é um MVP de uma aplicação inspirada no LinkedIn e no Tinder, desenvolvida em **Groovy**. O sistema tem como objetivo aproximar candidatos e empresas a partir de suas informações e competências.

Além disso, o sistema possui candidatos e empresas pré-cadastrados e permite também realizar novos cadastros durante a execução.

---

## Funcionalidades

- Listar candidatos cadastrados;
- Listar empresas cadastradas;
- Cadastrar novos candidatos;
- Cadastrar novas empresas;
- Informar competências dos candidatos;
- Informar competências desejadas pelas empresas;
- Validação de campos obrigatórios;
- Validação da idade do candidato;
- Tratamento de entradas inválidas no cadastro.

---

## Estrutura do projeto

```text
src/
└── main/
    └── groovy/
        └── linketinder/
            ├── Main.groovy
            │
            ├── interfaces/
            │   └── PessoaInterface.groovy
            │
            ├── model/
            │   ├── Pessoa.groovy
            │   ├── Candidato.groovy
            │   └── Empresa.groovy
            │
            └── data/
                ├── CandidatoData.groovy
                └── EmpresaData.groovy
``` 

---

## Principais classes

* PessoaInterface: define o comportamento comum das pessoas do sistema;
* Pessoa: classe abstrata que reúne os atributos compartilhados entre candidatos e empresas;
* Candidato: representa um candidato e possui informações específicas como CPF e idade;
* Empresa: representa uma empresa e possui informações específicas como CNPJ e país;
* CandidatoData: contém os candidatos pré-cadastrados;
* EmpresaData: contém as empresas pré-cadastradas;

---

## Tecnologias utilizadas
* Groovy
* Gradle

---

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

* Java;
* Gradle ou utilizar o Gradle Wrapper incluído no projeto.

---

# Como executar

Clone o repositório:

```bash
git clone https://github.com/rafasavaris/Linketinder-Project
```

Entre na pasta do projeto:

```bash
cd linketinder
```

Execute utilizando o Gradle Wrapper:

```bash
./gradlew run
```

### No Windows:

```bash
gradlew.bat run
```

---

## Menu do sistema

Ao executar o programa, será apresentado um menu com as seguintes funcionalidades:

* Listar candidatos: Exibe todos os candidatos pré-cadastrados e os candidatos adicionados durante a execução;
* Listar empresas: Exibe todas as empresas pré-cadastradas e as empresas adicionadas durante a execução;
* Cadastro de candidato: permite informar Nome, E-mail, CPF, Idade, Estado, CEP, Descrição e Competências. As competências devem ser informadas separadas por vírgulas. Por exemplo: Python, Java, SQL
* Cadastro de empresa: permite informar Nome, E-mail corporativo, CNPJ, País, Estado, CEP, Descrição, Competências desejadas. As competências também devem ser informadas separadas por vírgulas.

---

## Observações

Os dados cadastrados durante a execução são armazenados apenas em memória. Dessa forma, os novos cadastros não são mantidos após o encerramento do programa.

---