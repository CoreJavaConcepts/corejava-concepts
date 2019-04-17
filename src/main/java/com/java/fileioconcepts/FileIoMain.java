package com.java.fileioconcepts;

import java.io.*;
import java.util.Arrays;

public class FileIoMain {
    public static void main(String[] args) {
        try{
            System.out.println("============Creating Destroying File===================");
            //----------Crete File inside current directory
            File file = new File("currntDirectoryFile");
            file.createNewFile();

            //---- Inside Subdirectory
            //File file = new File( File subdir, String fileName);
            //File file = new File( String subdir, String fileName);
            //-----------Create new directory inside current directory and create new file inside it
            File dir = new File("directory");
            dir.mkdir();//Need to create directory first
            File file2 = new File(dir, "newFile.txt");
            file2.createNewFile();

            //Or
            File file3 = new File("directory", "newFile2.txt"); // directory is already created directory,
            // if not already exist then it will throw exception
            file3.createNewFile();


            //----------Common file operations
            File filee = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\newFile.txt");
            // isFile or isDirectory
            System.out.println("is File: "+filee.isFile());
            System.out.println("is Directory: "+filee.isDirectory());
            // exists
            System.out.println("exists: "+filee.exists());
            //Creating file
            boolean fileCreated = filee.createNewFile(); // Create new file if not already exists , return boolean value
            System.out.println("fileCreated :"+fileCreated);
            System.out.println("exists: "+filee.exists());

            // delete
            filee.delete();

            //------------Get All File/Directory name List
            File fileee = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3");
            String[] fileNameArr = fileee.list();
            System.out.println(Arrays.toString(fileNameArr));

            //Returns number of characters in file, helpful for reading
            File fileeee = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            long numOfCharacters = fileeee.length();
            System.out.println("numOfCharacters :"+numOfCharacters);

            System.out.println("=======================================Writers========================================================");
            System.out.println("=================FileWriter========================");
            //--------------Constructors
            File fileForFileWriter = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");

            FileWriter fileWriter = new FileWriter(fileForFileWriter, true); // true to append in existing file, otherwise it will override
            //-----Or use direct file path
            //FileWriter fileWriter = new FileWriter("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            //FileWriter fileWriter = new FileWriter(fileForFileWriter);

            //----Writing File
            fileWriter.write("\n");
            fileWriter.write("written from filewritter");//Writing String
            fileWriter.write("\n");// Problematic new line which varies system to system
            fileWriter.write(65);// Writing character
            fileWriter.write("\n");
            fileWriter.write(new char[]{'a','b','c'});// Writing char array
            //Drawback: No option provided for writing int, boolean etc

            //------Flush and close
            fileWriter.flush();
            fileWriter.close();

            System.out.println("\n");
            System.out.println("=================BufferedWriter========================");
            //---Constructor always takes Writer argument, not file or String location to file
            // File will always interact with FileWriter and not directly interact with BufferedWriter
            File fileForBufferedWriter = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForBufferedWriter, false));
            //BufferedWriter bufferedWriter = new BufferedWriter(Writer writer, int buffersize);


            //---Writing
            bufferedWriter.write("WrittenFrom Buffered Writer");//Write String
            bufferedWriter.newLine(); // Main advantage, newLine will work for all systems. Other same as FileWriter to write file
            bufferedWriter.write(68);// Write character
            bufferedWriter.newLine();
            bufferedWriter.write(new char[]{'x','y','z'});//Write char array

            //----Flush and Close
            bufferedWriter.flush();
            bufferedWriter.close();//No need to close underlying FileWriter, doing this automatically close filewriter (not recommended)

            System.out.println("=================PrintWriter========================");
            // Most advance, can write any type of primitive data directly to file
            // Can directly interact with file, need to create FileWriter is optional
            //-----Constructor
            File fileForPrintWriter = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            PrintWriter printWriter = new PrintWriter(fileForPrintWriter); // With File
            //With File String
            //PrintWriter printWriter2 = new PrintWriter("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            //PrintWriter printWriter3 = new PrintWriter(new FileWriter(fileForPrintWriter));// With FileWriter

            //--------Writing File
            // Three types of method: write , print, println
            // write method same as FileWriter(writing String, char and char array)
            // print and println with all primitive and String
            printWriter.println("Writing from PrintWriter");
            printWriter.write(100); // treats as charater not int primitive
            printWriter.println(100);
            printWriter.println(true);
            printWriter.println('C');

            //------Close
            printWriter.flush();
            printWriter.close();


            System.out.println("===========================================Readers===========================================");
            System.out.println("=================FileReader========================");
            //---------Constructor
            File fileForFileReader = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            FileReader fileReader = new FileReader(fileForFileReader);
            // Or Using direct file path
            //FileReader fileReader = new FileReader("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");

            //--------Reading file
            System.out.println("------Using each character-------");
            int readedChar = fileReader.read();
            while(readedChar != -1){
                System.out.print((char)readedChar);//Need to cast to char
                readedChar = fileReader.read();
            }

            System.out.println("\n");
            System.out.println("------Using char array-------");
            char[] charArr = new char[(int)fileForFileReader.length()];//Set size using length

            FileReader fileReader1 = new FileReader(fileForFileReader);
            fileReader1.read(charArr);

            //fileReader.read(charArr); // Cann't use this already used fileReader since its read() is exhausted

            for(char ch: charArr){
                System.out.print(ch);
            }

            //Drawback is we need to reach character by character which is not recommended

            //---Close
            fileReader.close();
            fileReader1.close();


            System.out.println("=================BufferedReader========================");
            //can use to read character but main advantage is read file line by line
            //----Constructor ,  It can't communicate directly with file, it communicates via Reader
            File fileForBufferedReader = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\dir1\\dir2\\dir3\\SampleFileForIo.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForBufferedReader));
            //BufferedReader bufferedReader = new BufferedReader(FileReader fileReader, int bufferSize);

            //------Reading File
            // All methods same as FileReader to read file with additional reading line
            String line = bufferedReader.readLine();
            while(line != null){//Returns null if next line is not available
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            //----Close
            bufferedReader.close(); // No need to close underlying FileReader


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
