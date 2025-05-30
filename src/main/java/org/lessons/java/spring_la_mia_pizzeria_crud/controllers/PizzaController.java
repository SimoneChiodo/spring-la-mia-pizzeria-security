package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Discount;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
  
  // @Autowired
  // private PizzaRepository repository;
  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private IngredientRepository ingredientRepository;

  // GET
  @GetMapping()
  public String pizza(Authentication authentication, Model model) {
    List<Pizza> pizze = pizzaService.findAll(); 

    model.addAttribute("pizze", pizze);
    model.addAttribute("username", authentication.getName());

    return "pizza/pizza";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    Pizza pizza = pizzaService.getById(id); 
    
    model.addAttribute("pizza", pizza);

    return "pizza/show";
  }
  
  // SEARCH
  @GetMapping("/search")
  public String search(@RequestParam(name = "title") String title, Model model) {
    List<Pizza> pizze = pizzaService.findByTitle(title); 
    
    model.addAttribute("pizze", pizze);

    return "pizza/pizza";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    Pizza pizza = new Pizza(); 

    pizza.setUrlImmagine("https://picsum.photos/500/300"); // Creo un URL di default per l'immagine

    model.addAttribute("pizza", pizza);

    //Aggiungo gli ingredienti da selezionare
    List<Ingredient> ingredienti = ingredientRepository.findAll();
    model.addAttribute("ingredienti", ingredienti);

    return "pizza/create";
  }

  // STORE
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "pizza/create"; // Se ci sono errori, torno alla pagina di creazione
    }

    // Se non ci sono errori, salvo la pizza nel DB
    pizzaService.create(formPizza); 

    return "redirect:/pizza"; //Ritorno alla index
  }

  // EDIT
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Pizza pizza = pizzaService.getById(id); 

    model.addAttribute("pizza", pizza);

    //Aggiungo gli ingredienti da selezionare
    List<Ingredient> ingredienti = ingredientRepository.findAll();
    model.addAttribute("ingredienti", ingredienti);

    return "pizza/edit";
  }
  
  // UPDATE
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "pizza/edit"; // Se ci sono errori, torno alla pagina di modifica
    }
    
    // Se non ci sono errori, aggiorno la pizza nel DB
    pizzaService.update(formPizza); 

    //Ritorno alla pagina della pizza modificata
    return "redirect:/pizza/" + formPizza.getId(); 
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    Pizza pizza = pizzaService.getById(id);

    pizzaService.delete(pizza); 

    return "redirect:/pizza"; //Ritorno alla index
  }

  // DISCOUNT CREATE 
  @GetMapping("/{id}/discount")
  public String sconto(@PathVariable Integer id, Model model) {
    Discount sconto = new Discount(); // Creo un nuovo oggetto sconto
    Pizza pizza = pizzaService.getById(id); 
    sconto.setPizza(pizza); // Associo la pizza allo sconto

    model.addAttribute("sconto", sconto); // Aggiungo lo sconto al model

    return "discount/create";
  }

}
