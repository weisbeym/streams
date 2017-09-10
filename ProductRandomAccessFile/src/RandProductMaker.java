
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author yismo
 */
public class RandProductMaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int choice = 0;
        choice = SafeInput.getRangedInt(console, "would you like to write(1) a file or search within a file(2)?", 1, 2);
        if(choice == 1){
            writeToFile();
        }if(choice == 2){
            readFile();
        }
    }
    
    public static void writeToFile(){
        ArrayList<Product> products = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);
        String fileName = "";
        String ID = "";
        String name = "";
        String description = "";
        int price;
        int productCount = 0;
        
        try{
            System.out.println("What will the file name be?");
            fileName = in.nextLine();
            while(!done){
                productCount ++;
                System.out.println("Enter the ID of product number " + productCount + " : ");
                ID = in.nextLine();
                System.out.println("Enter the name of the product: ");
                name = in.nextLine();
                System.out.println("Enter a description: ");
                description = in.nextLine();
                price = SafeInput.getRangedInt(in, "Enter the price: ", 0, 10000000);
                products.add(new Product(ID, name, description, price));
                done = SafeInput.getYNConfirm(in, "add another product?");
                done = !done;
            }
            
            //creates file for the persons ArrayLists
            RandomAccessFile f = new RandomAccessFile(fileName + "txt", "rw");
            for(Product p : products){
                f.write(p.processProductForOutput().getBytes());
            }
            
            f.close();
            
        }catch(FileNotFoundException ex){
            System.out.println("Error, could not create output file!");
            System.exit(0);
        }catch (IOException ex)
        {
            ex.getStackTrace();
            System.exit(0);
        }
    }

    private static void readFile() {
        File f;
        int rows;
        String str, searchTerm;
        String retVal = "";
        Scanner console = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        String fileHeader = String.format("%-5s%-16s%-64s%-8s", "ID", "Name", "Desc.", "Price");
        FileFilter filter = new FileNameExtensionFilter("Class set", "txt", "text", "csv");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(filter);
        chooser.setCurrentDirectory(new File("/"));
        
        try
        {
           if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
           {
               f = chooser.getSelectedFile();
               RandomAccessFile raf = new RandomAccessFile(f, "r");
               raf.seek(0);
               str = raf.readLine();
               rows = (int) (str.length()/Product.PARSED_LENGTH);
               
               for(int i = 0; i < rows; i++){
                   products.add(new Product(str.substring(i * 126, (i + 1) * 126)));
               }    
               raf.close();
               
               System.out.println("What would you like to search for?");
               searchTerm = console.nextLine();
               
               
               
               for(Product product : products){
                   if(product.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                       System.out.println(product.toCSVDataRecord());
                       
                   }
               }
           }
           else
           {
               System.out.println("You have to choose a file. Returning to menu...");
               return;
           }
        }
        
        catch(FileNotFoundException ex)
        {
            System.out.println("Error, could not open class file!");
            System.exit(0);
        }    
        catch (IOException ex)
        {
            ex.getStackTrace();
            System.out.println("ERROR trying to read the file!");
            return;
        }
    }
    
        static String formatRecordDisplay(int recordCount, String record) 
    {
        String ret = "";
        String [] recordSplit = record.split(", ");
        // "Tom Bombadil, 100, 90"
            ret += String.format("%-5s", recordSplit[0]);
            ret += String.format("%-16s", recordSplit[1]);
            ret += String.format("%-64s", recordSplit[2]);
            ret += String.format("%-8s", recordSplit[3]);
        return ret;
        
    }
}
