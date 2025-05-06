package pe.edu.cibertec.ef_juansifuentes.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmActor {

  @EmbeddedId
  private FilmActorId filmActorId;
  private Date lastUpdate;

  @ManyToOne
  @MapsId("actorId")
  @JoinColumn(name = "actor_id")
  private Actor actor;


  @ManyToOne
  @MapsId("filmId")
  @JoinColumn(name = "film_id")
  private Film film;
}
