package hu.unideb.inf.szekfoglalo_maven_fx;

import hu.unideb.inf.szekfoglalo_maven_fx.hibernate.db.HibernateUtil;
import hu.unideb.inf.szekfoglalo_maven_fx.model.User;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session; //k�s?bb kiszedhet?


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Sz�kfoglal�");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //A DB-bol kiszedne a kulcs (User_name) alapj�n egy User-t
        //�s azt �rn� ki sysout-al
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User)session.get(User.class, "a");
        session.getTransaction().commit();
        session.close();
        System.out.println(user);
        //----------------------------------------------
        HibernateUtil.closeSessionFactory();
    }

}
