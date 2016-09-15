package systemcore.validation;

import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import systemcore.databaseconnection.book_details;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Prabhath on 7/30/2016.
 */
public class BookDetailsValidation {
    public boolean validateBookName(String bookName) {
        if(bookName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Name, Cannot be Empty");
            alert.showAndWait();
            return false;
        } else return true;
    }

    public boolean validateBookId(int bookId, String bookEx) {
        SessionFactory factory = null;
        List result = null;
        if (bookId == 0 && bookEx.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Id, Cannot be Empty");
            alert.showAndWait();
            return false;
        } else if (bookId==0 && bookEx.equals("ex")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Only Numbers are valid to Book Id");
            alert.showAndWait();
            return false;
         }else {
            boolean val = false;
            List<book_details> list = null;
            try {
                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(book_details.class)
                        .buildSessionFactory();

                Session session = factory.getCurrentSession();
                session.beginTransaction();

                try {
                    Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
                    list = query.list();
                    session.getTransaction().commit();
                } catch (HibernateException he) {
                    he.printStackTrace();
                } finally {
                    factory.close();
                }
                if(list.isEmpty()) val = true;
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Book Id, Already Entered");
                    //alert.setContentText("Book Id, Already Entered");
                    alert.showAndWait();
                }
            } finally {
                factory.close();
            }
            return (true && val);
        }

    }

    public boolean validateBookIdEdit(int bookId, String bookEx) {
        SessionFactory factory = null;
        List result = null;
        if (bookId == 0 && bookEx.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Id, Cannot be Empty");
            alert.showAndWait();
            return false;
        } else if (bookId==0 && bookEx.equals("ex")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Only Numbers are valid to Book Id");
            alert.showAndWait();
            return false;
        }else {
            boolean val = false;
            List<book_details> list = null;
            try {
                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(book_details.class)
                        .buildSessionFactory();

                Session session = factory.getCurrentSession();
                session.beginTransaction();

                try {
                    Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
                    list = query.list();
                    session.getTransaction().commit();
                } catch (HibernateException he) {
                    he.printStackTrace();
                } finally {
                    factory.close();
                }
                if(list.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please Enter a Correct Book Id");
                    alert.showAndWait();
                }
                else {
                    val = true;
                }
            } finally {
                factory.close();
            }
            return (true && val);
        }

    }

    public boolean validateBookIdDelete(int bookId, String bookEx) {
        SessionFactory factory = null;
        List result = null;
        if (bookId == 0 && bookEx.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book Id, Cannot be Empty");
            alert.showAndWait();
            return false;
        } else if (bookId==0 && bookEx.equals("ex")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Only Numbers are valid to Book Id");
            alert.showAndWait();
            return false;
        }else {
            boolean val = false;
            List<book_details> list = null;
            try {
                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(book_details.class)
                        .buildSessionFactory();

                Session session = factory.getCurrentSession();
                session.beginTransaction();

                try {
                    Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
                    list = query.list();
                    session.getTransaction().commit();
                } catch (HibernateException he) {
                    he.printStackTrace();
                } finally {
                    factory.close();
                }
                if(list.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please Enter a Correct Book Id");
                    alert.showAndWait();
                }
                else {
                    val = true;
                }
            } finally {
                factory.close();
            }
            return (true && val);
        }
    }
}
