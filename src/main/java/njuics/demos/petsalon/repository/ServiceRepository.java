package njuics.demos.petsalon.repository;

import org.springframework.data.repository.CrudRepository;
import njuics.demos.petsalon.model.Service;

public interface ServiceRepository extends CrudRepository<Service, Integer> {

}