package linketinder.model
import linketinder.interfaces.PessoaInterface

abstract class Pessoa implements PessoaInterface {
    Integer idUsuario
    String nome
    String estado
    String cep
    String descricao
    String cidade
    String senha
    String pais
    String email
    List<String> competencias
}