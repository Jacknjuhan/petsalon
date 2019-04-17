package njuics.demos.petsalon.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import njuics.demos.petsalon.model.Service;
import njuics.demos.petsalon.repository.ServiceRepository;


@RestController
public class ServiceController{
    private ServiceRepository repository;

    ServiceRepository(ServiceRepository repository){
        this.repository = repository;
    }
    // Aggregate root

    @GetMapping("/services")
    Resources<Resource<Service>> all() {
        List<Resource<Service>> services = repository.findAll().stream()
                .map(service -> new Resource<>(service,
                        linkTo(methodOn(ServiceController.class).one(service.getId())).withSelfRel(),
                        linkTo(methodOn(ServiceController.class).all()).withRel("services")))
                .collect(Collectors.toList());

        return new Resources<>(services,
                linkTo(methodOn(PetController.class).all()).withSelfRel());
    }

    @PostMapping("/services")
    Service newService(@RequestBody Service newService) {
        return repository.save(newService);
    }

    // Single item

    @GetMapping("/services/{id}")
    Resource<service> one(@PathVariable Integer id) {
        Service service = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<>(service,
                linkTo(methodOn(ServiceController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ServiceController.class).all()).withRel("services"));
    }

    @PutMapping("/services/{id}")
    Service replaceOwner(@RequestBody Service newService, @PathVariable Integer id) {

        return repository.findById(id)
                .map(service -> {
                    service.setName(newService.getName());
                    return repository.save(service);
                })
                .orElseGet(() -> {
                    service.setId(id);
                    return repository.save(newService);
                });
    }

    @DeleteMapping("/services/{id}")
    void deleteService(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}