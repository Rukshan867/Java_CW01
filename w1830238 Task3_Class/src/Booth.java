
public class Booth {

    private int stock = 150;
    private  String name = "Empty";
    private int vaccsType;

    public Booth(){

    }
    //This Constructor will get the assigned value for the Vaccination type( 1-AstraZeneca, 2-Sinopharm, 3-Pfizer
    public Booth(int type ){
        this.vaccsType = type;
    }

    //This is a method to view the situation of the booth
    public void viewBooth(int x){
        if (name.equals("Empty")){
            System.out.println("Booth number " + x + " is Empty.");
        }
        else {
            System.out.println("Booth number " + x +" is Occupied by " + name);
        }
    }

    //This method will identify empty booth
    public void emptyBooth(int x){
        if (name.equals("Empty")){
            System.out.println("Booth number " + x + " is Empty.");
        }
    }

    //This method will return the value that belongs to the assigned Vaccination type
    public int getVaccsType(){
        return vaccsType;
    }

    //This method will return the Name
    public String getName(){
        return name;
    }
    //This method will set the New Name
    public void setName(String name){
        this.name = name;
    }

    //Reducing the vaccine Stock
    public void setStock(){
        this.stock --;
    }
    //Adding new Vaccines to the Stock
    public void setStock(int num){
        this.stock += num;
    }
    //Returning the remaining Vaccine Stock
    public int getStock(){
        return stock;
    }

}

