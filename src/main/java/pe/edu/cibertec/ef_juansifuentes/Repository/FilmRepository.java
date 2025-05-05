package pe.edu.cibertec.ef_juansifuentes.Repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ef_juansifuentes.Entity.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
