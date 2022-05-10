import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;



public class Delivery {

    public static void main(String[] args) throws Exception{

        System.out.println("""
                Greetings, dear Deliverer!
                Please dial the menu number to work with the program, if you have finished, then dial 7:""");
        String str = ("""
                
                1.Show the list of equipment to be delivered
                2.Show the delivered equipment
                3.Show the delivered equipment:
                    What equipment has been delivered? Enter the name or serial number of the equipment
                4.Show number of delivered machines
                5.Show number of ordered machines
                6.Show how much I earned
                7.Exit""");
        Scanner scan = new Scanner(System.in);

        while(true){

            System.out.println(str);
            int numberOfMenu = scan.nextInt();
            switch (numberOfMenu) {
                case 1 -> Methods.viewEquipmentForDelivery();
                case 2 -> Methods.viewDeliveredEquipment();
                case 3 -> Methods.deliverEquipment();
                case 4 -> Methods.viewCountOfDeliveredEquipment();
                case 5 -> Methods.viewCountOfOrderedEquipment();
                case 6 -> Methods.viewSalary();
                case 7 -> Main.main(args);
                default -> System.out.println("Wrong input!!! Try again the next time :)");
            }
        }
    }


}

class Methods{

    public static String path = "src/OrderedProducts.csv";
    public static String path1 = "src/DeliveredEquipment.csv";
    public static Scanner scan = new Scanner(System.in);

    public static void viewEquipmentForDelivery() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        for(String[] reading: reader){
            for(String read: reading){
                String result = String.format("%-10s | ", read);
                System.out.print(result);
            }
            System.out.println();
        }
    }

    public static void viewDeliveredEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path1));

        for(String[] reading: reader){
            for(String read: reading){
                String result = String.format("%-10s | ", read);
                System.out.print(result);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }

    }

    public static void deliverEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        System.out.println("Input serial number for deliver");

        String searching = scan.next();
        String[] search = {searching};

        for(String[] read: reader){
            var list = new ArrayList<String>(List.of(read));
            String[] find = {list.get(0)};
            System.out.println(list);
            if(Arrays.deepEquals(search, find)){
                CSVWriter writer = new CSVWriter(new FileWriter(path));
                CSVWriter writer2 = new CSVWriter((new FileWriter(path1, true)));
                writer2.writeNext(read);
                writer2.close();
                writer.close();
                continue;
            }
            else{
                CSVWriter writer1 = new CSVWriter((new FileWriter(path, true)));
                writer1.writeNext(read);
                writer1.close();
            }

        }

    }

    public static void viewCountOfDeliveredEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path1));

        int count = 0;
        for(String[] reading: reader){
            count++;
        }
        System.out.println(count + " delivered equipment in total");

        reader.close();

    }

    public static void viewCountOfOrderedEquipment() throws Exception{

        CSVReader reader = new CSVReader(new FileReader(path));

        int count = 0;
        for(String[] reading: reader){
            count++;
        }
        System.out.println(count + " ordered equipment in total");

        reader.close();

    }

    public static void viewSalary() throws Exception{
        String path = "src/AllEquipment";
        BufferedReader a = new BufferedReader(new FileReader(path));
        int count = 0;
        String line;
        while ((line = a.readLine()) != null) {
            ++count;
        }
        System.out.println("Your salary is " + count * 200);
        a.close();
    }
}