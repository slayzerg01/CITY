import java.util.Arrays;

// класс для нахождения соединении вершин
public class find {
    private int n = CITY.n;
    private int[][] a = new int[n][n];  // тут все ясно
    private int[] label = new int[n];
    private double[][] d;

    public find(double[][] weigth) {
        d = weigth; // получаем расчитанные до этго весы
        for (int i = 0; i < n; i++) {
            label[i] = i; // создаем метки
            for (int j = 0; j < n; j++){
                a[i][j] = 0; // обнуляем массив соединений
            }
        }
        explore();

    }
    //метод для поиска соединений
    private void explore(){
        int k = 0;
        do {
            int imax = 0;
            int jmax = 0;
            double min = 10000;
            //находим минимальный вес
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] < min && d[i][j] != 0) {
                        if (label[i] != label[j]) { // и чтоб этот вес был из разных меток (помнишь пример с цветами
                            //это тоже самое, только вместо цветов цифры)
                            min = d[i][j];
                            imax = i; // записываем минимумы и максимымы
                            jmax = j;
                        }
                    }
                }
            }
            // перзаписываем метки вершин одной цифрой ( или перекрашиваем соединенные вершины одним цветом)
            for (int l = 0; l < n; l++) {
                if (label[l] == label[jmax])
                    label[l] = label[imax];
            }
            a[imax][jmax] = 1;
            a[jmax][imax] = 1; // записываем соединение вершин
            d[imax][jmax] = 0;
            d[jmax][imax] = 0; // удаляем минималный вес, чтоб не мешал, т.к. его уже рассмотрели
            /*
            эта часть кода не нужна можно удалить если хочешь
            System.out.print("min = " + min + " i,j=" + imax + " " + jmax + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++){
                    System.out.print(d[i][j] + " ");}
                System.out.print("\n");
            } */
            k++;
        } while ((n - k) != 1); // делаем до тех пор пока не рассмотрим n-1 вершин
        /* тоже не обязательный код просто в консоль выводит матрицу а и соединения вершин
        System.out.print("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                System.out.print(a[i][j] + " ");}
            System.out.print("\n");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]==1){
                    System.out.println("i "+(i+1)+" j "+(j+1));
                }
            }
        }*/
    }
    // получаем матрицу а
    public int[][] geta(){
        return a;
    }
}