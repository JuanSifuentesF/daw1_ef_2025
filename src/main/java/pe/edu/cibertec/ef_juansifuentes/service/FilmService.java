package pe.edu.cibertec.ef_juansifuentes.service;

import pe.edu.cibertec.ef_juansifuentes.dto.ActorDto;
import pe.edu.cibertec.ef_juansifuentes.dto.FilmDto;
import pe.edu.cibertec.ef_juansifuentes.entity.Film;


public interface FilmService {
  Iterable<ActorDto> getActors() throws Exception;
  Iterable<FilmDto> searchFilmByActor(int actorId) throws Exception;
  Iterable<FilmDto> searchAllFilms() throws Exception;
  Film findFilmById(int id) throws Exception;
  int getActorIdByFilmId(int filmId) throws Exception;
  void updateFilmWithActor(Film updatedFilm, int actorId) throws Exception;
  void deleteFilmWithActor(int filmId, int actorId) throws Exception;

}
