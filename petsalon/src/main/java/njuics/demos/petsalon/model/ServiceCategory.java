package njuics.demos.petsalon.model;


public enum ServiceCategory{
    Bathing("Bathing"),
    Cutting("Cutting"),
    Dyeing("Dyeing"),
    Dressing("Dressing"),
    Shopping("Shopping");

    private String discription;
    private ServiceCategory(String discription){
        this.discription = discription;
    }
    public void setDiscription(String discription){
        this.discription = discription;
    }
    public String getDiscription() {
        return discription;
    }
}