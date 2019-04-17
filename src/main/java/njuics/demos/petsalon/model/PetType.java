package njuics.demos.petsalon.model;



public enum PetType{
    Dog("Dog"),
    Cat("Cat"),
    Hamster("Hamster"),
    Bird("Bird");

    private String discription;

    private PetType(String discription){
        this.discription = discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }
}