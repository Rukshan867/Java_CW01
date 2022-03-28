
public class Booth {

    private  int stock = 150;
    private String name = "Empty";


    public Booth(){

    }



    //This is a method to view the situation of the booth
    public  void viewBooth(int x){
        if (name.equals("Empty")){
            System.out.println("Booth number " + x + " is Empty.");
        }
        else {
            System.out.println("Booth number " + x +" is Occupied by " + name);
        }
    }

    //This method will identify empty booth
    public  void emptyBooth(int x){
        if (name.equals("Empty")){
            System.out.println("Booth number " + x + " is Empty.");
        }
    }



    //This method will return the Name
    public  String getName(){
        return name;
    }
    //This method will set the New Name
    public  void setName(String n){
        name = n;
    }

    //Reducing the vaccine Stock
    public  void setStock(){
        stock --;
    }
    //Adding new Vaccines to the Stock
    public  void setStock(int num){
        stock += num;
    }
    //Returning the remaining Vaccine Stock
    public int getStock(){
        return stock;
    }

}

