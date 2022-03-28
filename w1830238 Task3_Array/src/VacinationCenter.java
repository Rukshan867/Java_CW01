
import java.io.*;
import java.util.Scanner;
public class VacinationCenter{

    private static int stock = 150;

    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);

    public static void main(String[] args) {



        String firstName;
        String lastName;
        int boothNum = 0;
        String[] firstNameArr = new String[6];
        String[] lastNameArr = new String[6];

        //Initialising
        initialise(firstNameArr);

        boolean loop = false;
        while (!loop) {
            try {
                //Displaying option menu
                System.out.println("Enter 100 or VVB: View all Vaccination Booths");
                System.out.println("Enter 101 or VEB: View all Empty Booths");
                System.out.println("Enter 102 or APB:  Add Patient to a Booth");
                System.out.println("Enter 103 or RPB: Remove Patient from a Booth");
                System.out.println("Enter 104 or VPS: View Patients Sorted in alphabetical order");
                System.out.println("Enter 105 or SPD: Store Program Data into file");
                System.out.println("Enter 106 or LPD: Load Program Data from file");
                System.out.println("Enter 107 or VRV: View Remaining Vaccinations");
                System.out.println("Enter 108 or AVS: Add Vaccinations to the Stock");
                System.out.println("Enter 999 or EXT: Exit the Program");

                System.out.println(" ");
                //Getting the option from user
                System.out.println("Enter your Option: ");
                String option = input2.nextLine().toLowerCase();

                //Option Menu
                switch (option) {
                    case "vvb":
                    case "100":
                        getPatientInfo(firstNameArr,lastNameArr);
                        System.out.println(" ");
                        break;
                    case "veb":
                    case "101":
                        emptyBooths(firstNameArr);
                        System.out.println(" ");
                        break;
                    case "apb":
                    case "102":
                        do {
                            System.out.println("Enter the First Name of the Patient  for the Booth :");
                            firstName = input2.nextLine();
                        } while (firstName.isEmpty());

                        do {
                            System.out.println("Enter the Surname Name of the Patient: ");
                            lastName = input2.nextLine();
                        } while (lastName.isEmpty());

                        System.out.println("Enter the number of Requested vaccination " );
                        System.out.println("AstraZeneca - 1" );
                        System.out.println("Sinopharm   - 2" );
                        System.out.println("Pfizer      - 3" );
                        int req = input.nextInt();

                        addPatient(firstNameArr,req,firstName);
                        setPatientInfo(firstNameArr,lastNameArr,firstName,lastName);

                        System.out.println(" ");
                        break;

                    case "rpb":
                    case "103":
                        removePatient(firstNameArr);
                        System.out.println(" ");
                        break;
                    case "vps":
                    case "104":
                        sortPatient(firstNameArr);
                        System.out.println(" ");
                        break;
                    case "spd":
                    case "105":
                        StoreData(firstNameArr,lastNameArr,stock);
                        break;
                    case "lpd":
                    case "106":
                        LoadData();
                        break;

                    case "vrv":
                    case "107":
                        remainingVacc(stock);
                        System.out.println(" ");
                        break;
                    case "avs":
                    case "108":
                        stock = addVaccs(stock);
                        System.out.println(" ");
                        break;

                    case "ext":
                    case "999":
                        loop = true;
                    default:
                        System.out.println("Please enter a valid option.");
                        System.out.println(" ");

                }
                //Displaying a warning message when the stock reaches 20
                if (stock == 20) {
                    System.out.println("Warning only " + stock + " are Remaining.");
                    System.out.println(" ");
                }
            }catch (Exception e) {
                System.out.println("Please enter a valid input.... ");
            }
        }



    }

    private static void initialise(String[] array) {
        for (int x = 0; x < 6; x++) array[x] = "Empty";
        System.out.println("initilise ");
    }
    //method to View all Vaccination Booths
    private static void viewBooths(String[] array){
        for (int x = 0; x < 6; x++){
            if (array[x].equals("Empty")){
                System.out.println("Booth " + x + " is Empty");
            }
            else {
                System.out.println("Booth " + x +" is Occupied by " + array[x] );
            }
        }

    }
    //View all Empty Booths method
    private static void emptyBooths(String[] array) {
        for (int x = 0; x < 6; x++) {
            if (array[x].equals("Empty")) {
                System.out.println("Booth " + x + " is Empty");
            }
        }

    }
    //method to Add a patient to the booth
    private static void addPatient(String[] array, int requested, String name){
        int[] vaccs = {1,1,2,2,3,3};
        for (int x=0; x < vaccs.length; x++){
            if(requested ==vaccs[x]) {
                if (array[x].equals("Empty")) {
                    array[x] = name;
                    System.out.println("Patient has been directed to the proper booth");
                    stock--;
                    break;
                } else if (array[x + 1].equals("Empty")) {
                    array[x + 1] = name;
                    System.out.println("Patient will be directed to the proper booth");
                    stock--;
                    break;
                } else {
                    System.out.println("Requested Vaccination type Booths are Occupied");
                    break;
                }
            }

        }



    }
    //method to Remove a patient from the booth
    private static void removePatient(String[] array){
        int boothNum ;

        System.out.println("Enter a booth number to Remove a Patient (0-5)");
        boothNum = input.nextInt();

        if (boothNum > 5){
            System.out.println("Please select a booth number between 0 and 5");
        }
        else if (array[boothNum].equals("Empty")) {
            System.out.println("This Booth is already Empty");
        }
        else {
            array[boothNum] = "Empty";
            System.out.println("Patient will be removed from the booth");
        }

    }
    //method
    private static void sortPatient(String[] array){
        //Counting the number of Patient that currently getting vaccinated
        int nameCount = 0;
        for(int n=0; n < array.length; n++){
            if(!array[n].equals("Empty")){
                nameCount +=1;
            }
        }
        //Creating an new list for the patient that currently getting vaccinated
        int q=0;
        String[] sortNames = new String[nameCount];
        for(int x=0; x< array.length; x++){

            if(!array[x].equals("Empty")){
                sortNames[q] = array[x];
                q ++;
            }
        }
        //Sorting list of names
        for(int n=0; n< sortNames.length; n++){
            for(int x = n + 1; x< sortNames.length; x++){
                String temp = sortNames[x];

                int num = sortNames[n].compareToIgnoreCase(sortNames[x]);
                if (num > 0){
                    sortNames[x] = sortNames[n];
                    sortNames[n] = temp ;
                }
            }
        }

        //Displaying the Sorted names list
        for(int n=0; n< sortNames.length; n++){
            System.out.println(sortNames[n]);
        }


    }

    private static void remainingVacc(int x){
        System.out.println("Remaining vaccine amount is: " + x);
    }

    private static int addVaccs(int stock){
        System.out.println("Enter the amount of vaccinations adding to the Stock: ");
        int amount = input.nextInt();

        stock += amount;
        return stock;
    }

    private static void setPatientInfo(String[] array, String[] lastName,String name, String LName) {


        for (int x = 0; x < array.length; x++) {
            if (array[x].equals(name)) {
                lastName[x] = LName;
                break;
            }
        }
    }

    private static void getPatientInfo(String[] array, String[] lastName ){
        String[] vaccType = {"AstraZeneca", "AstraZeneca", "Sinopharm", "Sinopharm", "Pfizer", "Pfizer"};
        for (int n=0; n < array.length; n++){
            if (!array[n].equals("Empty")){
                System.out.println("Booth number  - " + n);
                System.out.println("First Name is " + array[n]);
                System.out.println("Last Name is " + lastName[n]);
                System.out.println("Requested Vacation is " + vaccType[n]);
                System.out.println(" ");
            }
            else {
                System.out.println("Booth number  - " + n);
                System.out.println("This Booth is empty");
                System.out.println(" ");
            }
        }


    }
    //Saving Program Data to a File
    private static void StoreData(String[] firstNameArr, String[] lastNameArr, int stock){
        try{
            FileWriter data = new FileWriter("Task3_Array.txt");

            String[] vaccType = {"AstraZeneca", "AstraZeneca", "Sinopharm", "Sinopharm", "Pfizer", "Pfizer"};
            for (int i=0; i < firstNameArr.length; i++){
                if (!firstNameArr[i].equals("Empty")) {
                    data.write("--------Booth Number " + i + "--------" +"\n");
                    data.write("First name : " + firstNameArr[i] + "\n");
                    data.write("Surname : " + lastNameArr[i] + "\n");
                    data.write("Vaccination Type: " + vaccType[i] + "\n");
                    data.write("\n ");
                }
                else{
                    data.write("--------Booth Number " + i + "--------" +"\n");
                    data.write("This Booth is empty" + "\n");
                    data.write("\n ");
                }
            }
            data.write("Remaining vaccine amount is: " + stock + "\n\n");
            data.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println(" ");
        } catch (IOException e){
            System.out.println("An error occurred.");
            System.out.println(" ");
            e.printStackTrace();
        }
    }

    //Retrieving Program data from the File
    private static void LoadData(){

        try {
            File load = new File("Task3_Array.txt");
            if (load.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(load));

                String st;
                while ((st = br.readLine()) != null) {
                    System.out.println(st);
                    System.out.print("");
                }

            } else {
                System.out.println("The file does not exist.");
            }

        } catch (IOException e){
            System.out.println("An error occurred.");
            System.out.println(" ");
            e.printStackTrace();
        }
    }

}