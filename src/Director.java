import com.opencsv.*;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.*;

public class Director {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        System.out.println("""
                Greetings dear Director!
                Please dial the menu number to work with the program, if you have finished, then dial 6:""");

        String print = ("""
                1. Show list of equipment
                2. Show Number of pieces of equipment
                3. Show equipment with the highest number
                4. Show machines with the lowest number
                5. Show equipment purchase report
                6. Exit""");

        while(true) {

            System.out.println(print);
            String numberOfMenu = scan.nextLine();

            switch (numberOfMenu) {
                case "1" -> MethodsDirector.viewListEquipment();
                case "2" -> MethodsDirector.viewCountOfEquipment();
                case "3" -> MethodsDirector.inAmount("Max");
                case "4" -> MethodsDirector.inAmount("Min");
                case "5" -> MethodsDirector.viewReports();
                case "6" -> Main.main(args);
                default -> System.out.println("Wrong input!!! Try again the next time :)");
            }
        }
    }
}

class MethodsDirector{

    public static String path = "src/AllEquipment.csv";
    public static String path1 = "src/OrderedProducts.csv";

    public static void viewListEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        for(String[] reading: reader){
            System.out.println(reading[1]);
        }

    }

    public static void viewCountOfEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        for(String[] reading: reader){
            String view = String.format("%-10s %s", reading[1], reading[2]);
            System.out.println(view);
        }

    }

    public static void viewReports() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        for(String[] reading: reader){
            for(String reports: reading){
                String result = String.format("%-10s | ",reports);
                System.out.print(result);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }

    }
    public static void inAmount(String maxOrMin) throws IOException{
        CSVReader reader = new CSVReader(new FileReader(path));
        reader.skip(1);

        String name = "";
        int amount = 0;

        if(maxOrMin.equals("Max")){
            amount = 0;}
        else{
            amount = 1000000000;}

        for (String[] row : reader){
            switch(maxOrMin){
                case "Max":
                    if(Integer.parseInt(row[2]) > amount){
                        amount = Integer.parseInt(row[2]);
                        name = row[1];
                    }
                    break;

                case "Min":
                    if(Integer.parseInt(row[2]) < amount){
                        amount = Integer.parseInt(row[2]);
                        name = row[1];
                    }
                    break;
            }
        }
        System.out.printf("Количество: %s %d", name, amount);
        System.out.println();
    }
}