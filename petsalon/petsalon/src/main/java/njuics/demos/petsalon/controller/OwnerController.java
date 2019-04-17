package njuics.demos.petsalon.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import njuics.demos.petsalon.model.Owner;
import njuics.demos.petsalon.repository.OwnerRepository;

@RestController
public class OwnerController{
    private OwnerRepository repository;

    OwnerController(OwnerRepository repository){
        this.repository = repository;
    }
    // Aggregate root

    @GetMapping("/owners")
    Resources<Resource<Owner>> all() {
        List<Resource<Owner>> owners = repository.findAll().stream()
                .map(owner -> new Resource<>(owner,
                        linkTo(methodOn(OwnerController.class).one(owner.getId())).withSelfRel(),
                        linkTo(methodOn(OwnerController.class).all()).withRel("owners")))
                .collect(Collectors.toList());

        return new Resources<>(owners,
                linkTo(methodOn(OwnerController.class).all()).withSelfRel());
    }

    @PostMapping("/owners")
    Owner newOwner(@RequestBody Owner newOwner) {
        return repository.save(newOwner);
    }

    // Single item

    @GetMapping("/owners/{id}")
    Resource<Owner> one(@PathVariable Integer id) {
        Owner owner = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<>(owner,
                linkTo(methodOn(OwnerController.class).one(id)).withSelfRel(),
                linkTo(methodOn(OwnerController.class).all()).withRel("owners"));
    }

    @PutMapping("/owners/{id}")
    Owner replaceOwner(@RequestBody Owner newOwner, @PathVariable Integer id) {
        return repository.findById(id)
                .map(owner -> {
                    owner.setName(newOwner.getName());
                    return repository.save(owner);
                })
                .orElseGet(() -> {
                    newOwner.setId(id);
                    return repository.save(newOwner);
                });
    }

    @DeleteMapping("/owners/{id}")
    void deleteOwner(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}