package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archive {
        public static void main(String[] args) throws IOException {
            String sourceFile = "D:\\IOTraning\\folderT"; //директория для архивации
            FileOutputStream fos = new FileOutputStream("D:\\IOTraning\\folderQ\\dirCompressed.zip"); //запись куда
            ZipOutputStream zipOut = new ZipOutputStream(fos); //передаём типу зипзаписи путь куда записываем, этот тип сжимает переданные в него данные
            File fileToZip = new File(sourceFile); // создаем обьект типа файл для директории откуда берём файлы

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        }

        private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) { //если наш файл который хотим архивировать директория делаем следующее
                if (fileName.endsWith("/")) { //если имя директории заканчивается на /
                    zipOut.putNextEntry(new ZipEntry(fileName)); //обьект зипзаписи добавь в новый поток директорию
                    zipOut.closeEntry(); //закрой поток
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/")); //иначе обьект зипзаписи добавь в новый поток имя директории и конкатинируй с /
                    zipOut.closeEntry();
                }
                File[] children = fileToZip.listFiles(); //масив файлов дочерних равняется вместилищем файлов в директории
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut); //рекурсия метод вызывает сам себя если есть потомки
                }
                return;//выход
            }
            FileInputStream fis = new FileInputStream(fileToZip);//чтение соурс директории
            ZipEntry zipEntry = new ZipEntry(fileName); //имя директории
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
}
