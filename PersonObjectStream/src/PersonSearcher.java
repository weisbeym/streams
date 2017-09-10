
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yisroel Weisberg
 */
public class PersonSearcher{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File personsFile;
        ObjectInputStream inFile;
        String searchTerm;
        
        FileFilter filter = new FileNameExtensionFilter("Class set", "txt", "text", "csv");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(filter);
        chooser.setCurrentDirectory(new File("/"));
        ArrayList<Person> persons = new ArrayList<>();   
        Scanner console = new Scanner(System.in);
        try
        {
           if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
           {
               personsFile = chooser.getSelectedFile();
               inFile = new ObjectInputStream(new FileInputStream(personsFile));
               persons = (ArrayList<Person>) inFile.readObject();
               
               System.out.println("What would you like to search?");
               searchTerm = console.nextLine();
               
               for(Person person : persons){
                   if(person.getFullName().contains(searchTerm)){
                       System.out.println(person.toCSVDataRecord());
                   }
               }
               
               inFile.close();
           }
           else
           {
               System.out.println("You have to choose a file. Returning to menu...");
               return;
           }
        }catch(FileNotFoundException ex)
        {
            System.out.println("Error, could not open class file!");
            System.exit(0);
        }catch (IOException ex)
        {
            ex.getStackTrace();
            System.out.println("ERROR trying to read the file!");
            return;
        }catch(ClassNotFoundException ex){
            System.out.println("Could not read object");
            System.exit(0);
        }
    }
    
}
