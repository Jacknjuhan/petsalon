package njuics.demos.petsalon.repository;

import org.springframework.data.repository.CrudRepository;
import njuics.demos.petsalon.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {

}
