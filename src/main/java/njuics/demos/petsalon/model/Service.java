package njuics.demos.petsalon.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Service{
    private @Id @GeneratedValue Integer id;
    private Date date;
    private Double fee;
    private ServiceCategory category;
    private Pet pet;//对应宠物

    Service(Date date,Double fee,ServiceCategory category,Pet pet){
        this.date = date;
        this.fee = fee;
        this.category = category;
        this.pet = pet;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setFee(Double fee) {
        this.fee = fee;
    }
    public void setCategory(ServiceCategory category) {
        this.category = category;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Double getFee() {
        return fee;
    }
    public Pet getPet() {
        return pet;
    }
    public ServiceCategory getCategory() {
        return category;
    }
}
