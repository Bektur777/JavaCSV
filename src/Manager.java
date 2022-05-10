import com.opencsv.*;

import java.io.*;
import java.util.*;

public class Manager {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        System.out.println("""
                Greetings, dear Zavkhoz!
                Please dial the menu number to work with the program, if you are finished, then dial 7:""");
        String menu = ("""
                                          
                1.Show all list of equipment for school
                2.Search for equipment:
                    By serial number.
                    By name
                3.Show equipment report
                4.Place an order for the equipment:
                5.View list of ordered equipment
                6.Delete order:
                7.Exit.""");

        while(true) {

            System.out.println(menu);
            String numberOfMenu = scan.nextLine();

            switch (numberOfMenu) {
                case "1" -> MethodsManager.viewAllEquipment();
                case "2" -> MethodsManager.searchingEquipment();
                case "3" -> MethodsManager.viewReport();
                case "4" -> MethodsManager.serialNumber();
                case "5" -> MethodsManager.viewOrderingEquipment();
                case "6" -> MethodsManager.deleteOrder();
                case "7" -> Main.main(args);
                default -> System.out.println("Wrong input!!! Try again the next time :)");
            }
        }
    }
}

class MethodsManager{

    public static final Random random = new Random();
    public static Scanner scan = new Scanner(System.in);
    public static String path = "src/AllEquipment.csv";
    public static String path1 = "src/OrderedProducts.csv";

    public static void serialNumber() throws Exception{

        int min = 100000;
        int max = 1000000;
        int range = max - min;
        String result = "";
        int res = random.nextInt(range+1);
        res += min;
        result += String.valueOf(res);
        orderEquipment(result);

    }

    public static void viewAllEquipment() throws Exception{
        CSVReader reader = new CSVReader((new FileReader(path)));
        for(String[] list: reader){
            System.out.println(list[1]);
        }
    }

    public static void searchingEquipment() throws Exception{
        CSVReader reader = new CSVReader((new FileReader(path)));
        System.out.println("Input the equipment for searching!!!\nSerial number ========> ser\nName ========> name ");
        String choose = scan.nextLine();
        if(choose.equalsIgnoreCase("name")){
            String searching = scan.nextLine();
            String[] requiredEquipment = {searching};
            for(String[] list: reader){
                String[] equipment = {list[1]};;
                if(Arrays.deepEquals(requiredEquipment, equipment)){
                    for (String find: list){
                        System.out.print(find + " ");
                    }
                }
            }
        }
        else if(choose.equalsIgnoreCase("ser")){
            String searching = scan.nextLine();
            String[] requiredEquipment = {searching};
            for(String[] list: reader){
                String[] equipment = {list[0]};;
                if(Arrays.deepEquals(requiredEquipment, equipment)){
                    for (String find: list){
                        System.out.print(find + " ");
                    }
                }
            }
        }
    }

    public static void viewReport() throws Exception{
        CSVReader reader = new CSVReader((new FileReader(path)));
        for(String[] reading: reader){
            for(String result: reading){
                System.out.printf("%-10s | ", result);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    }

    public static void orderEquipment(String str) throws Exception{

        Date date = new Date();
        String dateNow = String.valueOf(date);

        CSVWriter writer = new CSVWriter(new FileWriter(path1,true));

        System.out.println("Enter the equipment for order");
        String order = scan.nextLine();
        System.out.println("Enter the count for order");
        String count = scan.nextLine();

        String[] equipment = {str,order,count,dateNow};
        writer.writeNext(equipment);

        System.out.println("The equipment was order");
        writer.close();

    }

    public static void viewOrderingEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path1));
        for(String[] reading: reader){
            for(String result: reading){
                String str = String.format("%-10s | ", result);
                System.out.print(str);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    }

    public static void deleteOrder() throws Exception{
        CSVReader reader = new CSVReader((new FileReader(path1)));
        CSVWriter writer = new CSVWriter((new FileWriter(path1, true)));
        System.out.println("Input the equipment for deleting!!!");
        String deleting = scan.nextLine();
        String[] requiredEquipment = {deleting};
        for(String[] list: reader){
            String[] equipment = {list[0]};;
            if(Arrays.deepEquals(requiredEquipment, equipment)){
                for (String find: list){
                    System.out.print(find + " ");
                }
            }
        }
    }

}