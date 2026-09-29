# Linketinder

O **Linketinder** é um MVP de uma aplicação inspirada no LinkedIn e no Tinder, desenvolvida em **Groovy**. O sistema tem como objetivo aproximar candidatos e empresas a partir de suas informações e competências.

A aplicação possui candidatos e empresas pré-cadastrados e permite também realizar novos cadastros durante a execução. O projeto utiliza orientação a objetos, separação em pacotes e testes automatizados com **Spock Framework**.

---

## Funcionalidades

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

---

## Principais classes

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

O sistema permite realizar dois tipos de cadastro.

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

## Validações

O sistema possui algumas validações para evitar cadastros incompletos ou inválidos.

Entre elas estão:

- Campos de texto obrigatórios não podem ficar vazios;
- A idade deve ser um valor numérico;
- A idade deve estar entre 1 e 120 anos;
- É necessário informar pelo menos uma competência;
- Entradas inválidas para a idade são tratadas sem interromper a execução do programa.

---

## Testes

O projeto utiliza o **Spock Framework** para a implementação dos testes automatizados.

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

- Groovy
- Gradle
- Spock Framework
- JUnit Platform
- IntelliJ IDEA

---

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java;
- Gradle ou utilizar o Gradle Wrapper incluído no projeto.

Recomenda-se utilizar uma versão do Java compatível com a configuração do projeto.

---

## Como executar

Clone o repositório:

```bash
git clone https://github.com/rafasavaris/Linketinder-Project
```

Entre na pasta do projeto:

```bash
cd Linketinder-Project
```

Execute a aplicação utilizando o Gradle Wrapper:

```bash
./gradlew run
```

No Windows:

```bash
gradlew.bat run
```

---

## Como executar os testes

Os testes automatizados podem ser executados utilizando o Gradle Wrapper:

```bash
./gradlew test
```

No Windows:

```bash
gradlew.bat test
```
Após a execução, o Gradle informa se os testes foram executados com sucesso.

---

## Menu do sistema

Ao executar o programa, será apresentado um menu com as seguintes funcionalidades:

- **Listar candidatos**: exibe todos os candidatos pré-cadastrados e os candidatos adicionados durante a execução;
- **Listar empresas**: exibe todas as empresas pré-cadastradas e as empresas adicionadas durante a execução;
- **Cadastro de candidato**: permite cadastrar um novo candidato informando seus dados pessoais e competências;
- **Cadastro de empresa**: permite cadastrar uma nova empresa informando seus dados e competências desejadas;
- **Sair**: encerra a execução do sistema.

---

## Funcionamento do cadastro

Os dados informados pelo usuário são utilizados para criar um novo objeto de `Candidato` ou `Empresa`. Depois disso, o objeto é enviado para o `CadastroService`, que realiza a inserção na lista correspondente.

Dessa forma, os cadastros realizados durante a execução passam a aparecer nas opções de listagem.

---

## Observações

Os dados cadastrados durante a execução são armazenados apenas em memória. Dessa forma, os novos cadastros não são mantidos após o encerramento do programa. Os candidatos e empresas pré-cadastrados são definidos nas classes `CandidatoData` e `EmpresaData`.

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