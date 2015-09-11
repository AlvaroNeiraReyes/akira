/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akori;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Claudio
 */
public class Cluster2txt {

    static public void main(String[] args) throws FileNotFoundException {
        File folder1 = new File("E:\\NetBeansProjects\\AKORI\\Proccess_1\\fotocluster");
        String folder2 = "E:\\NetBeansProjects\\AKORI\\Proccess_1\\txtcluster";
        String folder = "E:\\NetBeansProjects\\AKORI\\Proccess_1";
        ArrayList<String> list = listFilesForFolder(folder1);

        for (String file : list) {
            String num = file.substring(file.indexOf(".") - 1, file.indexOf("."));
            int cluster = Integer.parseInt(num);
            String name = file.substring(0, file.indexOf(".") - 1);

            //System.out.println(cluster + "," + name);
            Scanner s = new Scanner(new File(folder2 + "\\" + name + ".txt"));
            PrintWriter writer = new PrintWriter(folder + "\\" + name + num + ".txt");
            while (s.hasNext()) {
                String temp = s.next();
                String[] aux = temp.split(",");
                int x = Integer.parseInt(aux[1]);
                int y = Integer.parseInt(aux[2]);
                int w = Integer.parseInt(aux[3]);
                int h = Integer.parseInt(aux[4]);
                int c = Integer.parseInt(aux[7]);
                
                if (c == cluster) {
                    writer.println(temp);
                }
                else if (x < 700 && y < 300 && w < 700 && h < 400) {
                    writer.println(temp);
                }
                else if (x < 700 && y > 200 && y < 400 && w > 700 && w < 1000 && h < 100) {
                    writer.println(temp);
                }
           
            }
            s.close();
            writer.close();
        }

    }

    public static ArrayList<String> listFilesForFolder(File folder) {
        ArrayList<String> list = new ArrayList<String>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry.getName());
            }
        }
        return list;
    }

}
