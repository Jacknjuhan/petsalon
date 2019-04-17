package njuics.demos.petsalon.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import njuics.demos.petsalon.model.Pet;
import njuics.demos.petsalon.repository.PetRepository;
@Controller
public class PetController{
    private PetRepository repository;

    PetController(PetRepository repository){
        this.repository = repository;
    }
    // Aggregate root

    @GetMapping("/pets")
    Resources<Resource<Pet>> all() {
        List<Resource<Pet>> pets = repository.findAll().stream()
                .map(pet -> new Resource<>(pet,
                        linkTo(methodOn(PetController.class).one(pet.getId())).withSelfRel(),
                        linkTo(methodOn(PetController.class).all()).withRel("pets")))
                .collect(Collectors.toList());

        return new Resources<>(pets,
                linkTo(methodOn(PetController.class).all()).withSelfRel());
    }

    @PostMapping("/pets")
    Pet newPet(@RequestBody Pet newPet) {
        return repository.save(newPet);
    }

    // Single item

    @GetMapping("/pets/{id}")
    Resource<Pet> one(@PathVariable Integer id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<>(pet,
                linkTo(methodOn(PetController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PetController.class).all()).withRel("pets"));
    }

    @PutMapping("/pets/{id}")
    Pet replacePet(@RequestBody Pet newPet, @PathVariable Integer id) {

        return repository.findById(id)
                .map(pet -> {
                    pet.setName(newPet.getName());
                    return repository.save(pet);
                })
                .orElseGet(() -> {
                    newPet.setId(id);
                    return repository.save(newPet);
                });
    }

    @DeleteMapping("/pets/{id}")
    void deletePet(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}