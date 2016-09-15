package systemcore.databaseconnection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Prabhath on 7/29/2016.
 */
@Entity
@Table(name="book_details")
public class book_details {
    @Id
    @Column(name="book_id")
    private int book_id;
    @Column(name="added_date")
    private String added_date;
    @Column(name="type_id")
    private String type_id;
    @Column(name="author")
    private String author;
    @Column(name="book_name")
    private String book_name;
    @Column(name="publisher")
    private String publisher;
    @Column(name="published_date")
    private String published_date;
    @Column(name="pages")
    private int pages;
    @Column(name="price")
    private int price;
    @Column(name="supply_medium")
    private String supply_medium;
    @Column(name="another_facts")
    private String another_facts;
    @Column(name="cupboard_no")
    private String cupboard_no;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSupply_medium() {
        return supply_medium;
    }

    public void setSupply_medium(String supply_medium) {
        this.supply_medium = supply_medium;
    }

    public String getAnother_facts() {
        return another_facts;
    }

    public void setAnother_facts(String another_facts) {
        this.another_facts = another_facts;
    }

    public String getCupboard_no() {
        return cupboard_no;
    }

    public void setCupboard_no(String cupboard_no) {
        this.cupboard_no = cupboard_no;
    }
}
