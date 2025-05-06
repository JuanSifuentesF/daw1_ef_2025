package pe.edu.cibertec.ef_juansifuentes.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ef_juansifuentes.entity.FilmActor;
import pe.edu.cibertec.ef_juansifuentes.entity.FilmActorId;

import java.util.List;

public interface FilmActorRepository  extends CrudRepository<FilmActor, FilmActorId> {

  List<FilmActor> findByFilmActorIdActorId(Integer actorId);
  List<FilmActor> findByFilmActorIdFilmId(Integer filmId);



}
