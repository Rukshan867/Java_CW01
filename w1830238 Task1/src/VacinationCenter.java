
import java.io.*;
import java.util.Scanner;
public class VacinationCenter {

    private static int stock = 150;

    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);

    public static void main(String[] args) {




        int boothNum = 0;
        String[] booths = new String[6];
        //Initialising
        initialise(booths);

        boolean loop = false;
        while(!loop) {
            input = new Scanner(System.in);
            input2 = new Scanner(System.in);
            try {
                String option;
                //Displaying option menu
                System.out.println("Enter 100 or VVB: View all Vaccination Booths");
                System.out.println("Enter 101 or VEB: View all Empty Booths");
                System.out.println("Enter 102 or APB: Add Patient to a Booth");
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
                option = input2.nextLine().toLowerCase();
                //Option Menu
                switch (option) {

                    case "vvb":
                    case "100":
                        viewBooths(booths);
                        System.out.println(" ");
                        break;
                    case "veb":
                    case "101":
                        emptyBooths(booths);
                        System.out.println(" ");
                        break;
                    case "apb":
                    case "102":
                        addPatient(booths);

                        System.out.println(" ");
                        break;
                    case "rpb":
                    case "103":
                        removePatient(booths);
                        System.out.println(" ");
                        break;
                    case "vps":
                    case "104":
                        sortPatient(booths);
                        System.out.println(" ");
                        break;
                    case "spd":
                    case "105":

                        StoreData(booths,stock);
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
                        break;
                    default:
                        System.out.println("Please enter a valid option.");
                        System.out.println(" ");
                        break;



                }
                //Displaying a warning message when the stock reaches 20
                if (stock == 20) {
                    System.out.println("Warning only " + stock + " are Remaining.");
                    System.out.println(" ");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid input.... ");
                System.out.println(" ");

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
            System.out.println(" ");
        }

    }
    //View all Empty Booths method
    private static void emptyBooths(String[] array) {
        for (int x = 0; x < 6; x++) {
            if (array[x].equals("Empty")) {
                System.out.println("Booth " + x + " is Empty");
                System.out.println(" ");
            }
        }

    }
    //method to Add a patient to the booth
    private static void addPatient(String[] array){
        String name;
        int boothNum ;

        System.out.println("Enter a booth number to Add a Patient (0-5)");
        boothNum = input.nextInt();
        //if someone is already in the booth a message will display
        if (boothNum > 5){
            System.out.println("Please select a booth number between 0 and 5");

        }
        else if(!array[boothNum].equals("Empty")){
            System.out.println("This both is Occupied");

        }
        else {
            do {
                System.out.println("Enter the Patient name for the Booth " + boothNum + ": ");
                name = input2.nextLine();
            } while(name.isEmpty());
            array[boothNum] = name;
            stock--;
            System.out.println("Patient will be directed to the proper booth");
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
    //method to Sort Patients Names
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
    //Saving Program Data to a File
    private static void StoreData(String[] firstNameArr,int stock){
        try{
            FileWriter data = new FileWriter("Task1_Array.txt");


            for (int i=0; i < firstNameArr.length; i++){
                if (!firstNameArr[i].equals("Empty")) {
                    data.write("--------Booth Number " + i + "--------" +"\n");
                    data.write("First name : " + firstNameArr[i] + "\n");

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
            File load = new File("Task1_Array.txt");
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