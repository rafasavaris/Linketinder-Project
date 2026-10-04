package linketinder.model
import linketinder.interfaces.PessoaInterface

abstract class Pessoa implements PessoaInterface {
    String nome
    String estado
    String cep
    String descricao
    String email
    List<String> competencias
}