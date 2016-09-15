package systemcore.bookdetails;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import systemcore.databaseconnection.DatabaseConnection;
import systemcore.databaseconnection.book_details;
import systemcore.validation.BookDetailsValidation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Prabhath on 7/26/2016.
 */
public class BookDetailsController {
    @FXML
    private TextField book_id,type_id,author,book_name,publisher,published_date,pages,price,supply_medium,cupboard_no;
    @FXML
    private TextArea other_facts;
    @FXML
    private DatePicker added_date,removed_date;

    public void saveButtonClicked() {
        BookDetailsValidation validation = new BookDetailsValidation();
        int bookId=0,page=0,prices=0;
        String bookEx="",added_dat="",rem_dat="";
        LocalDate dat = LocalDate.now();
        added_dat = String.valueOf(dat);
        rem_dat = String.valueOf(dat);
        boolean val_book_id,val_book_name;
        if(!book_id.getText().equals("")) {
            try {
                bookId = Integer.parseInt(book_id.getText());
            } catch (NumberFormatException ex) {
                bookEx="ex";
            }
        }
        if(!pages.getText().equals("")) {
            page = Integer.parseInt(pages.getText());
        }
        if(!price.getText().equals("")) {
            prices = Integer.parseInt(price.getText());
        }
        if(added_date.getValue()!=null){
            added_dat = String.valueOf(added_date.getValue());
        }
        if(removed_date.getValue()!=null) {
            rem_dat = String.valueOf(removed_date.getValue());
        }

        val_book_id = validation.validateBookId(bookId,bookEx);
        val_book_name = validation.validateBookName(book_name.getText());
        if(val_book_id && val_book_name) {
            book_id.setStyle("");
            book_name.setStyle("");
            book_details saveDetails = new book_details();
            saveDetails.setBook_id(Integer.parseInt(book_id.getText()));
            saveDetails.setAdded_date(added_dat);
            saveDetails.setType_id(type_id.getText());
            saveDetails.setAuthor(author.getText());
            saveDetails.setBook_name(book_name.getText());
            saveDetails.setPublisher(publisher.getText());
            saveDetails.setPublished_date(published_date.getText());
            saveDetails.setPages(page);
            saveDetails.setPrice(prices);
            saveDetails.setSupply_medium(supply_medium.getText());
            saveDetails.setAnother_facts(other_facts.getText());
            saveDetails.setCupboard_no(cupboard_no.getText());

            DatabaseConnection<book_details> saveDet = new DatabaseConnection<>();
            saveDet.saveBookDetails(saveDetails);

            book_id.setText("");
            type_id.setText("");
            author.setText("");
            book_name.setText("");
            publisher.setText("");
            published_date.setText("");
            pages.setText("");
            price.setText("");
            supply_medium.setText("");
            cupboard_no.setText("");
            other_facts.setText("");
            added_date.setValue(null);
            removed_date.setValue(null);
        } else {
            if(!val_book_id && !val_book_name) {
                book_id.setStyle("-fx-text-box-border: red;");
                book_name.setStyle("-fx-text-box-border: red;");
            }
            else if(!val_book_name) book_name.setStyle("-fx-text-box-border: red;");
            else {
                book_id.setStyle("-fx-text-box-border: red;");
            }
        }
    }

    public void cancelButtonClicked() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("All the Data Fields will be Emptied");
        alert.setContentText("Are you sure about this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            book_id.setText("");
            type_id.setText("");
            author.setText("");
            book_name.setText("");
            publisher.setText("");
            published_date.setText("");
            pages.setText("");
            price.setText("");
            supply_medium.setText("");
            cupboard_no.setText("");
            other_facts.setText("");
            added_date.setValue(null);
            removed_date.setValue(null);
        }
    }

    @FXML
    private TextField book_id_edit,type_id_edit,author_edit,book_name_edit,publisher_edit,published_date_edit,pages_edit,price_edit,supply_medium_edit,cupboard_no_edit;
    @FXML
    private TextArea other_facts_edit;
    @FXML
    private DatePicker added_date_edit,removed_date_edit;

    public void searchButtonClicked() {
        BookDetailsValidation validation = new BookDetailsValidation();
        int bookId=0,page=0,prices=0;
        String bookEx="";
        boolean val_book_id;
        if(!book_id_edit.getText().equals("")) {
            try {
                bookId = Integer.parseInt(book_id_edit.getText());
            } catch (NumberFormatException ex) {
                bookEx="ex";
            }
        }



        if(!pages_edit.getText().equals("")) {
            page = Integer.parseInt(pages_edit.getText());
        }
        if(!price_edit.getText().equals("")) {
            prices = Integer.parseInt(price_edit.getText());
        }
        val_book_id = validation.validateBookIdEdit(bookId,bookEx);
        if(val_book_id) {
            book_id_edit.setStyle("");

            type_id_edit.setText("");
            author_edit.setText("");
            book_name_edit.setText("");
            publisher_edit.setText("");
            published_date_edit.setText("");
            pages_edit.setText("");
            price_edit.setText("");
            supply_medium_edit.setText("");
            cupboard_no_edit.setText("");
            other_facts_edit.setText("");
            added_date_edit.setValue(null);
            removed_date_edit.setValue(null);

            DatabaseConnection<book_details> dbconn = new DatabaseConnection<>();
            book_details book_det = dbconn.getBookDetails(bookId);
            type_id_edit.setText(book_det.getType_id());
            author_edit.setText(book_det.getAuthor());
            book_name_edit.setText(book_det.getBook_name());
            added_date_edit.setValue(LocalDate.parse(book_det.getAdded_date()));
            publisher_edit.setText(book_det.getPublisher());
            published_date_edit.setText(book_det.getPublished_date());
            pages_edit.setText(String.valueOf(book_det.getPages()));
            price_edit.setText(String.valueOf(book_det.getPrice()));
            supply_medium_edit.setText(book_det.getSupply_medium());
            cupboard_no_edit.setText(book_det.getCupboard_no());
            other_facts_edit.setText(book_det.getAnother_facts());
        } else {
            book_id_edit.setStyle("-fx-text-box-border: red;");

            type_id_edit.setText("");
            author_edit.setText("");
            book_name_edit.setText("");
            publisher_edit.setText("");
            published_date_edit.setText("");
            pages_edit.setText("");
            price_edit.setText("");
            supply_medium_edit.setText("");
            cupboard_no_edit.setText("");
            other_facts_edit.setText("");
            added_date_edit.setValue(null);
            removed_date_edit.setValue(null);
        }
    }

    public void editButtonClicked() {
        BookDetailsValidation validation = new BookDetailsValidation();
        int bookId=0,page=0,prices=0;
        String bookEx="",added_dat="",rem_dat="";
        LocalDate dat = LocalDate.now();
        added_dat = String.valueOf(dat);
        rem_dat = String.valueOf(dat);
        boolean val_book_id,val_book_name;
        if(!book_id_edit.getText().equals("")) {
            try {
                bookId = Integer.parseInt(book_id.getText());
            } catch (NumberFormatException ex) {
                bookEx="ex";
            }
        }
        if(!pages_edit.getText().equals("")) {
            page = Integer.parseInt(pages_edit.getText());
        }
        if(!price_edit.getText().equals("")) {
            prices = Integer.parseInt(price_edit.getText());
        }
        if(added_date_edit.getValue()!=null){
            added_dat = String.valueOf(added_date_edit.getValue());
        }
        if(removed_date_edit.getValue()!=null) {
            rem_dat = String.valueOf(removed_date_edit.getValue());
        }

        val_book_id = validation.validateBookId(bookId,bookEx);
        val_book_name = validation.validateBookName(book_name_edit.getText());
        if(val_book_id && val_book_name) {
            book_id_edit.setStyle("");
            book_name_edit.setStyle("");
            book_details saveDetails = new book_details();
            //saveDetails.setBook_id(Integer.parseInt(book_id.getText()));
            saveDetails.setAdded_date(added_dat);
            saveDetails.setType_id(type_id_edit.getText());
            saveDetails.setAuthor(author_edit.getText());
            saveDetails.setBook_name(book_name_edit.getText());
            saveDetails.setPublisher(publisher_edit.getText());
            saveDetails.setPublished_date(published_date_edit.getText());
            saveDetails.setPages(page);
            saveDetails.setPrice(prices);
            saveDetails.setSupply_medium(supply_medium_edit.getText());
            saveDetails.setAnother_facts(other_facts_edit.getText());
            saveDetails.setCupboard_no(cupboard_no_edit.getText());

            DatabaseConnection<book_details> saveDet = new DatabaseConnection<>();
            saveDet.saveBookDetails(saveDetails);

            book_id_edit.setText("");
            type_id_edit.setText("");
            author_edit.setText("");
            book_name_edit.setText("");
            publisher_edit.setText("");
            published_date_edit.setText("");
            pages_edit.setText("");
            price_edit.setText("");
            supply_medium_edit.setText("");
            cupboard_no_edit.setText("");
            other_facts_edit.setText("");
            added_date_edit.setValue(null);
            removed_date_edit.setValue(null);
        } else {
            if(!val_book_id && !val_book_name) {
                book_id_edit.setStyle("-fx-text-box-border: red;");
                book_name_edit.setStyle("-fx-text-box-border: red;");
            }
            else if(!val_book_name) book_name_edit.setStyle("-fx-text-box-border: red;");
            else {
                book_id_edit.setStyle("-fx-text-box-border: red;");
            }
        }
    }

    public void cancelButtonEdit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("All the Data Fields will be Emptied");
        alert.setContentText("Are you sure about this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            book_id_edit.setText("");
            type_id_edit.setText("");
            author_edit.setText("");
            book_name_edit.setText("");
            publisher_edit.setText("");
            published_date_edit.setText("");
            pages_edit.setText("");
            price_edit.setText("");
            supply_medium_edit.setText("");
            cupboard_no_edit.setText("");
            other_facts_edit.setText("");
            added_date_edit.setValue(null);
            removed_date_edit.setValue(null);
        }
    }

    @FXML
    private TextField bookId_delete;

    public void deleteButtonClicked() {
        BookDetailsValidation validation = new BookDetailsValidation();
        int bookId=0,page=0,prices=0;
        String bookEx="";
        boolean val_book_id;
        if(!bookId_delete.getText().equals("")) {
            try {
                bookId = Integer.parseInt(bookId_delete.getText());
            } catch (NumberFormatException ex) {
                bookEx="ex";
            }
        }
        val_book_id = validation.validateBookIdEdit(bookId,bookEx);
        if(val_book_id) {
            bookId_delete.setStyle("");
            DatabaseConnection<book_details> bookDet = new DatabaseConnection<>();
            bookDet.deleteBookRecords(bookId);
            bookId_delete.setText("");
        } else {
            bookId_delete.setStyle("-fx-text-box-border: red;");
        }
    }

    /*
    *Search Books
     */
    @FXML
    private TextField book_id_search;
    public void searchBookId() {
        Stage primaryStage = null;
        Parent root = null;
        try {
            DatabaseConnection<book_details> dbconn = new DatabaseConnection<>();
            book_details book_det = dbconn.searchBookDetailsId(Integer.parseInt(String.valueOf(book_id_search)));
            primaryStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("allBookDet.fxml"));
            primaryStage.setTitle("Library Management System");
            primaryStage.setScene(new Scene(root,880,1500));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
