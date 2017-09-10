
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yisroel Weisberg
 */
public class PersonStreamMaker{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        boolean doneEntering = false;
        File personsFile;
        ObjectOutputStream out;
        Scanner in = new Scanner(System.in);
        String fileName = "";
        String firstName = "";
        String lastName = "";
        String ID = "";
        String title = "";
        int YOB;
        
        int personCount = 0;
        
        try{
            System.out.println("What will the file name be?");
            fileName = in.nextLine();
            
            //stores the info of persons
            while(!doneEntering){
                personCount ++;
                
                System.out.println("Enter the ID of person number " + personCount + " : ");
                ID = in.nextLine();
                System.out.println("Enter his/her first name: ");
                firstName = in.nextLine();
                System.out.println("Enter his/her last name: ");
                lastName = in.nextLine();
                System.out.println("Enter his/her Title: ");
                title = in.nextLine();
                YOB = SafeInput.getRangedInt(in, "Enter his/her year of birth: ", -5000, 5000);
                persons.add(new Person(ID, firstName, lastName, title, YOB));
                doneEntering = SafeInput.getYNConfirm(in, "add another person?");
                doneEntering = !doneEntering;
            }
            
            //creates file for the persons ArrayLists
            personsFile = new File(fileName + ".txt");
            out = new ObjectOutputStream(new FileOutputStream(personsFile));

            out.writeObject(persons);
            
            out.close();
            System.out.println("File has been generated");
            
        }catch(FileNotFoundException ex){
            System.out.println("Error, could not create output file!");
            System.exit(0);
        }catch (IOException ex)
        {
            ex.getStackTrace();
            System.exit(0);
        }
    }
    
}
