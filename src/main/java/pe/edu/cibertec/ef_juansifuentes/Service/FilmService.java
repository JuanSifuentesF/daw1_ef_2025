package pe.edu.cibertec.ef_juansifuentes.Service;

import pe.edu.cibertec.ef_juansifuentes.Dto.ActorDto;
import pe.edu.cibertec.ef_juansifuentes.Dto.FilmDto;
import pe.edu.cibertec.ef_juansifuentes.Entity.Film;


public interface FilmService {
  Iterable<ActorDto> getActors() throws Exception;
  Iterable<FilmDto> searchFilmByActor(int actorId) throws Exception;
  Iterable<FilmDto> searchAllFilms() throws Exception;
  Film findFilmById(int id) throws Exception;
  int getActorIdByFilmId(int filmId) throws Exception;
  void updateFilmWithActor(Film updatedFilm, int actorId) throws Exception;
  void deleteFilmWithActor(int filmId, int actorId) throws Exception;

}
