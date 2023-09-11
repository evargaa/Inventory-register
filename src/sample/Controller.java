package sample;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class Controller implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfManufacturer;
    @FXML
    private TextField tfBuiltinmemory;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfMemorytype;
    @FXML
    private TextField tfPieces;
    @FXML
    private TableView<Keszlet> tvKeszlet;
    @FXML
    private TableColumn<Keszlet, Integer> collId;
    @FXML
    private TableColumn<Keszlet, String> collName;
    @FXML
    private TableColumn<Keszlet, String> collCategory;
    @FXML
    private TableColumn<Keszlet, String> collManufacturer;
    @FXML
    private TableColumn<Keszlet, Integer> collBuiltinmemory;
    @FXML
    private TableColumn<Keszlet, Integer> collYear;
    @FXML
    private TableColumn<Keszlet, String> collMemorytype;
    @FXML
    private TableColumn<Keszlet, Integer> collPieces;
    @FXML
    private Button btnBeill;
    @FXML
    private Button btnFrissites;
    @FXML
    private Button btnTorles;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnBeill){
            adatFelvetel();
        }else if (event.getSource() == btnFrissites){
            adatFrissites();
        }else if (event.getSource() == btnTorles){
            adatTorles();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKeszlet();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keszletek","root","");
            return conn;
        }catch (Exception ex){
            System.out.println("Hiba: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Keszlet> getKeszletList(){
        ObservableList<Keszlet> keszletList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM keszlet";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Keszlet keszlet;
            while (rs.next()){
                keszlet = new Keszlet(rs.getInt("id"),rs.getString("name"),rs.getString("category"),
                        rs.getString("manufacturer"),rs.getInt("builtinmemory"),
                        rs.getInt("year"),rs.getString("memorytype"),rs.getInt("pieces"));
                keszletList.add(keszlet);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return keszletList;
    }

    public void showKeszlet(){
        ObservableList<Keszlet> list = getKeszletList();

        collId.setCellValueFactory(new PropertyValueFactory<Keszlet ,Integer>("id"));
        collName.setCellValueFactory(new PropertyValueFactory<Keszlet ,String>("name"));
        collCategory.setCellValueFactory(new PropertyValueFactory<Keszlet ,String>("category"));
        collManufacturer.setCellValueFactory(new PropertyValueFactory<Keszlet ,String>("manufacturer"));
        collBuiltinmemory.setCellValueFactory(new PropertyValueFactory<Keszlet ,Integer>("builtinmemory"));
        collYear.setCellValueFactory(new PropertyValueFactory<Keszlet ,Integer>("year"));
        collMemorytype.setCellValueFactory(new PropertyValueFactory<Keszlet ,String>("memorytype"));
        collPieces.setCellValueFactory(new PropertyValueFactory<Keszlet ,Integer>("pieces"));

        tvKeszlet.setItems(list);
    }

    private void adatFelvetel(){
        String query = "INSERT INTO keszlet VALUES ("+ tfId.getText() + ",'" +tfName.getText() + "','"
                + tfCategory.getText() + "','" + tfManufacturer.getText() + "'," + tfBuiltinmemory.getText()
                + "," + tfYear.getText() + ",'"+tfMemorytype.getText() + "',"+ tfPieces.getText() +")";
        executeQuery(query);
        showKeszlet();
    }
    private void adatFrissites(){
        String query = "UPDATE keszlet SET name = '" + tfName.getText() + "', category = '"+ tfCategory.getText() +"', manufacturer = '"
                + tfManufacturer.getText() +"', builtinmemory= "+ tfBuiltinmemory.getText()+", year = "+ tfYear.getText() +
                ", memorytype = '"+ tfMemorytype.getText() +
                "', pieces = " + tfPieces.getText() +" WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showKeszlet();
    }
    private void adatTorles(){
        String query = "DELETE FROM keszlet WHERE id = " +tfId.getText() + "";
        executeQuery(query);
        showKeszlet();
    }
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;

        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void handleMouseAction(MouseEvent event){
        Keszlet kijelol  = tvKeszlet.getSelectionModel().getSelectedItem();
        tfId.setText("" +kijelol.getId());
        tfName.setText(kijelol.getName());
        tfCategory.setText(kijelol.getCategory());
        tfManufacturer.setText(kijelol.getManufacturer());
        tfBuiltinmemory.setText("" +kijelol.getBuiltinmemory());
        tfYear.setText("" +kijelol.getYear());
        tfMemorytype.setText(kijelol.getMemorytype());
        tfPieces.setText("" +kijelol.getPieces());
    }

}

