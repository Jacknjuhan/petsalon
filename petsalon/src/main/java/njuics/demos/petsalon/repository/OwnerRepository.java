package njuics.demos.petsalon.repository;

import org.springframework.data.repository.CrudRepository;
import njuics.demos.petsalon.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

}