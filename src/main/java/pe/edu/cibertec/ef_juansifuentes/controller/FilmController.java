package pe.edu.cibertec.ef_juansifuentes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ef_juansifuentes.dto.ActorDto;
import pe.edu.cibertec.ef_juansifuentes.dto.FilmDto;
import pe.edu.cibertec.ef_juansifuentes.entity.Film;
import pe.edu.cibertec.ef_juansifuentes.form.FilmSearchForm;
import pe.edu.cibertec.ef_juansifuentes.service.FilmService;

@Controller
@RequestMapping("/film")
public class FilmController {

  @Autowired
  FilmService filmService;

  @GetMapping("/start")
  public String start(Model model) {
   try{
        Iterable<ActorDto> cboActors = filmService.getActors();
        model.addAttribute("actors", cboActors);
        model.addAttribute("filmSearchForm", new FilmSearchForm());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "main";
  }

  @PostMapping("/search")
  public String search(@ModelAttribute FilmSearchForm filmSearchForm, Model model) {
    try {
      int actorId = Integer.parseInt(filmSearchForm.getActorId());
      Iterable<FilmDto> iterable = null;
      if (actorId > 0)
        iterable = filmService.searchFilmByActor(actorId);
       else
        iterable = filmService.searchAllFilms();

       Iterable<ActorDto> cboActors = filmService.getActors();
       model.addAttribute("cboActors", cboActors);
       model.addAttribute("films", iterable);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "main";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, Model model) {
    try {
      Film film = filmService.findFilmById(id);
      int currentActorId = filmService.getActorIdByFilmId(id); // Nuevo método
      model.addAttribute("film", film);
      model.addAttribute("actors", filmService.getActors());
      model.addAttribute("currentActorId", currentActorId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "edit";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute Film film, @RequestParam("actorId") Integer actorId) {
    try {
      filmService.updateFilmWithActor(film, actorId); // Nuevo método
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/film/start";
  }



}
