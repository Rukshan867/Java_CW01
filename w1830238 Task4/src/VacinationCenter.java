import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class VacinationCenter {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int boothNum;
        //sortName Array is to store the Alphabetically sorted name list
        String[] sortName;
        //"sortNameCount" variable is to get the count of patients that currently getting vaccinated
        int sortNameCount = 0;
        //Following Variables will be used to store the User inputs
        String firstName;
        String lastName;
        int age;
        String city;
        long id;
        int vaccsReq;
        //This will help to keep the track of vaccination stock and add new stock
        Booth stock = new Booth();

        Booth[] centerArr = new Booth[6];
        centerArr[0] = new Booth(1);
        centerArr[1] = new Booth(1);
        centerArr[2] = new Booth(2);
        centerArr[3] = new Booth(2);
        centerArr[4] = new Booth(3);
        centerArr[5] = new Booth(3);

        Patient[] patientsArr = new Patient[6];
        patientsArr[0] = new Patient();
        patientsArr[1] = new Patient();
        patientsArr[2] = new Patient();
        patientsArr[3] = new Patient();
        patientsArr[4] = new Patient();
        patientsArr[5] = new Patient();

        LinkedList<Booth> waitingListArr = new LinkedList<>();
        LinkedList<Patient> waitingPatientList = new LinkedList<>();


        boolean loop = false;
        while (!loop) {
            try {
                input = new Scanner(System.in);
                input2 = new Scanner(System.in);
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
                System.out.println("Enter 109 or VPW: View Patient Waiting List");
                System.out.println("Enter 999 or EXT: Exit the Program");
                System.out.println(" ");

                //Getting the option from user
                System.out.println("Enter your Option: ");
                String option = input2.nextLine().toLowerCase();

                //Option Menu
                switch (option) {


                    case "vvb":
                    case "100":
                        for (int x = 0; x < patientsArr.length; x++) {
                            patientsArr[x].getValues(x);
                            System.out.println(" ");
                        }

                        System.out.println(" ");
                        break;

                    case "veb":
                    case "101":
                        for (int x = 0; x < centerArr.length; x++) {
                            centerArr[x].emptyBooth(x);
                        }
                        System.out.println(" ");
                        break;

                    case "apb":
                    case "102":

                        do {
                            System.out.println("Enter the First Name of the Patient  for the Booth: ");
                            firstName = input2.nextLine();
                        } while (firstName.isEmpty());

                        do {
                            System.out.println("Enter the Surname Name of the Patient: ");
                            lastName = input2.nextLine();
                        } while (lastName.isEmpty());

                        System.out.println("Enter the age of the Patient: ");
                        age = input.nextInt();

                        do {
                            System.out.println("Enter the City of the Patient: ");
                            city = input2.nextLine();
                        } while (city.isEmpty());

                        System.out.println("Enter the NIC or Passport Number of the Patient: ");
                        id = input.nextLong();
                        System.out.println("Enter the number of Requested vaccination ");
                        System.out.println("AstraZeneca - 1");
                        System.out.println("Sinopharm   - 2");
                        System.out.println("Pfizer      - 3");
                        vaccsReq = input.nextInt();
                        //Following Process will Add the patient to a booth right away or wil be added to a waiting List.
                        for (int x = 0; x < centerArr.length; x++) {

                            if (centerArr[x].getVaccsType() == vaccsReq) {
                                if (centerArr[x].getName().equals("Empty")) {
                                    centerArr[x].setName(firstName);
                                    System.out.println("Patient has been directed to the proper booth");
                                    patientsArr[x].setValues(firstName, lastName, age, city, id, vaccsReq);
                                    stock.setStock();
                                    sortNameCount++;
                                    break;
                                } else if (centerArr[x + 1].getName().equals("Empty") ) {
                                    centerArr[x + 1].setName(firstName);
                                    System.out.println("Patient will be directed to the proper booth");
                                    patientsArr[x + 1].setValues(firstName, lastName, age, city, id, vaccsReq);
                                    stock.setStock();
                                    sortNameCount++;
                                    break;
                                } else {
                                    System.out.println("Requested Vaccination type Booths are Occupied Patient will be added to a waiting list");
                                    //Adding the Patient to a waiting List Till an opening comes in the requested vaccination type booths
                                    waitingListArr.add(new Booth(firstName,vaccsReq));
                                    //Adding the Addition information about the Patient to a temporarily list till he finds a booth
                                    //After the Patient finds  proper booth this Information will be available in the System.
                                    waitingPatientList.add(new Patient(firstName,lastName,age,city,id,vaccsReq));
                                    break;
                                }

                            }

                        }
                        System.out.println(" ");

                        break;

                    case "rpb":
                    case "103":
                        System.out.println("Enter a booth number to Remove a Patient (0-5)");
                        boothNum = input.nextInt();
                        if (centerArr[boothNum].getName().equals("Empty")) {
                            System.out.println("This Booth is already Empty");
                        } else {
                            //This process will remove a patient from the chosen booth and if the empty booth vaccination
                            // brand is matching to a patient that waits in the waiting list he will be added to the booth.
                            centerArr[boothNum].setName("Empty");
                            patientsArr[boothNum].setFirstName("Empty");
                            sortNameCount--;
                            System.out.println("Patient will be removed from the booth");
                            for (int x = 0; x < waitingListArr.size(); x++){
                                //Patients in the waiting will be only add to the booth if the requested vaccinations booths are empty
                                if (centerArr[boothNum].getVaccsType() == waitingListArr.get(x).getVaccsType()){
                                    sortNameCount++;
                                    stock.setStock();
                                    System.out.println("New Patient will be add to the booth");
                                    centerArr[boothNum].setName(waitingListArr.get(x).getName());
                                    //Setting Additional information about the New patient to the System
                                    patientsArr[boothNum].setFirstName(waitingPatientList.get(x).getFirstName());
                                    patientsArr[boothNum].setLasttName(waitingPatientList.get(x).getLasttName());
                                    patientsArr[boothNum].setAge(waitingPatientList.get(x).getAge());
                                    patientsArr[boothNum].setCity(waitingPatientList.get(x).getCity());
                                    patientsArr[boothNum].setId(waitingPatientList.get(x).getId());
                                    patientsArr[boothNum].setVaccsType(waitingPatientList.get(x).getVaccsType());
                                    //Removing the Patient From the waiting List
                                    waitingListArr.remove(x);
                                    waitingPatientList.remove(x);
                                    break;

                                }

                            }


                        }
                        System.out.println(" ");
                        break;

                    case "vps":
                    case "104":
                        sortName = new String[sortNameCount];
                        int y = 0;
                        //Creating an new list for the patient that currently getting vaccinated
                        for (Booth arr : centerArr) {
                            if (!arr.getName().equals("Empty")) {
                                sortName[y] = arr.getName();
                                y++;
                            }
                        }
                        //Sorting list of names
                        for (int n = 0; n < sortName.length; n++) {
                            for (int x = n + 1; x < sortName.length; x++) {
                                String temp = sortName[x];

                                int num = sortName[n].compareToIgnoreCase(sortName[x]);
                                if (num > 0) {
                                    sortName[x] = sortName[n];
                                    sortName[n] = temp;
                                }
                            }
                        }
                        //Displaying the Sorted names list
                        for (String sort : sortName) {
                            System.out.println(sort);
                        }
                        System.out.println(" ");
                        break;

                    case "spd":
                    case "105":


                        try{
                            FileWriter data = new FileWriter("Task4.txt");


                            for (int i=0; i < centerArr.length; i++){
                                if (!centerArr[i].getName().equals("Empty")) {
                                    data.write("----------------Booth Number " + i + "----------------" +"\n");
                                    data.write("First name      : " + patientsArr[i].getFirstName() + "\n");
                                    data.write("Surname         : " + patientsArr[i].getLasttName() + "\n");
                                    data.write("Age             : " + patientsArr[i].getAge() + "\n");
                                    data.write("City            : " + patientsArr[i].getCity() + "\n");
                                    data.write("Nic or Passport : " + patientsArr[i].getId() + "\n");
                                    data.write(  "Vaccination Type : " + patientsArr[i].getVaccsType()  + "\n");
                                    data.write("\n ");
                                }
                                else{
                                    data.write("--------Booth Number " + i + "--------" +"\n");
                                    data.write("This Booth is empty" + "\n");
                                    data.write("\n ");
                                }
                            }
                            data.write("Remaining vaccine amount is: " + stock.getStock() + "\n\n");
                            data.close();
                            System.out.println("Successfully wrote to the file.");
                            System.out.println(" ");
                        } catch (IOException e){
                            System.out.println("An error occurred.");
                            System.out.println(" ");
                            e.printStackTrace();
                        }

                        break;
                    case "lpd":
                    case "106":


                        try {
                            File load = new File("Task4.txt");
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


                        break;

                    case "vrv":
                    case "107":
                        //Assigning the remaining stock to the Variable "left"
                        int left = stock.getStock();
                        System.out.println("Remaining vaccine amount is: " + left);
                        System.out.println(" ");
                        break;


                    case "avs":
                    case "108":
                        //Getting Amount from the User to Add the Vaccination stock
                        System.out.println("Enter the amount of vaccinations adding to the Stock: ");
                        int amount = input.nextInt();
                        stock.setStock(amount);
                        break;


                    case "vpw":
                    case "109":
                        //This will help the User to check the patient in the waiting list
                        if (waitingPatientList.size() > 0) {
                            for (int i = 0; i < waitingPatientList.size(); i++) {
                                System.out.println("-----------Waiting List Patient No " + (i +1) + "-----------");
                                System.out.println("First Name: " + waitingPatientList.get(i).getFirstName());
                                System.out.println("Surname   : " + waitingPatientList.get(i).getLasttName());
                                System.out.println("Age       : " + waitingPatientList.get(i).getAge());
                                System.out.println("City      : " + waitingPatientList.get(i).getCity());
                                System.out.println("NIC or Passport Number: " + waitingPatientList.get(i).getId());
                                System.out.println("Vaccination Requested: " + waitingPatientList.get(i).getVaccsType());
                                System.out.println(" ");
                            }
                        }
                        else {
                            System.out.println("There are not any Patients in the waiting List");
                        }
                        System.out.println(" ");
                        break;


                    case "ext":
                    case "999":
                        //Ending the program by Breaking the Loop
                        loop = true;
                        break;

                    default:
                        System.out.println("Please enter a valid option.");
                        System.out.println(" ");


                }
                //Displaying a warning message when the stock reaches 20
                if (stock.getStock() == 20) {
                    System.out.println("Warning only 20 vaccines are Remaining.");
                    System.out.println(" ");
                }
            }catch (Exception e) {
                System.out.println("Please enter a valid input.... ");
            }
        }



    }
}
