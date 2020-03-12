package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//
// primary
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

//  // askWin
//        FXMLLoader askWinloader = new FXMLLoader();
//        askWinloader.setLocation(getClass().getResource("askWindow.fxml"));
//        Parent askeWinroot = askWinloader.load();

        primaryStage.setTitle("Data Base");
        primaryStage.setScene(new Scene(root));

//        Stage askWin = new Stage();
//        askWin.setTitle("backup");
//        askWin.setScene(new Scene(askeWinroot));
//        askWin.initModality(Modality.WINDOW_MODAL);
//        askWin.initOwner(primaryStage);


        Controller controller = loader.getController();


      primaryStage.setOnShowing(controller.getOpenEventHandler());

        primaryStage.show();


        primaryStage.setOnCloseRequest(controller.getCloseEventHandler());


        //        AutoSaveTask autoSaveTask = new AutoSaveTask();
//        autoSaveTask.timerStart();

//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            public void handle(WindowEvent we) {
//                System.out.println("Stage is closing");
//            }
//        });

    }


    public static void main(String[] args) {

        Application.launch(args);
        //timeCheck();

    }

    private static void timeCheck(){
        //check start
        ObservableList<Per> data = FXCollections.observableArrayList();

        System.out.println("Add check");
        DBOp dataBase = new DBOp();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            dataBase.createPer(i,"name" + i,i,"class"+i);
        }
        long finish = System.currentTimeMillis();
        System.out.println("100 notes time: " + (finish-start));

        System.out.println("Find check ");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            dataBase.findID(i,data);
        }
        finish = System.currentTimeMillis();
        System.out.println("100 notes time: " + (finish-start));

        System.out.println("Delete check");
        start = System.currentTimeMillis();
        dataBase.deleteFromList(data);
        finish = System.currentTimeMillis();
        System.out.println("100 notes time: " + (finish-start));

        data.clear();

        System.out.println("Add check");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dataBase.createPer(i,"name" + i,i,"class"+i);
        }
        finish = System.currentTimeMillis();
        System.out.println("1000 notes time: " + (finish-start));

        System.out.println("Find check ");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dataBase.findID(i, data);
        }
        finish = System.currentTimeMillis();
        System.out.println("1000 notes time: " + (finish-start));

        System.out.println("Delete check");
        start = System.currentTimeMillis();
        dataBase.deleteFromList(data);
        finish = System.currentTimeMillis();
        System.out.println("1000 notes time: " + (finish-start));

        data.clear();

        System.out.println("Add check");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dataBase.createPer(i,"name" + i,i,"class"+i);
        }
        finish = System.currentTimeMillis();
        System.out.println("10000 notes time: " + (finish-start));

        System.out.println("Find check ");
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dataBase.findID(i,data);
        }
        finish = System.currentTimeMillis();
        System.out.println("10000 notes time: " + (finish-start));

        System.out.println("Delete check");
        start = System.currentTimeMillis();
        dataBase.deleteFromList(data);
        finish = System.currentTimeMillis();
        System.out.println("10000 notes time: " + (finish-start));

        data.clear();

        System.out.println("Add check");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            dataBase.createPer(i,"name" + i,i,"class"+i);
        }
        finish = System.currentTimeMillis();
        System.out.println("100000 notes time: " + (finish-start));

        System.out.println("Find check ");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            dataBase.findID(i,data);
        }
        finish = System.currentTimeMillis();
        System.out.println("100000 notes time: " + (finish-start));

        System.out.println("Delete check");
        start = System.currentTimeMillis();
        dataBase.deleteFromList(data);
        finish = System.currentTimeMillis();
        System.out.println("100000 notes time: " + (finish-start));

        data.clear();
    }
}
