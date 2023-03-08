package com.example.finalprojectce2014;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private TextField ssn;
    @FXML
    private TextField firstname;

    @FXML
    private TextField mi;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phoneno;
    @FXML
    private TextField street;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField deptid;
    @FXML
    private Button add;

    @FXML
    private TableColumn<studentData, Date> tabledob;

    @FXML
    private BarChart<?, ?> chart1;

    @FXML
    private BarChart<?, ?> chart2;

    @FXML
    private Button close;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<studentData, String> tabledep;

    @FXML
    private DatePicker dob;

    @FXML
    private TableColumn<studentData,String> tablefirst;

    @FXML
    private Button home;

    @FXML
    private AnchorPane homepage;

    @FXML
    private AnchorPane insert1;

    @FXML
    private AnchorPane insert2;

    @FXML
    private TableColumn<studentData, String> tablelast;

    @FXML
    private Button list;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane mainform;

    @FXML
    private TableColumn<studentData, String> tablemi;

    @FXML
    private Button minimize;

    @FXML
    private Label one;

    @FXML
    private TableColumn<studentData, Integer> tableph;

    @FXML
    private Button plus;

    @FXML
    private Button reset;

    @FXML
    private TableColumn<studentData, Integer> tablessn;

    @FXML
    private TableColumn<studentData, String> tablestreet;

    @FXML
    private TableView<studentData> table;

    @FXML
    private Label three;

    @FXML
    private Label two;

    @FXML
    private Button upadte;

    @FXML
    private Label user;

    @FXML
    private TableColumn<studentData, Integer> tablezip;

    @FXML
    private ImageView image;


    @FXML
    private PieChart piechart;

    @FXML
    private AnchorPane imageframe;

    @FXML
    private Button imageimport;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image imagedata;

    private ObservableList data;

    public void homeDisplayTotal(){
        String sql = "SELECT COUNT(id) FROM data";
        connect = database.connectDb();

        int countStudents = 0;
        try{
            prepare =connect.prepareStatement(sql);
            result =prepare.executeQuery();

            while (result.next()){
                countStudents = result.getInt("COUNT(id)");
            }
            one.setText(String.valueOf(countStudents));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void homeChart1(){
        chart1.getData().clear();
        String sql = "SELECT deptid, id FROM data GROUP BY deptid";
        connect = database.connectDb();
        try{
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2))
                );
            }
            chart1.getData().add(chart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void homeChart2(){
        chart2.getData().clear();
        String sql = "SELECT  phone, id FROM data GROUP BY phone";
        connect = database.connectDb();
        try{
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2))
                );
            }
            chart2.getData().add(chart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void pieChart(){
        piechart.getData().clear();

                //Setting data
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                        new PieChart.Data("BIOL", 10),
                        new PieChart.Data("CHEM", 2),
                        new PieChart.Data("CS", 8),
                        new PieChart.Data("MATH", 4)
                );
                piechart.setData(data);
            }




    public void addStudentAdd(){
        String nulldate;
        String sql ="INSERT INTO data (ssn,firstName,mi,lastName,phone,birthDate,street,zipcode,deptid) " +
                "VALUES( ?,?,?,?,?,?,?,?,?)";
        connect = database.connectDb();
        try {
            Alert alert;
            if (ssn.getText().isEmpty() ||
                    firstname.getText().isEmpty() ||
                    mi.getText().isEmpty() ||
                    lastname.getText().isEmpty() ||
                    phoneno.getText().isEmpty() ||
                    street.getText().isEmpty() ||
                    zipcode.getText().isEmpty() ||
                    deptid.getText().isEmpty()
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {

                String checkData ="SELECT ssn FROM data WHERE ssn ='"
                + ssn.getText()+"' ";

                statement =connect.createStatement();
                result= statement.executeQuery(checkData);

                if (result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText(ssn.getText()+" already exist!");
                    alert.showAndWait();

                }else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, ssn.getText());
                    prepare.setString(2, firstname.getText());
                    prepare.setString(3, mi.getText());
                    prepare.setString(4, lastname.getText());
                    prepare.setString(5, phoneno.getText());
                    prepare.setString(6, String.valueOf(dob.getValue()));
                    prepare.setString(7, street.getText());
                    prepare.setString(8, zipcode.getText());
                    prepare.setString(9, deptid.getText());


                    prepare.executeUpdate();

                    addStudentShowListData();

                    addStudentReset();
                }
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addStudentUpdate(){

        String sql = "UPDATE data SET firstName = '"+firstname.getText()
                +"', mi = '"+mi.getText()
                +"',lastName ='"+lastname.getText()
                +"',phone ='"+phoneno.getText()
                +"',birthDate ='"+dob.getValue()
                +"',street ='"+street.getText()
                +"',zipcode ='"+zipcode.getText()
                +"',deptid ='"+deptid.getText()+"' WHERE ssn ='"
                +ssn.getText()+"'";
        try{
            Alert alert;
            if (ssn.getText().isEmpty() ||
                    firstname.getText().isEmpty() ||
                    mi.getText().isEmpty() ||
                    lastname.getText().isEmpty() ||
                    phoneno.getText().isEmpty() ||
                    street.getText().isEmpty() ||
                    zipcode.getText().isEmpty() ||
                    deptid.getText().isEmpty()
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {

                alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Update ?"+ssn.getText()+ "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Updated Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Updated Successfully");
                    alert.showAndWait();

                    addStudentShowListData();
                    addStudentReset();

                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void addStudentDelete() {
        String sql = "DELETE FROM data  WHERE ssn ='"
                + ssn.getText()
                + "'";
        connect = database.connectDb();
        try {
            Alert alert;
            if (ssn.getText().isEmpty()
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {
                alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Delete ?"+ssn.getText()+ "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Updated Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Deleted Successfully");
                    alert.showAndWait();

                    addStudentShowListData();
                    addStudentReset();


                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        public void addStudentReset(){
        ssn.setText("");
        firstname.setText("");
        mi.setText("");
        lastname.setText("");
        phoneno.setText("");
        dob.setValue(null);
        street.setText("");
        zipcode.setText("");
        deptid.setText("");
        image.setImage(null);

        getData.path= "";
    }
    public void addStudentImportImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file","*jpg","*png"));

        File file = open.showOpenDialog(mainform.getScene().getWindow());

        if(file !=null){
            getData.path = file.getAbsolutePath();
            imagedata = new Image(file.toURI().toString(),97,72,false,true);
            image.setImage(imagedata);
        }
    }

    public ObservableList<studentData> addStudentListData(){
        ObservableList<studentData> studentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM data";
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            studentData stdD;
            while ((result.next())){
                stdD = new studentData(result.getInt("ssn"),
                        result.getString("firstName"),
                        result.getString("mi"),
                        result.getString("lastName"),
                        result.getString("phone"),
                        result.getDate("birthDate"),
                        result.getString("street"),
                        result.getInt("zipcode"),
                        result.getString("deptid")
                );
                studentList.add(stdD);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
    private  ObservableList<studentData> addstudentList;

    public void addStudentShowListData(){
        addstudentList = addStudentListData();
        tablessn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        tablefirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tablemi.setCellValueFactory(new PropertyValueFactory<>("mi"));
        tablelast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableph.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tabledob.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tablestreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        tablezip.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        tabledep.setCellValueFactory(new PropertyValueFactory<>("deptid"));



        table.setItems(addstudentList);



    }

    public  void switchForm(ActionEvent event){
        if(event.getSource()==home){
            homepage.setVisible(true);
            insert2.setVisible(false);
            insert1.setVisible(false);

            homeDisplayTotal();
            homeChart1();
            homeChart2();
            pieChart();
        } else if (event.getSource()==plus) {
            homepage.setVisible(false);
            insert2.setVisible(true);
            insert1.setVisible(true);
        }else if (event.getSource()==list){
        }

    }
    private  double x = 0;
    private double y = 0;

    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout ?");
            Optional<ButtonType>option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getSceneX() -x);
                    stage.setY(event.getSceneY() -y);

                    stage.setOpacity(.8);
                });
                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }else return;;
        }
        catch (Exception e){e.printStackTrace();}
    }

    public void minimize(){
        Stage stage;
        stage = (Stage)mainform.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close(){
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addStudentShowListData();
        homeDisplayTotal();
        homeChart1();
        homeChart2();
        pieChart();

    }
}
