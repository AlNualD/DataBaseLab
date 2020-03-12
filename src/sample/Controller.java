package sample;

import javafx.collections.FXCollections;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Controller {
    @FXML
    private Button createButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addNoteButton;
    @FXML
    private Button findButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button deleteNoteButton;
    @FXML
    private TextField IDField;
    @FXML
    private TextField NAMEField;
    @FXML
    private TextField LVLField;
    @FXML
    private TextField CLASSField;
    @FXML
    private TableView<Per> tableBase;
    @FXML
    private TableColumn<Per,Integer> IDCol;
    @FXML
    private TableColumn<Per,String> NAMECol;
    @FXML
    private  TableColumn<Per,Integer> LVLCol;
    @FXML
    private TableColumn<Per,String> CLASSCol;


   private DBL DataBase = new DBL();
  private ObservableList<Per> usersData = FXCollections.observableArrayList();
    private boolean needBackup = false;
//  private void initData(){
//      usersData.add(new Per(123,"Name","Class",3));
//      usersData.add(new Per(193,"Namehh","Class",2));
//      usersData.add(new Per(153,"Namek","Clhass",3));
//
//  }

  @FXML
  private void initialize(){
      IDCol.setCellValueFactory(new PropertyValueFactory<Per,Integer>("id"));
      NAMECol.setCellValueFactory(new PropertyValueFactory<Per,String>("name"));
      LVLCol.setCellValueFactory(new PropertyValueFactory<Per,Integer>("lvl"));
      CLASSCol.setCellValueFactory(new PropertyValueFactory<Per,String>("CHclass"));

  }


  public void saveButtonClicked(){
      DataBase.saveToFile();
      needBackup = false;
      DataBase.deleteBackupFile();
  }


  private void setItems(){
      Platform.runLater(()->{
          //tableBase.setItems(usersData);
          tableBase.setItems(DataBase.getData());
      });
  }
    public void loadButtonClicked(){
        DataBase.deleteALL();
        DataBase.loadDataFromFile();
        setItems();
        System.out.println("LB click");


    }

    public void createButtonClicked(){
        System.out.println("CB click");
        tableBase.setItems(DataBase.getData());

    }

    public void deleteButtonClicked(){
      DataBase.deleteALL();
    }

    public void addNoteClicked(){
      if(addFieldsCheck()){
            int idNUM = Integer.parseInt(IDField.getText());
            String nameF = NAMEField.getText();

            int lvlNUM = Integer.parseInt(LVLField.getText());
            String classF = CLASSField.getText();

            DataBase.addNote(idNUM,nameF,classF,lvlNUM);
            needBackup = true;
      }

    }

    private boolean addFieldsCheck(){
        return ((!NAMEField.getText().equals("")) && (!CLASSField.getText().equals("")) && (!IDField.getText().equals("")) && (!LVLField.getText().equals("")));
    }

    public void findButtonClicked(){
      DataBase.clearALL();
      int id, lvl;
      String name, CHClass;

      id = -1;
      lvl = -1;
      name ="";
      CHClass = "";

      if(!IDField.getText().equals("")){
       //   DataBase.findID(Integer.parseInt(IDField.getText()));
          id = Integer.parseInt(IDField.getText());
      }
      if(!CLASSField.getText().equals("")){
 //           DataBase.findCHclass(CLASSField.getText());
        CHClass = CLASSField.getText();
      }
      if(!LVLField.getText().equals("")){
 //           DataBase.findLvl(Integer.parseInt(LVLField.getText()));
        lvl = Integer.parseInt(LVLField.getText());
      }
      if(!NAMEField.getText().equals("")){
//          DataBase.findName(NAMEField.getText());
          name = NAMEField.getText();
      }
      DataBase.findEQ(id,name,lvl,CHClass);
    }

    public void resetButtonClicked(){
      DataBase.fullData();
    }


    public void deleteNoteButtonClicked(){
      findButtonClicked();
      DataBase.deleteChosenData();
      DataBase.fullData();
      needBackup = true;
    }

    public void onTableClick(){
      setPerInf(tableBase.getSelectionModel().selectedItemProperty().getValue());
    }

    private void setPerInf(Per per){
      if(per == null){return;}
      IDField.setText(String.valueOf(per.getId()));
      NAMEField.setText(per.getName());
      CLASSField.setText(per.getCHclass());
      LVLField.setText(String.valueOf(per.getLvl()));
    }


    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            //ТУТ НЕОБХОДИМАЯ ЛОГИКА
            System.out.println("Stage is closing");
            if (needBackup) {
                DataBase.backupSave();
            }
        }
    };

    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
        return closeEventHandler;
    }


    private javafx.event.EventHandler<WindowEvent> openEventHandler = new javafx.event.EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            //ТУТ НЕОБХОДИМАЯ ЛОГИКА
            System.out.println("Stage is openningfdsdf");
            if(DataBase.backupCheck()){
                DataBase.loadBackupFile();
                setItems();
            }
        }
    };

    public javafx.event.EventHandler<WindowEvent> getOpenEventHandler(){
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("askWindow.fxml"));
//        try {
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.initOwner(addNoteButton.getScene().getWindow());
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return openEventHandler;
    }

//    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
//        @Override
//        public void handle(WindowEvent event) {
//            //ТУТ НЕОБХОДИМАЯ ЛОГИКА
//            //TODO: exix
//            System.out.println("exitt");
//        }
//    };

  //  public javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
    //    return closeEventHandler;
   // }



//askWin
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    public void yesButtonClicked(){
        DataBase.loadBackupFile();
        askWinClose();
    }

    public void noButtonClicked(){
        askWinClose();
    }

    private void askWinClose(){
        Stage stage = (Stage)yesButton.getScene().getWindow();
        stage.close();
    }


}
