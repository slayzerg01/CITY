import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class weight { // класс для вычисления весов
    private int n = CITY.n;
    private double[][] d = new double[n][n]; //массив весов
    TextField[][] text = new TextField[n][n];
    private int mas = CITY.mas;
    private int[][] a;

    //конструктор который подсчитывает весы
    public weight(Stage stage, int[] x, int[] y) {
        BorderPane window = new BorderPane();
        GridPane grid = new GridPane();
        Button start = new Button("Вычислить");
        HBox bot = new HBox();
        for(int i=0;i<n+1;i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50));
            grid.getRowConstraints().add(new RowConstraints(50));
        }
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0){
                    Label label = new Label(String.valueOf(j));
                    grid.add(label,i,j);
                }
                else if(j==0) {
                    Label label = new Label(String.valueOf(i));
                    grid.add(label,i,j);
                }
                else if(i<j){
                    TextField tex = new TextField("0");
                    tex.setAlignment(Pos.CENTER);
                    this.text[i-1][j-1]= tex;
                    grid.add(text[i-1][j-1],i,j);
                }
                else {
                    if (i==j) {
                        TextField tex = new TextField("0");
                        tex.setAlignment(Pos.CENTER);
                        this.text[i-1][j-1]= tex;
                        grid.add(text[i-1][j-1],i,j);
                    }
                    else {
                        TextField tex = new TextField();
                        tex.setAlignment(Pos.CENTER);
                        this.text[i-1][j-1]= tex;
                        grid.add(text[i-1][j-1],i,j);
                    }
                }

            }
        }
        grid.setGridLinesVisible(true); // делаем видимой сетку строк и столбцов
        Stage weights = new Stage();
        weights.setTitle("Весы");
        weights.initModality(Modality.WINDOW_MODAL);
        weights.initOwner(stage);
        window.setCenter(grid);
        bot.getChildren().add(start);
        grid.setAlignment(Pos.BASELINE_CENTER);
        bot.setAlignment(Pos.BASELINE_CENTER);
        window.setBottom(bot);
        bot.setPrefHeight(40F);
        Scene scene = new Scene(window, (n+2)*50, (n+2)*50);
        weights.setScene(scene);
        weights.show();
        start.setOnAction(
                event -> {
                    for(int i=0;i<n;i++){
                        for(int j=0;j<n;j++){
                            d[i][j] = Integer.parseInt(text[j][i].getText()); // записываем из окна числа в массив весов
                        }
                    }
                    find two = new find(d); // создаем объект класса find засовываем туда весы и получаем матрицу соединений вершин
                    a = two.geta(); // записываем его
                    BorderPane graf = new BorderPane(); // новый контейнер
                    //дальше понятно
                    HBox top1 = new HBox();
                    Label texttop = new Label("Граф соединения городов");
                    texttop.setFont(Font.font(30)); // размер шрифта
                    top1.getChildren().add(texttop);
                    top1.setAlignment(Pos.CENTER);
                    top1.setPrefHeight(50F);
                    HBox cent = new HBox();
                    Group pane = this.draw(x,y,a); // засовываем в контейнер нарисованные фигуры круги и линии
                    cent.getChildren().add(pane);
                    graf.setCenter(cent);
                    graf.setTop(top1);
                    Stage showgraf = new Stage();
                    showgraf.setTitle("Граф");
                    showgraf.initModality(Modality.WINDOW_MODAL);
                    showgraf.initOwner(stage);
                    Scene scen = new Scene(graf, 1000, 1000);
                    showgraf.setScene(scen);
                    showgraf.show();
                    });

    }
    public Group draw(int[] x, int[] y, int[][] a){
        Group panel = new Group();
        Circle circle;
        Line line;   // тут и ежу понятно
        Label label;
        // рисуем линии
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if (a[i][j]==1) { //если вершины соединены то рисуем линию
                    line = new Line(x[i]*mas,y[i]*mas,x[j]*mas,y[j]*mas);
                    panel.getChildren().addAll(line);
                }
            }
        }
        // цикл для рисования окружностей
        for (int i=0;i<n;i++){
            circle = new Circle(x[i]*mas,y[i]*mas,0.2*mas, Color.RED); // собственно сами о
            label = new Label(String.valueOf(i)+" ("+x[i]+", "+y[i]+")");
            label.setLayoutX(x[i]*mas+0.1*mas);
            label.setLayoutY(y[i]*mas+0.1*mas); //координаты надписи
            label.setFont(Font.font(0.2*mas));  // это размер шрифта
            panel.getChildren().addAll(circle,label);
        }
        return panel;
    }
}
