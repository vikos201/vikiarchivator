package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main{

 /*public static void main(String[]args){

     zipOurFiles();
        }*/

    public static void zipOurFiles() {

        String sourceFile = "D:\\IOTraning\\folderT\\my.txt";
        FileOutputStream fos = null;
        try {
        try {
            fos = new FileOutputStream("D:\\IOTraning\\folderT\\compressed.zip");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileToZip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

            zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}