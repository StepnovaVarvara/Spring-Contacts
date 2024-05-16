package org.example;

import lombok.SneakyThrows;
import org.example.config.AppConfig;
import org.example.env.ProfileWorker;
import org.example.processor.ContactWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(ProfileWorker.class).setup();
        startWorking(context);
    }

    public static void startWorking(ApplicationContext context) {
        System.out.println("Введите число соответствующее действию: " + "\n" +
                "1 - вывести список контактов" + "\n" +
                "2 - добавить новый контакт" + "\n" +
                "3 - удалить контакт" + "\n" +
                "4 - сохранить контакты в файл" + "\n" +
                "Иное число - завершение работы программы");

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case (1):
                    context.getBean(ContactWorker.class).printAllContact();
                    startWorking(context);
                case (2):
                    context.getBean(ContactWorker.class).addContact();
                    startWorking(context);
                case (3):
                    context.getBean(ContactWorker.class).removeContact();
                    startWorking(context);
                case (4):
                    context.getBean(ContactWorker.class).saveContactsToFile();
                    startWorking(context);
                default:
                    System.out.println("Программа завершила работу");
                    break;
            }
        } else {
            startWorking(context);
        }
    }
}
