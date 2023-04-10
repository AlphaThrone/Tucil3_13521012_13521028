package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader extends Input{
    private ArrayList<Node> listAllNode;
    private int count;

    public FileReader(){
        listAllNode = new ArrayList<Node>();
    }

    public ArrayList<Node> getNodes(){
        return listAllNode;
    }

    public void read(String path){
        File file = new File(path);
        try{
            Scanner readfile = new Scanner(file);
            count = readfile.nextInt();
            readfile.next();
            int i=0;
            while (i<count) {
                listAllNode.add(new Node(readfile.next(), null, Double.parseDouble(readfile.next()), Double.parseDouble(readfile.next())));
                i++;
            }
            for (Node node : listAllNode) {
                node.printNode();
            }
            readfile.close();
        } catch (FileNotFoundException e){
            e.fillInStackTrace();
        }
    }
}
