public class Patient {

    private String firstName = "Empty";
    private String lasttName;
    private int age;
    private String city;
    private long id;
    private String vaccsType;
    public Patient(){

    }
    public  Patient(String firstName, String lasttName, int age, String city ,long id , int type){
        this.setFirstName(firstName);
        this.setLasttName(lasttName);
        this.setAge(age);
        this.setCity(city);
        this.setId(id);

        if (type == 1){
            this.setVaccsType("AstraZeneca");
        }
        else if (type == 2){
            this.setVaccsType("Sinopharm");
        }
        else{
            this.setVaccsType("Pfizer");
        }
    }

    public void setValues (String firstName, String lasttName, int age, String city ,long id , int type){
        this.setFirstName(firstName);
        this.setLasttName(lasttName);
        this.setAge(age);
        this.setCity(city);
        this.setId(id);

        if (type == 1){
            this.setVaccsType("AstraZeneca");
        }
        else if (type == 2){
            this.setVaccsType("Sinopharm");
        }
        else{
            this.setVaccsType("Pfizer");
        }
    }

    public void getValues(int boothNum){
        if (getFirstName().equals("Empty")){
            System.out.println("----------Booth number " + boothNum + " is Empty----------");
        }
        else {
            System.out.println("----------Booth number - " + boothNum + "----------");
            System.out.println("First Name: " + getFirstName());
            System.out.println("Surname   : " + getLasttName());
            System.out.println("Age       : " + getAge());
            System.out.println("City      : " + getCity());
            System.out.println("NIC or Passport Number: " + getId());
            System.out.println("Vaccination Requested: " + getVaccsType());
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaccsType() {
        return vaccsType;
    }

    public void setVaccsType(String vaccsType) {
        this.vaccsType = vaccsType;
    }
}
