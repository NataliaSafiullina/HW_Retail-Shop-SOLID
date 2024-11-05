package ru.safiullina;

import java.util.List;
import java.util.Scanner;

/**
 * Класс реализует интерфейс Пользовательский интерфейс
 * в виде вывода в консоль.
 */

public class ConsoleUI implements UserInterface<String>{

    private static final Scanner scanner = new Scanner(System.in);
    private static int MAX_NUMBER_OF_LINES = 10;

    @Override
    public void showItems(List<String> list, String listTitle){
        int lines = 0;
        int numberItem = 0;

        if(listTitle == null) listTitle = "";
        System.out.println("\n" + listTitle);

        for (String item : list) {
            // По экранный вывод данных
            if (lines > MAX_NUMBER_OF_LINES) {
                waitForEnter();
                lines = 0;
            }
            // Выводим строку в консоль
            System.out.print(numberItem + " " + item + "\n");
            // Считаем сколько строк на экране и инкриминируем номер пункта
            lines++;
            numberItem++;
        }

    }

    @Override
    public int enterInt(String message) {

        if(message == null) message = "Введите номер пункта: ";
        System.out.print(message);

        String input = scanner.nextLine();

        int item = -1;
        try {
            item = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.printf("\nОшибка: %s. %s. \n \n", message, e);
        }

        return item;
    }

    public void waitForEnter (){
        System.out.println("Нажмите ENTER чтобы продолжить...");
        scanner.nextLine();
    }

}
