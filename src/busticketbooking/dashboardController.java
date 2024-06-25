/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busticketbooking;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author THÁI NGUYÊN
 */
public class dashboardController implements Initializable {

    @FXML
    private Button availableBus_Btn;

    @FXML
    private AnchorPane available_Form;

    @FXML
    private Button available_addBtn;

    @FXML
    private TextField available_busID;

    @FXML
    private TableColumn<busData, String> available_col_busId;

    @FXML
    private TableColumn<busData, String> available_col_date;

    @FXML
    private TableColumn<busData, String> available_col_location;

    @FXML
    private TableColumn<busData, String> available_col_price;

    @FXML
    private TableColumn<busData, String> available_col_status;

    @FXML
    private DatePicker available_date;

    @FXML
    private Button available_deleteBtn;

    @FXML
    private TextField available_location;

    @FXML
    private TextField available_price;

    @FXML
    private Button available_resetBtn;

    @FXML
    private TextField available_search;

    @FXML
    private ComboBox<?> available_status;

    @FXML
    private Button available_updateBtn;

    @FXML
    private Button bookingTicket_Btn;

    @FXML
    private AnchorPane bookingTicket_Form;

    @FXML
    private ComboBox<?> bookingTicket_busId;

    @FXML
    private DatePicker bookingTicket_date;

    @FXML
    private TextField bookingTicket_firstName;

    @FXML
    private ComboBox<?> bookingTicket_gender;

    @FXML
    private TextField bookingTicket_lastName;

    @FXML
    private ComboBox<?> bookingTicket_location;

    @FXML
    private TextField bookingTicket_phone;

    @FXML
    private Button bookingTicket_resetBtn;

    @FXML
    private Label bookingTicket_sci_busID;

    @FXML
    private Label bookingTicket_sci_date;

    @FXML
    private Label bookingTicket_sci_firstName;

    @FXML
    private Label bookingTicket_sci_gender;

    @FXML
    private Label bookingTicket_sci_lastName;

    @FXML
    private Label bookingTicket_sci_location;

    @FXML
    private Button bookingTicket_sci_payBtn;

    @FXML
    private Label bookingTicket_sci_phone;

    @FXML
    private Button bookingTicket_sci_receiptBtn;

    @FXML
    private Label bookingTicket_sci_ticketNum;

    @FXML
    private Label bookingTicket_sci_total;

    @FXML
    private Label bookingTicket_sci_type;

    @FXML
    private Button bookingTicket_selectBtn;

    @FXML
    private ComboBox<?> bookingTicket_ticketNum;

    @FXML
    private ComboBox<?> bookingTicket_type;

    @FXML
    private Button close;

    @FXML
    private Button customers_Btn;

    @FXML
    private TableColumn<?, ?> customers_BusID;

    @FXML
    private AnchorPane customers_Form;

    @FXML
    private TableColumn<?, ?> customers_customerNum;

    @FXML
    private TableColumn<?, ?> customers_date;

    @FXML
    private TableColumn<?, ?> customers_firstName;

    @FXML
    private TableColumn<?, ?> customers_gender;

    @FXML
    private TableColumn<?, ?> customers_lastName;

    @FXML
    private TableColumn<?, ?> customers_location;

    @FXML
    private TableColumn<?, ?> customers_phone;

    @FXML
    private TextField customers_search;

    @FXML
    private TableColumn<?, ?> customers_ticketNum;

    @FXML
    private TableColumn<?, ?> customers_type;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private AnchorPane dashboard_Form;

    @FXML
    private Label dashboard_available_bus;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private Label dashboard_incomeToday;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private TableView<busData> availableB_tableView;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // available bus funt
    public ObservableList<busData> availableBusBusData() {

        ObservableList<busData> busListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM bus";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            busData busD;

            while (result.next()) {
                busD = new busData(result.getInt("bus_id"),
                        result.getString("location"),
                        result.getString("status"),
                        result.getDouble("price"),
                        result.getDate("date"));

                busListData.add(busD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return busListData;
    }

    private ObservableList<busData> availableBBusListData;

    public void availableBShowBusData() {

        availableBBusListData = availableBusBusData();

        available_col_busId.setCellValueFactory(new PropertyValueFactory<>("busId"));
        available_col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        available_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        available_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        available_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableB_tableView.setItems(availableBBusListData);

    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đăng xuất");
            alert.setHeaderText(null);
            alert.setContentText("Bạn muốn đăng xuất?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
//                LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {

                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });
                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void defaultBtn() {
        dashboard_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
        availableBus_Btn.setStyle("-fx-background-color:transparent");
        bookingTicket_Btn.setStyle("-fx-background-color:transparent");
        customers_Btn.setStyle("-fx-background-color:transparent");
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_Btn) {
            dashboard_Form.setVisible(true);
            available_Form.setVisible(false);
            bookingTicket_Form.setVisible(false);
            customers_Form.setVisible(false);

            dashboard_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            availableBus_Btn.setStyle("-fx-background-color:transparent");
            bookingTicket_Btn.setStyle("-fx-background-color:transparent");
            customers_Btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == availableBus_Btn) {
            dashboard_Form.setVisible(false);
            available_Form.setVisible(true);
            bookingTicket_Form.setVisible(false);
            customers_Form.setVisible(false);

            availableBus_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            dashboard_Btn.setStyle("-fx-background-color:transparent");
            bookingTicket_Btn.setStyle("-fx-background-color:transparent");
            customers_Btn.setStyle("-fx-background-color:transparent");

            availableBShowBusData();
            availableSearch();
        } else if (event.getSource() == bookingTicket_Btn) {
            dashboard_Form.setVisible(false);
            available_Form.setVisible(false);
            bookingTicket_Form.setVisible(true);
            customers_Form.setVisible(false);

            bookingTicket_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            availableBus_Btn.setStyle("-fx-background-color:transparent");
            dashboard_Btn.setStyle("-fx-background-color:transparent");
            customers_Btn.setStyle("-fx-background-color:transparent");

            busIdList();
            LocationList();
            typeList();
            ticketNumList();
            genderList();
        } else if (event.getSource() == customers_Btn) {
            dashboard_Form.setVisible(false);
            available_Form.setVisible(false);
            bookingTicket_Form.setVisible(false);
            customers_Form.setVisible(true);

            customers_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            availableBus_Btn.setStyle("-fx-background-color:transparent");
            bookingTicket_Btn.setStyle("-fx-background-color:transparent");
            dashboard_Btn.setStyle("-fx-background-color:transparent");


        }
    }

    public void avaialbleBSelectBusData() {

        busData busD = availableB_tableView.getSelectionModel().getSelectedItem();
        int num = availableB_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        available_busID.setText(String.valueOf(busD.getBusId()));
        available_location.setText(busD.getLocation());
        available_price.setText(String.valueOf(busD.getPrice()));
        available_date.setValue(LocalDate.parse(String.valueOf(busD.getDate())));
    }

    public void availableBusAdd() {

        String addData = "INSERT INTO bus (bus_id,location,status,price,date) VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (available_busID.getText().isEmpty()
                    || available_location.getText().isEmpty()
                    || available_status.getSelectionModel().getSelectedItem() == null
                    || available_price.getText().isEmpty()
                    || available_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Xin hãy điền đủ thông tin");
                alert.showAndWait();

            } else {

                String check = "SELECT bus_id FROM bus WHERE bus_id = '" + available_busID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Bus ID: " + available_busID.getText() + " Đã tồn tại ");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(addData);
                    prepare.setString(1, available_busID.getText());
                    prepare.setString(2, available_location.getText());
                    prepare.setString(3, (String) available_status.getSelectionModel().getSelectedItem());
                    prepare.setString(4, available_price.getText());
                    prepare.setString(5, String.valueOf(available_date.getValue()));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công");
                    alert.showAndWait();

                    availableBShowBusData();
                    availableBusReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBusReset() {
        available_busID.setText("");
        available_location.setText("");
        available_status.getSelectionModel().clearSelection();
        available_price.setText("");
        available_date.setValue(null);
    }

    private String[] statusList = {"Khả dụng", "Không khả dụng"};

    public void comboBoxStatus() {
        List<String> listS = new ArrayList<>();
        for (String data : statusList) {
            listS.add(data);
        }
        ObservableList listStatus = FXCollections.observableArrayList(listS);
        available_status.setItems(listStatus);
    }

    public void availableBusUpdate() {
        String updateData = "UPDATE bus SET location = '"
                + available_location.getText() + "', status = '"
                + available_status.getSelectionModel().getSelectedItem()
                + "', price = '" + available_price.getText()
                + "', date = '" + available_date.getValue()
                + "' WHERE bus_id = '" + available_busID.getText() + "'";

        connect = database.connectDb();

        Alert alert;
        try {
            if (available_busID.getText().isEmpty()
                    || available_location.getText().isEmpty()
                    || available_status.getSelectionModel().getSelectedItem() == null
                    || available_price.getText().isEmpty()
                    || available_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Xin hãy điền đầy đủ thông tin");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật BusID: " + available_busID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công!");
                    alert.showAndWait();

                    availableBShowBusData();
                    availableBusReset();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBusDelete() {

        String deleteData = "DELETE FROM bus WHERE bus_id = '" + available_busID.getText() + "'";
        connect = database.connectDb();
        try {

            Alert alert;

            if (available_busID.getText().isEmpty()
                    || available_location.getText().isEmpty()
                    || available_price.getText().isEmpty()
                    || available_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Xin hãy điền đầy đủ thông tin");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Xóa Bus ID: " + available_busID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa Thành công!");
                    alert.showAndWait();

                    availableBShowBusData();
                    availableBusReset();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableSearch() {

        FilteredList<busData> filter = new FilteredList<>(availableBBusListData, e -> true);

        available_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateBusData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if (predicateBusData.getBusId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getLocation().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<busData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(availableB_tableView.comparatorProperty());
        availableB_tableView.setItems(sortList);
    }

    public void busIdList() {
        String busD = "SELECT * FROM bus WHERE status = 'Khả dụng'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(busD);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {
                listB.add(result.getString("bus_id"));
            }
            bookingTicket_busId.setItems(listB);
            ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    public void LocationList(){
        
        String locationL = "SELECT * FROM bus WHERE status = 'Khả dụng'";
        
        connect = database.connectDb();
        
        try{
            
            prepare = connect.prepareStatement(locationL);
            result = prepare.executeQuery();
            ObservableList listL = FXCollections.observableArrayList();
            while(result.next()){
                listL.add(result.getString("location"));
            }
            bookingTicket_location.setItems(listL);
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    private String[] listT = {"First Class", "Economy Class"};
    
    public void typeList(){
        
        List<String> tList = new ArrayList<>();
        
        for(String data : listT){
            tList.add(data);
        }
        
        ObservableList listType = FXCollections.observableArrayList(tList);
        bookingTicket_type.setItems(listType);
        
    }
    
    public void ticketNumList(){
        List<String> listTicket = new ArrayList<>();
        for(int i = 1; i <= 60; i++){
            listTicket.add(String.valueOf(i));
        }
        
        
        String removeSeat = "SELECT seatNum FROM customer WHERE bus_id='" +bookingTicket_busId.getSelectionModel().getSelectedItem()+"'";

        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(removeSeat);
            result = prepare.executeQuery();
            
            while(result.next()){
                listTicket.remove(result.getString("seatNum"));
            }
            
            ObservableList listTi = FXCollections.observableArrayList(listTicket);
            
            bookingTicket_ticketNum.setItems(listTi);
        }catch(Exception e){e.printStackTrace();}
    }
    

    private double priceData = 0;
    private double totalP = 0;
    public void bookingTicketSelect(){
        
        String firstName = bookingTicket_firstName.getText();
        String lastName = bookingTicket_lastName.getText();
        String gender = (String)bookingTicket_gender.getSelectionModel().getSelectedItem();
        String phoneNumber = bookingTicket_phone.getText();
        String date = String.valueOf(bookingTicket_date.getValue());
        
        String busId = (String)bookingTicket_busId.getSelectionModel().getSelectedItem();
        String location = (String)bookingTicket_location.getSelectionModel().getSelectedItem();
        String type = (String)bookingTicket_type.getSelectionModel().getSelectedItem();
        String ticketNum = (String)bookingTicket_ticketNum.getSelectionModel().getSelectedItem();
        
        Alert alert;
        
        if(firstName == null || lastName == null 
                || gender == null || phoneNumber == null || date == null
                || busId == null || location == null
                || type == null || ticketNum == null){
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Xin hãy điền đủ thông tin");
            alert.showAndWait();
            
        }else{
            
            String totalPrice = "SELECT price FROM bus WHERE location = '" + location + "'";
            try{
                connect = database.connectDb();
                prepare = connect.prepareStatement(totalPrice);
                result = prepare.executeQuery();
                
                if(result.next()){
                    priceData = result.getDouble("price");
                }
                
                if(type == "First Class"){
                    totalP = (priceData + 100);
                }else if(type == "Economy Class"){
                    totalP = priceData; 
                }
            }catch(Exception e){e.printStackTrace();}
            
            bookingTicket_sci_total.setText("$"+String.valueOf(totalP));
            bookingTicket_sci_firstName.setText(firstName);
            bookingTicket_sci_lastName.setText(lastName);
            bookingTicket_sci_gender.setText(gender);
            bookingTicket_sci_phone.setText(phoneNumber);
            bookingTicket_sci_date.setText(date);
            
            bookingTicket_sci_busID.setText(busId);
            bookingTicket_sci_location.setText(location);
            bookingTicket_sci_type.setText(type);
            bookingTicket_sci_ticketNum.setText(ticketNum);
            
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Xác nhận thông tin khách hàng");
            alert.showAndWait();
            
            bookingTicketReset();
            
        }
    }
    
    public void bookingTicketReset(){
        bookingTicket_firstName.setText("");
        bookingTicket_lastName.setText("");
        bookingTicket_gender.getSelectionModel().clearSelection();
        bookingTicket_phone.setText("");
        bookingTicket_date.setValue(null);
    }
    
    private String[] genderL = {"Nam","Nữ","Khác"};
    
    public void genderList(){
        
        List<String> listG = new ArrayList<>();
        
        for(String data : genderL){
            listG.add(data);
        }
        
        ObservableList gList = FXCollections.observableArrayList(listG);
        bookingTicket_gender.setItems(gList);
        
    }
    
    private int countRow;
    public void bookingTicketPay(){
        
        String firstName = bookingTicket_sci_firstName.getText();
        String lastName = bookingTicket_sci_lastName.getText();
        String gender = bookingTicket_sci_gender.getText();
        String phoneNumber = bookingTicket_sci_phone.getText();
        String date = bookingTicket_sci_date.getText();
        
        String busId = bookingTicket_sci_busID.getText();
        String location = bookingTicket_sci_location.getText();
        String type = bookingTicket_sci_type.getText();
        String seatNum = bookingTicket_sci_ticketNum.getText();
        
        String payData = "INSERT INTO customer (customer_id,firstName,lastName,gender,phoneNumber,bus_id,location,type,seatNum,total,date)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            
            Alert alert;
            
            String countNum = "SELECT COUNT(id) FROM customer";
            statement = connect.createStatement();
            result = statement.executeQuery(countNum);
            
            while(result.next()){
                countRow = result.getInt("COUNT(id)");
            }
            
            if(bookingTicket_sci_firstName.getText().isEmpty()
                    || bookingTicket_sci_lastName.getText().isEmpty()
                    || bookingTicket_sci_gender.getText().isEmpty()
                    || bookingTicket_sci_phone.getText().isEmpty()
                    || bookingTicket_sci_date.getText().isEmpty()
                    || bookingTicket_sci_busID.getText().isEmpty()
                    || bookingTicket_sci_location.getText().isEmpty()
                    || bookingTicket_sci_type.getText().isEmpty()
                    || bookingTicket_sci_ticketNum.getText().isEmpty()
                    || totalP == 0){
                
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Xin hãy điền đủ thông tin"+ "");
                alert.showAndWait();
                
            }else{
            
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Xác nhận thanh toán");
                alert.showAndWait();
                prepare = connect.prepareStatement(payData);
                prepare.setString(1, String.valueOf(countRow+1));
                prepare.setString(2, firstName);
                prepare.setString(3, lastName);
                prepare.setString(4, gender);
                prepare.setString(5, phoneNumber);
                prepare.setString(6, busId);
                prepare.setString(7, location);
                prepare.setString(8, type);
                prepare.setString(9, seatNum);
                prepare.setString(10, String.valueOf(totalP));
                prepare.setString(11, date);
                
                prepare.executeUpdate();
                
                
                
                
                
                
                
                String receiptData = "INSERT INTO customer_receipt (customer_id,total,date) VALUES(?,?,?)";
                
//                getData.number = (countRow + 1);
                
                prepare = connect.prepareStatement(receiptData);
                prepare.setString(1, String.valueOf(countRow+1));
                prepare.setString(2, String.valueOf(totalP));
                prepare.setString(3, date);
                
                prepare.executeUpdate();
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Thành công!");
                alert.showAndWait();
                 
                bookingTicket_sci_firstName.setText("");
                bookingTicket_sci_lastName.setText("");
                bookingTicket_sci_gender.setText("");
                bookingTicket_sci_phone.setText("");
                bookingTicket_sci_date.setText("");
                bookingTicket_sci_busID.setText("");
                bookingTicket_sci_location.setText("");
                bookingTicket_sci_type.setText("");
                bookingTicket_sci_ticketNum.setText("");
                bookingTicket_sci_total.setText("$0.0");
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultBtn();
        availableBShowBusData();
        comboBoxStatus();
        availableBusReset();
        busIdList();
        LocationList();
        typeList();
        ticketNumList();
        genderList();
    }

}
