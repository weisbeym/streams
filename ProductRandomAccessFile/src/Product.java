/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yisroel
 */
public class Product {
    private String ID;
    private String name;
    private String description;
    private int price;
    
    public static final int NAME_LENGTH = 35;
    public static final int DESC_LENGTH = 75;
    public static final int ID_LENGTH = 6;
    public static final int PARSED_LENGTH = 126;
    

    //contructors
    public Product(String ID, String name, String description, int price) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public Product(String line){
        String[] data = {line.substring(0, 5).trim(), line.substring(7, 41).trim(), line.substring(43, 117).trim(), line.substring(119, 124).trim()};
        this.ID = data[0];
        this.name = data[1];
        this.description = data[2];
        this.price = Integer.parseInt(data[3]);
    }
    
    //methods
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getPrice(){
        return price;
    }
    
    public String toCSVDataRecord(){
        return ID + "," + name + "," + description + "," + price;
    }
    
    public String dataFormat(){
        return String.format("%-20s%-20s%-20s%-20d", ID, name, description, price);
    }
    
    public String addPadding(String str, int requiredLength){
        for(int i = str.length(); i < requiredLength; i++){
            str += " ";
        }
        return str;
    }
    
    public String processProductForOutput(){
        return addPadding((String)ID, ID_LENGTH) + " " + addPadding(name, NAME_LENGTH) + " " + addPadding(description, DESC_LENGTH) + " " + addPadding(Integer.toString(price), ID_LENGTH) + " ";
    }
}
