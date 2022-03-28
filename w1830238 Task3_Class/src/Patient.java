public class Patient {

    private String firstName = "Empty";
    private String lasttName;
    private int age;
    private String city;
    private long id;
    private String vaccsType;
    public Patient(){

    }

    public void setValues (String firstName, String lasttName, int age, String city ,long id , int type){
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.age = age;
        this.city = city ;
        this.id = id;

        if (type == 1){
            this.vaccsType = "AstraZeneca";
        }
        else if (type == 2){
            this.vaccsType = "Sinopharm";
        }
        else{
            this.vaccsType = "Pfizer";
        }
    }

    public void getValues(int boothNum){
        if (firstName.equals("Empty")){
            System.out.println("---------Booth number " + boothNum + " is Empty---------");
        }
        else {
            System.out.println("---------Booth number " + boothNum + "---------");
            System.out.println("First Name: " + firstName);
            System.out.println("Surname   : " + lasttName);
            System.out.println("Age       : " + age);
            System.out.println("City      : " + city);
            System.out.println("NIC or Passport Number: " + id);
            System.out.println("Vaccination Requested: " + vaccsType);
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public long getId() {
        return id;
    }

    public String getVaccsType() {
        return vaccsType;
    }
}
