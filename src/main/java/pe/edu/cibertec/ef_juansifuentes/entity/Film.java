package pe.edu.cibertec.ef_juansifuentes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer filmId;
  private String title;
  private String description;
  private Integer releaseYear;
  private Integer languageId;
  private Integer originalLanguageId;
  private Integer rentalDuration;
  private Double rentalRate;
  private Integer length;
  private Double replacementCost;
  private String rating;
  private String specialFeatures;
  private Date lastUpdate;

  @OneToMany(mappedBy = "film")
  private List<FilmActor> filmActors;
}
