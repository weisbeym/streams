import java.io.Serializable;
import java.util.Calendar;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yisroel Weisberg
 */
public class Person implements Serializable {
    private String ID;
    private String firstName;
    private String lastName;
    private String title;
    private int YOB;
    
    Calendar calendar = Calendar.getInstance();

    //constructors
    public Person(String ID, String firstName, String lastName, String title, int YOB) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }
    
    public Person(String line){
        String[] data = line.split(",");
        this.ID = data[0];
        this.firstName = data[1];
        this.lastName = data[2];
        this.title = data[3];
        this.YOB = Integer.parseInt(data[4]);
    }
    
    //methods
    public String getFullName(){
        return firstName + " " + lastName;
    } 
    
    public String getFormalName(){
        return title + " " + firstName + " " + lastName;
    }
    
    public int getAge(){
        return YOB - calendar.YEAR;
    }
    
    public String toCSVDataRecord(){
        return ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }
    
    public String dataFormat(){
        return String.format("%-20s%-20s%-20s%-20s%-20s", ID, firstName, lastName, title, YOB);
    }
}
