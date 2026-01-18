package org.example.asteroid;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class file {
    private static String path = "Points.txt";
    public static void writeHighestPoint(int point){
        try(PrintWriter writer = new PrintWriter(path)){
            writer.println(String.valueOf(point));
        }catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }

    public static int readHighestPoint(){

        int point = 0;
        try(Scanner scanner = new Scanner(Paths.get(path))){
            while(scanner.hasNextLine()){
                point = Integer.valueOf(scanner.nextLine());
            }

        }catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return point;
    }
}
