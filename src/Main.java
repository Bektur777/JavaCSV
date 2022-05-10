import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static String path = "src/accounts.csv";
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("""
                Choose the type of account
                Director
                Manager
                Delivery
                Enter the number if you want to leave out of program!!!""");
        String chooseAcc = scan.nextLine();
        while(true){
            if (chooseAcc.equalsIgnoreCase("director")
                    || chooseAcc.equalsIgnoreCase("manager")
                    || chooseAcc.equalsIgnoreCase("delivery")) {
                chooseAccount(chooseAcc);
            }
            else{
                System.out.println("Try again");
            }
        }
    }

//    public static void readingAccounts() throws IOException{
//        CSVReader reader = new CSVReader(new FileReader(path));
//        for(String[] reading: reader){
//            for(String read: reading){
//                System.out.println(read);
//            }
//        }
//    }

    public static void  chooseAccount(String str) throws Exception {
        System.out.println("What do u want registration or authorization");
        CSVWriter writer = new CSVWriter(new FileWriter(path,true));
        CSVReader reader = new CSVReader(new FileReader(path));
        String typing = scan.nextLine();
        if(typing.equalsIgnoreCase("reg")){
            System.out.println("Enter login and password!!!");
            String acc = scan.nextLine();
            String pass = scan.nextLine();
            String[] account = {str,acc, pass};
            writer.writeNext(account);
            System.out.println("Your account successfully created!!!\nAnd you can enter your account");
        }
        else if(typing.equalsIgnoreCase("auth")){
            System.out.println("Enter the login and password");
            String log = scan.nextLine();
            String pas = scan.nextLine();
            String[] string = {str};
            String[] login = {log};
            String[] password = {pas};
            for(String[] reading: reader){
                String[] name = {reading[0]};
                String[] l = {reading[1]};
                String[] p = {reading[2]};
                if(Arrays.deepEquals(name, string)){
                    System.out.println(Arrays.toString(name));
                    if(Arrays.equals(login, l) && Arrays.equals(password, p)){
                        if(Arrays.deepEquals(name, new String[]{"manager"})){
                            Manager.main(new String[]{"delivery"});
                        }
                        if(Arrays.deepEquals(name, new String[]{"director"})){
                            Director.main(new String[]{"delivery"});
                        }
                        if(Arrays.deepEquals(name, new String[]{"delivery"})){
                            Delivery.main(new String[]{"delivery"});
                        }
                    }
                    else{
                        System.out.println("Try again");
                    }
                }
            }
        }
        writer.close();
    }
}

