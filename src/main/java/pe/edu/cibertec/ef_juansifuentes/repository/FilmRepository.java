package pe.edu.cibertec.ef_juansifuentes.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ef_juansifuentes.entity.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
