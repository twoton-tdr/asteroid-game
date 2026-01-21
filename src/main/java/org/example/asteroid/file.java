package org.example.asteroid;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class file{
    private File pointsFile;
    public file(){
        String userHome = System.getProperty("user.home");
        pointsFile = new File(userHome,"points.txt");
    }
    public void writeHighestPoint(int point){
        try{
            Files.writeString(pointsFile.toPath(),String.valueOf(point));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public int readHighestPoint(){
        int point = 0;
        try {
            if(Files.notExists(pointsFile.toPath())){
                Files.createFile(pointsFile.toPath());
            }
            String pointString = Files.readString(pointsFile.toPath());
            if(pointString.isEmpty()){
                writeHighestPoint(0);
                point = 0;
            }else{
                point = Integer.valueOf(pointString);
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return point;
    }
}
//public class file {
//    private static String path = "Points.txt";
//     private static File createFilePath(){
//        String userHome = System.getProperty("user.home");
//        File pointsFile = new File(userHome,path);
//        return pointsFile;
//    }
//    public static void writeHighestPoint(int point){
//         File pointsFile = createFilePath();
//         try(pointsFile.)
//        try(PrintWriter writer = new PrintWriter(path)){
//            writer.println(String.valueOf(point));
//        }catch(Exception e){
//            System.out.println("Error: "+ e.getMessage());
//        }
//    }
//
//    public static int readHighestPoint(){
//
//        int point = 0;
//        try(Scanner scanner = new Scanner(Paths.get(path))){
//            while(scanner.hasNextLine()){
//                point = Integer.valueOf(scanner.nextLine());
//            }
//
//        }catch(Exception e){
//            System.out.println("Error: "+ e.getMessage());
//        }
//        return point;
//    }
//}
