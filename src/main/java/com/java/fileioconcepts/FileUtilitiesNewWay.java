package com.java.fileioconcepts;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileUtilitiesNewWay {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try{
            //Reading through stream is better option for reading file efficiently instead of reading all in memory
            String fileName = "D:\\IntellijWorkspace\\corejava-concepts\\sampleFile\\sampleFile.txt";

            System.out.println("---------Reading in memory------------------");
            //all the file lines are kept in memory
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            System.out.println(allLines);

            System.out.println("-----------------Using Java Stream---------------");
            Stream<String> fileStream = Files.lines(Paths.get(fileName));
            // Files and Paths is java.nio.file package, since 1.7
            // Note: Files don't take File object, instead it takes Path, Paths.get(String) returns Path object
            fileStream.forEach(System.out::println);

            System.out.println("---------Reading File Efficiently using Streaming Through the File------------------");
            inputStream = new FileInputStream(fileName);
            sc = new Scanner(inputStream, "UTF-8");
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(line);
            }

            System.out.println("---------Reading File Efficiently using Apache FileUtils LineIterator------------------");
            LineIterator lineIterator = FileUtils.lineIterator(new File(fileName)); // LineIterator
            while(lineIterator.hasNext()){
                String line = lineIterator.nextLine();
                System.out.println(line);
            }
            LineIterator.closeQuietly(lineIterator);


        }catch(IOException ex){
            ex.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }

            if (sc != null) {
                sc.close();
            }
        }



    }
}
