package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Iterator;

public class DBL {

    private ObservableList<Per> data = FXCollections.observableArrayList();
    private DBOp HM_data = new DBOp();
    String Fname = "./src/sample/data.dat";
    String backupFname = "./src/sample/backupdata.dat";

    File backupFile = new File(backupFname);




    public ObservableList<Per> getData() {
        return data;
    }

    public  boolean  backupCheck(){
        return backupFile.exists();
    }

    public void deleteBackupFile(){
        if(backupFile.exists()){
            backupFile.delete();
        }
    }

    public void fullData(){
        data.clear();
        HM_data.fullList(data);
    }



    public void deleteChosenData(){
        HM_data.deleteFromList(data);
    }

    public synchronized void loadBackupFile(){
        HM_data.loadDataFromFile(backupFname);
        HM_data.fullList(data);
    }

    public synchronized void loadDataFromFile(){
        HM_data.loadDataFromFile(Fname);
        HM_data.fullList(data);
//        System.out.println("LOAD FILE");
//        BufferedReader Fin = null;
//        try {
//            Fin = new BufferedReader(new FileReader(Fname));
//            String buff ="";
//            while ((buff = Fin.readLine())!= null)
//            {
//                Per per = new Per();
//                int ibuff = Integer.parseInt(buff);
//                per.setId(ibuff);
//                buff = "";
//                buff = Fin.readLine();
//                per.setName(buff);
//                buff = "";
//                buff = Fin.readLine();
//                per.setCHclass(buff);
//                buff = "";
//                buff = Fin.readLine();
//                ibuff = Integer.parseInt(buff);
//                per.setLvl(ibuff);
//
//                data.add(per);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void deleteALL(){
        data.clear();
        HM_data.deleteAll();
    }

    public void clearALL(){
        data.clear();
    }

    public void findEQ(int id, String name, int lvl, String CHClass){
        HM_data.findEquals(data,id,name,lvl,CHClass);
    }
    public void findID(int id){
        HM_data.findID(id,data);

//        data.forEach(per -> {
//            if(per.getId() != (id)){
//                data.remove(per);
//            }
//        });
    }

    public void findName(String name){

        for (Per datum : data) {
            Per per;
            if (!(per = datum).getName().equals(name)) {
                data.remove(per);
            }
        }
        //    HM_data.findName(name, data);
//        data.forEach(per -> {
//            if(!per.getName().equals(name)){
//                data.remove(per);
//            }
//        });
    }

     public void findCHclass(String CHclass){
         for (Per datum : data) {
             Per per;
             if (!(per = datum).getCHclass().equals(CHclass)) {
                 data.remove(per);
             }
         }
        //        HM_data.findCHclass(CHclass, data);
//        data.forEach(per -> {
//            if(!per.getCHclass().equals(CHclass)){
//                data.remove(per);
//            }
//        });
    }

     public void findLvl(int lvl){

        //        HM_data.findlvl(lvl,data);
//        data.forEach(per -> {
//            if(per.getLvl() != lvl){
//                data.remove(per);
//            }
//        });
    }



//    public void addEmtyNote(){
//        data.add(new Per(0,"name","class",0));
//    }

   synchronized public void addNote(int id, String name, String CHclass, int lvl){
        Per p = new Per(id,name,CHclass,lvl);
      //  data.add(p);
        HM_data.addNote(p);
        fullData();
    }


    public void backupSave(){
        HM_data.saveToFile(backupFname);
    }
    public void saveToFile(){
        HM_data.saveToFile(Fname);
//        try {
//            BufferedWriter Fout = new BufferedWriter(new FileWriter(Fname));
//            data.forEach(per ->{
//                try {
//                    Fout.write(generateNote(per));
//                    Fout.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private String generateNote(Per per){
        String buff = "";
        buff = per.getId() + "\n" + per.getName() + "\n" + per.getCHclass() +"\n" + per.getLvl() +"\n";
        return buff;
    }



}
