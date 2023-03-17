import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class CITY extends Application {
    public static int n;
    public static int mas = 80; //масштаб изображения графика. Чем больше mas тем больше фигуры
    public static void main(String[] args) {
        Application.launch(args);
    }  //основной метод, который запускает прогу

    @Override
    public void start(Stage stage) {
        BorderPane mainWindow = new BorderPane(); // контейнер главного окна
        HBox bot = new HBox(); // горизонтальный контейнер в котором находится кнопка
        VBox center = new VBox(); // вертикальный контейнер в котором находятся поля х и у
        HBox centeru = new HBox(); // горизонтальный контейнер в котором находится х
        HBox centerd = new HBox(); // горизонтальный контейнер в котором находится у
        HBox top = new HBox();  // горизонтальный контейнер в котором находится количество городо n
        bot.setPrefHeight(50F); // отступы
        //control.setSpacing(10F);
        Button start = new Button("Ввести вес ребер"); // кнопка вычислить
        TextField textn = new TextField();
        TextField textx = new TextField();   // поля куда вводятся х у n
        TextField texty = new TextField();
        Label labeln = new Label("Введите количество городов ");
        Label labelx = new Label("Введите  координаты  X:      ");  //  надписи :)
        Label labely = new Label("Введите  координаты  Y:      ");
        labelx.setPadding(new Insets(10, 10, 10, 10));
        labely.setPadding(new Insets(10, 10, 10, 10));   // тоже отступы
        top.setPadding(new Insets(10, 10, 10, 10));
        centeru.getChildren().addAll(labelx,textx);  // засовываем тект и поле в контейнеры
        centerd.getChildren().addAll(labely,texty); // засовываем тект и поле в контейнеры
        centeru.setAlignment(Pos.CENTER);
        centerd.setAlignment(Pos.CENTER); // выравнивание по центру контейнера
        top.setAlignment(Pos.CENTER);
        bot.setAlignment(Pos.CENTER);
        center.getChildren().addAll(centeru,centerd); // засовываем горизонтальные контейнеры в вертикальные)
        center.setSpacing(10.0);
        bot.getChildren().addAll(start); //засовываем в контейнер кнопку
        top.getChildren().addAll(labeln,textn); //  засовываем тект и поле в контейнеры
        mainWindow.setCenter(center); //задаем центр главного контейнера
        mainWindow.setBottom(bot); //задаем подвал главного контейнера
        mainWindow.setTop(top); //задаем крышу главного контейнера
        stage.setScene(new Scene(mainWindow, 410, 200)); // создаём новую сцену (окно)
        stage.setTitle("CITY"); //задаём титульник или название окна хз
        stage.setResizable(false); // делает невозможным изменение размера окна
        stage.show(); // показывает окно
        start.setOnAction( //регистрируется нажатие на кнопку
                event -> {
                    n = Integer.parseInt(textn.getText()); // записывается с поля число n
                    // записывается массив х из поля
                    int[] x = Arrays.stream(textx.getText().split(" ")).mapToInt(Integer::parseInt).toArray();
                    // записывается массив у из поля
                    int[] y = Arrays.stream(texty.getText().split(" ")).mapToInt(Integer::parseInt).toArray();
                    if (x.length == y.length && n == x.length){ // проверка чтобы не было так типа количесвто х=3 а у=2
                        // или ввела количство n=5, а на иксов или игреков на самом деле не такое количество
                        weight one = new weight(stage,x,y); // создаем объект класса weight засовываем туда х и у. там же рисуется граф
                    }
                    // если не то со входными данными
                    // по хорошему тут надо допилить, чтоб окно выходило с сообщение, а не тупо вывод в консоль текста
                    //Кста надо бы сверху новое овно тоже по-другому реализовать, но это потом)
                    // upd допилил вывод и окна)
                    else {
                        Stage showerror = new Stage();
                        showerror.setTitle("Ошибка");
                        showerror.initModality(Modality.WINDOW_MODAL);
                        showerror.initOwner(stage);
                        Label error = new Label("Количество x и y должно быть равно " + String.valueOf(n));
                        error.setAlignment(Pos.CENTER);
                        Scene scen = new Scene(error, 300, 100);
                        showerror.setScene(scen);
                        showerror.show();
                    }
                });
    }
}

