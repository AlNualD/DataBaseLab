package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.*;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DBOp {
    public DBOp(){
    }

    private HashMap<Integer,Per> dataHM = new HashMap<Integer, Per>();

    public void delete(int id){
        dataHM.remove(id);
    }

    public void deleteFromList(ObservableList<Per> data){
        data.forEach(per ->{
            dataHM.remove(per.getId());
        });
    }

    public void fullList(ObservableList<Per> data){
        data.addAll(dataHM.values());
    }

    public void addNote(Per per){
        dataHM.put(per.getId(),per);
    }

    public void deleteAll(){
        dataHM.clear();
    }

    public boolean check(Per per, String name, int lvl, String CHClass){
        return (per.getName().equals(name) || name.equals("")) && (per.getLvl() == lvl || lvl == -1) && (per.getCHclass().equals(CHClass) || CHClass.equals(""));
    }
    public void findEquals(ObservableList<Per> data, int id, String name, int lvl, String CHClass){
        if(id != -1){
            if(dataHM.containsKey(id)) {
                data.add(dataHM.get(id));
            }
        }
        else {
            for(Per pval : dataHM.values()){
                if(check(pval,name,lvl,CHClass)){
                    data.add(pval);
                }
            }
        }
    }

    public void findID(int id, ObservableList<Per> data){
        if(dataHM.containsKey(id)) {
            data.add(dataHM.get(id));
        }
    }

    public Per fintIDcheck(int id){
        if(dataHM.containsKey(id)) {
            return dataHM.get(id);
        }
        Per per = new Per();
        return per;
    }

    public void findName(String name,ObservableList<Per> data){
        for(Per pval : dataHM.values()){
           if(pval.getName().equals(name)){
               data.add(pval);
           }
        }

    }

    public void findCHclass(String CHclass,ObservableList<Per> data){
        for(Per pval : dataHM.values()){
            if(pval.getCHclass().equals(CHclass)){
                data.add(pval);
            }
        }
    }

    public void findlvl(int lvl,ObservableList<Per> data){
        for(Per pval : dataHM.values()){
            if(pval.getLvl() == lvl){
                data.add(pval);
            }
        }
    }

    public void createPer(int id, String name, int lvl, String CHclass){
        Per per = new Per(id, name, CHclass, lvl);
        dataHM.put(per.getId(),per);
    }


    public void loadDataFromFile(String Fname){

        System.out.println("LOAD FILE");
        BufferedReader Fin = null;
        try {
            Fin = new BufferedReader(new FileReader(Fname));
            String buff ="";
            while ((buff = Fin.readLine())!= null)
            {
                Per per = new Per();
                int ibuff = Integer.parseInt(buff);
                per.setId(ibuff);
                buff = "";
                buff = Fin.readLine();
                per.setName(buff);
                buff = "";
                buff = Fin.readLine();
                per.setCHclass(buff);
                buff = "";
                buff = Fin.readLine();
                ibuff = Integer.parseInt(buff);
                per.setLvl(ibuff);


                dataHM.put(per.getId(),per);

            }
            Fin.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToFile(String Fname){
        try {
            BufferedWriter Fout = new BufferedWriter(new FileWriter(Fname));
            for(Per pval : dataHM.values()){
                Fout.write(generateNote(pval));
                Fout.flush();
            }
            Fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateNote(Per per){
        String buff = "";
        buff = per.getId() + "\n" + per.getName() + "\n" + per.getCHclass() +"\n" + per.getLvl() +"\n";
        return buff;
    }
}
