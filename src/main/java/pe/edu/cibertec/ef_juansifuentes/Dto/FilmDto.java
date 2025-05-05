package pe.edu.cibertec.ef_juansifuentes.Dto;

public record FilmDto(int filmId,
                      String title,
                      int releaseYear,
                      int rentalDuration,
                      double rentalRate,
                      int length,
                      String actorFullName) {
}
