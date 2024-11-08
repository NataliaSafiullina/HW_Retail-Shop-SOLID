package ru.safiullina;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time

        // Создаем список Future
        final List<Future> threads = new ArrayList<>();
        // Создаем пул потоков длиной равной размеру массива строк texts
        final ExecutorService threadPool = Executors.newFixedThreadPool(texts.length);


        for (String text : texts) {

            Callable logic = () -> {

                int maxSize = 0;
                for (int i = 0; i < text.length(); i++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (i >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = i; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - i) {
                            maxSize = j - i;
                        }
                    }
                }
                System.out.println(text.substring(0, 100) + " -> " + maxSize);
                return maxSize;

            };

            // Отправляем задачу на выполнение в пул потоков
            Future task = threadPool.submit(logic);
            threads.add(task);

        }

        // Ищем максимальную длинную последовательность
        int max = 0;
        for (Future task : threads) {
            // Получаем результат
            max = Math.max(max, (int) task.get());
        }
        System.out.println("Максимальная длина цепочки = " + max);

        // Завершаем работу пула потоков
        threadPool.shutdown();

        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Time: " + (endTs - startTs) + "ms");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}