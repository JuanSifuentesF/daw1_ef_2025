package pe.edu.cibertec.ef_juansifuentes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ef_juansifuentes.service.FilmService;
import pe.edu.cibertec.ef_juansifuentes.request.FilmActorRequest;
import pe.edu.cibertec.ef_juansifuentes.response.DeleteFilmsActorResponse;

import java.util.List;

@RestController
@RequestMapping("/manage-films")
public class ManageFilmApi {

  @Autowired
  FilmService filmService;

  @DeleteMapping("/delete")
  public DeleteFilmsActorResponse deleteFilmsActor(@RequestBody List<FilmActorRequest> requests) {
    try {
      for (FilmActorRequest request : requests) {
        filmService.deleteFilmWithActor(request.film_id(), request.actor_id());
      }
      return new DeleteFilmsActorResponse("01", "Eliminación exitosa");
    } catch (Exception e) {
      e.printStackTrace();
      return new DeleteFilmsActorResponse("99", "Error al eliminar relaciones: " + e.getMessage());
    }
  }
}