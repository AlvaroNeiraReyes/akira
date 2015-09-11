/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

import static akori.Cluster2txt.listFilesForFolder;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Claudio
 */
public class ListFile {
    static public void main(String[]args){
        ArrayList<String> list = listFilesForFolder(new File("e:\\Trabajos\\AKORI\\datos gino"));
        
        for(String temp : list){
            if(!temp.contains(".bdf"))
                System.out.print("'"+temp.replace(".asc", "")+"',");
        }
            
        
    }
}
