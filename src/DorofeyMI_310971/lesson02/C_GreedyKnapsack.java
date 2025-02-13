package DorofeyMI_310971.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;
        double costPerUnitWeight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.costPerUnitWeight = (double) cost / weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item other) {
            return Double.compare(other.costPerUnitWeight, this.costPerUnitWeight);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }

        Arrays.sort(items);   // сортируем предметы
        double result = 0;
        for (Item item : items) {
            if (W >= item.weight) {
                // Если вес предмета меньше или равен оставшемуся объему рюкзака
                result += item.cost;
                W -= item.weight;
            } else {
                // Берем часть предмета, если он не помещается целиком
                result += (double) W * item.costPerUnitWeight;
                break; // Рюкзак заполнен
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+ "DorofeyMI_310971/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}