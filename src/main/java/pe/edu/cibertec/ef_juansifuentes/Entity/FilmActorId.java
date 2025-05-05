package pe.edu.cibertec.ef_juansifuentes.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmActorId {

    private Integer actorId;
    private Integer filmId;

}
