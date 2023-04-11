package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jxmapviewer.viewer.GeoPosition;

public class FileReader extends Input{
    private ArrayList<Node> listAllNode;
    private GeoPosition locMap;
    private int zoom;
    private int count;

    public FileReader(){
        listAllNode = new ArrayList<Node>();
        this.count = 0;
    }

    public ArrayList<Node> getNodes(){
        return listAllNode;
    }

    public int getNodeCount(){
        return count;
    }

    public GeoPosition getMapLocation(){
        return this.locMap;
    }

    public int getZoom(){
        return this.zoom;
    }

    public void read(String path){
        File file = new File(path);
        try{
            Scanner readfile = new Scanner(file);
            count = readfile.nextInt();
            this.locMap = new GeoPosition(Double.parseDouble(readfile.next()), Double.parseDouble(readfile.next()));
            this.zoom = Integer.parseInt(readfile.next());
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
