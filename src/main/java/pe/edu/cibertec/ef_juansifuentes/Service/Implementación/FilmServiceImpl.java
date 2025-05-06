package pe.edu.cibertec.ef_juansifuentes.Service.Implementación;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ef_juansifuentes.Dto.ActorDto;
import pe.edu.cibertec.ef_juansifuentes.Dto.FilmDto;
import pe.edu.cibertec.ef_juansifuentes.Entity.FilmActorId;
import pe.edu.cibertec.ef_juansifuentes.Service.FilmService;
import pe.edu.cibertec.ef_juansifuentes.Entity.Actor;
import pe.edu.cibertec.ef_juansifuentes.Entity.Film;
import pe.edu.cibertec.ef_juansifuentes.Entity.FilmActor;
import pe.edu.cibertec.ef_juansifuentes.Repository.ActorRepository;
import pe.edu.cibertec.ef_juansifuentes.Repository.FilmActorRepository;
import pe.edu.cibertec.ef_juansifuentes.Repository.FilmRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

  @Autowired
  ActorRepository actorRepository;

  @Autowired
  FilmActorRepository filmActorRepository;

  @Autowired
  FilmRepository filmRepository;

  @Override
  public Iterable<ActorDto> getActors() throws Exception {

    List<ActorDto> listActorDto = new ArrayList<ActorDto>();
    Iterable<Actor> actors = actorRepository.findAll();
    actors.forEach(actor -> {
      String actorFullName = String.format("%s %s",
            actor.getFirstName(),
            actor.getLastName() );
      ActorDto actorDto = new ActorDto(actor.getActorId(),actorFullName);
      listActorDto.add(actorDto);
    });

    return listActorDto;
  }

  @Override
  public Iterable<FilmDto> searchFilmByActor(int actorId) throws Exception {

    List<FilmDto> listFilmDto = new ArrayList<FilmDto>();
    Iterable<FilmActor> filmActors = filmActorRepository.findByFilmActorIdActorId(actorId);
    filmActors.forEach(filmActor -> {
      String actorFullName = String.format("%s %s",
            filmActor.getActor().getFirstName(),
            filmActor.getActor().getLastName());
      FilmDto filmDto = new FilmDto(
            filmActor.getFilm().getFilmId(),
            filmActor.getFilm().getTitle(),
            filmActor.getFilm().getReleaseYear(),
            filmActor.getFilm().getRentalDuration(),
            filmActor.getFilm().getRentalRate(),
            filmActor.getFilm().getLength(),
            actorFullName);
      listFilmDto.add(filmDto);
    });
    return listFilmDto;
  }

  @Override
  public Iterable<FilmDto> searchAllFilms() throws Exception {

    List<FilmDto> listFilmDto = new ArrayList<FilmDto>();
    Iterable<Film> films = filmRepository.findAll();
    films.forEach(film -> {
      String actorFullName = "-";
      FilmDto filmDto = new FilmDto(
            film.getFilmId(),
            film.getTitle(),
            film.getReleaseYear(),
            film.getRentalDuration(),
            film.getRentalRate(),
            film.getLength(),
            actorFullName);
      listFilmDto.add(filmDto);
    });
    return listFilmDto;
  }

  @Override
  public Film findFilmById(int id) throws Exception {
    return filmRepository.findById(id)
          .orElseThrow(() -> new Exception("Película no encontrada"));
  }

  @Override
  public int getActorIdByFilmId(int filmId) throws Exception {
    List<FilmActor> relaciones = filmActorRepository.findByFilmActorIdFilmId(filmId);
    if (!relaciones.isEmpty()) {
      return relaciones.get(0).getActor().getActorId();
    }
    return 0;
  }

  @Override
  public void updateFilmWithActor(Film updatedFilm, int actorId) throws Exception {
    Film original = filmRepository.findById(updatedFilm.getFilmId())
          .orElseThrow(() -> new Exception("Película no encontrada"));

    original.setTitle(updatedFilm.getTitle());
    original.setLength(updatedFilm.getLength());
    original.setReleaseYear(updatedFilm.getReleaseYear());
    original.setRentalDuration(updatedFilm.getRentalDuration());
    original.setRentalRate(updatedFilm.getRentalRate());
    original.setLastUpdate(new Date());

    filmRepository.save(original);

    filmActorRepository.deleteAll(filmActorRepository.findByFilmActorIdFilmId(original.getFilmId()));
    Actor actor = actorRepository.findById(actorId)
          .orElseThrow(() -> new Exception("Actor no encontrado"));

    FilmActor nuevaRelacion = new FilmActor();
    nuevaRelacion.setFilmActorId(new FilmActorId(actorId, original.getFilmId()));
    nuevaRelacion.setActor(actor);
    nuevaRelacion.setFilm(original);
    nuevaRelacion.setLastUpdate(new Date());

    filmActorRepository.save(nuevaRelacion);
  }

  @Override
  public void deleteFilmWithActor(int filmId, int actorId) throws Exception {
    FilmActorId filmActorId = new FilmActorId(actorId, filmId);

    if (filmActorRepository.existsById(filmActorId)) {
      filmActorRepository.deleteById(filmActorId);
    }

  }


}
