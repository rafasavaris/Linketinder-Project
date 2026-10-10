package linketinder.model

class Competencia {
    Integer idCompetencia
    String titulo

    Competencia(String titulo) {
        this.titulo = titulo
    }

    Competencia(Integer idCompetencia, String titulo) {
        this.idCompetencia = idCompetencia
        this.titulo = titulo
    }

    @Override
    String toString() {
        return titulo
    }
}