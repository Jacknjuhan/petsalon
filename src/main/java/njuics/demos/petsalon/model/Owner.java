package njuics.demos.petsalon.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Owner{
    private @Id @GeneratedValue Integer id;
    private String name;
    private Set<Pet> petSet;//宠物集合

    //init
    Owner(String name,Set<Pet> petSet){
        this.name = name;
        this.petSet = petSet;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPetSet(Set<Pet> petSet){
        this.petSet = petSet;
    }
    //interface
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Pet> getPetSet() {
        return petSet;
    }
}