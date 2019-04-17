package njuics.demos.petsalon.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pet{
    private @Id @GeneratedValue Integer id;
    private String name;
    private PetType type;
    private Owner owner;
    private List<Service> serviceList;//服务列表
    //init
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(PetType type) {
        this.type = type;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
    //interface
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public PetType getType() {
        return type;
    }
    public Owner getOwner() {
        return owner;
    }
    public List<Service> getServiceList() {
        return serviceList;
    }
}