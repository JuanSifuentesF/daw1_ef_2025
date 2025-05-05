package pe.edu.cibertec.ef_juansifuentes.Repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ef_juansifuentes.Entity.FilmActor;
import pe.edu.cibertec.ef_juansifuentes.Entity.FilmActorId;

import java.util.List;

public interface FilmActorRepository  extends CrudRepository<FilmActor, FilmActorId> {

  List<FilmActor> findByFilmActorIdActorId(Integer actorId);
  List<FilmActor> findByFilmActorIdFilmId(Integer filmId);



}
