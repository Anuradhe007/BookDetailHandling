package systemcore.databaseconnection;

import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Prabhath on 7/29/2016.
 */
public class DatabaseConnection<T> {
    SessionFactory factory = null;
    Session session = null;
    public DatabaseConnection() {

        try {
             factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(book_details.class)
                    .buildSessionFactory();
             session = factory.getCurrentSession();
            session.beginTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void saveBookDetails(T dataObject) {
        try {
            System.out.println("saving the Data object............................");
            session.save(dataObject);
            session.getTransaction().commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Added Book Record");
            alert.showAndWait();
        } catch (HibernateException he) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cannot Add Book Record");
            alert.showAndWait();
            he.printStackTrace();
        } finally {
            factory.close();
        }
    }

    public book_details getBookDetails(int bookId) {
        List<book_details> list = null;
        try {
            Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
             list = query.list();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            factory.close();
        }
        return list.get(0);
    }

    public void editBookDetails(int bookId) {
        List<book_details> list = null;
        try {
            Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
            list = query.list();
            book_details edit = list.get(0);

            session.getTransaction().commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Deleted Book Record");
            alert.showAndWait();
        } catch(RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Can not Delete Book Record");
            alert.showAndWait();
            re.printStackTrace();
        } finally {
            factory.close();
        }
    }

    public void deleteBookRecords(int bookId) {
        List<book_details> list = null;
        try {
            Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
            list = query.list();
            session.delete(list.get(0));
            session.getTransaction().commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Deleted Book Record");
            alert.showAndWait();
        } catch(RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Can not Delete Book Record");
            alert.showAndWait();
            re.printStackTrace();
        } finally {
            factory.close();
        }
    }

    /*
    * Search book details
     */
    public book_details searchBookDetailsId(int bookId) {
        List<book_details> list = null;
        try {
            Query query = session.createQuery("from book_details where book_id =:bookId").setParameter("bookId",bookId);
            list = query.list();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            factory.close();
        }
        return list.get(0);
    }

}
