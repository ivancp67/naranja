package ceu.dam.javafx.practica31;

import ceu.dam.javafx.practica31.gui.AppController;
import ceu.dam.javafx.practica31.gui.FxmlPaths;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
  public void start(Stage primaryStage)
    throws Exception
  {
    AppController controller = new AppController(primaryStage);
    controller.cambiarVista(FxmlPaths.CONSULTA);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(
      new String[0]);
  }
}