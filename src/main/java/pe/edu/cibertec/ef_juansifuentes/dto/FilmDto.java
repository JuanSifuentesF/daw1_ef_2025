package pe.edu.cibertec.ef_juansifuentes.dto;

public record FilmDto(int filmId,
                      String title,
                      int releaseYear,
                      int rentalDuration,
                      double rentalRate,
                      int length,
                      String actorFullName) {
}
