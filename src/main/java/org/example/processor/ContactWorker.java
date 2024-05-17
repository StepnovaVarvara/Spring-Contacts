package org.example.processor;

import lombok.SneakyThrows;
import org.example.dto.Contact;
import org.example.dto.NoteBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class ContactWorker {
    @Value("${app.processor.nameRegex}")
    private String nameRegex;
    @Value("${app.processor.phoneRegex}")
    private String phoneRegex;
    @Value("${app.processor.emailRegex}")
    private String emailRegex;
    @Value("${app.filePath.init}")
    private String initFile;
    @Value("${app.filePath.save}")
    private String saveFile;

    @SneakyThrows
    public void saveInitContacts() {
        File file = new File(initFile);

        List<Contact> contactList = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String[] lineToArray = input.nextLine().split(";");

            Contact contact = new Contact()
                    .setFullName(lineToArray[0])
                    .setPhoneNumber(lineToArray[1])
                    .setEmail(lineToArray[2]);
            contactList.add(contact);
        }

        NoteBook.setContactList(contactList);
        input.close();
    }


    public void printAllContact() {
        if (NoteBook.getContactList().isEmpty()) {
            System.out.println("Лист контактов пуст");
        } else {
            for (Contact contact : NoteBook.getContactList()) {
                System.out.println(contact.getFullName() + " | "
                        + contact.getPhoneNumber() + " | "
                        + contact.getEmail());
            }
        }
    }


    public void addContact() {
        System.out.println("Введите данные контакта в формате: " +
                "\"Иванов Иван Иванович; +89099999991; someEmail@example.example\"");

        Scanner scanner = new Scanner(System.in);
        String[] stringToArray = scanner.nextLine().split(";");
        if (stringToArray.length != 3) {
            System.out.println("Ввод несоответствует формату");
        }

        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(stringToArray[0].trim());
        if (!nameMatcher.matches()) {
            System.out.println("Некорректное имя");
        }

        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(stringToArray[1].trim());
        if (!phoneMatcher.matches()) {
            System.out.println("Некорректный номер телефона");
        }

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(stringToArray[2].trim());
        if (!emailMatcher.matches()) {
            System.out.println("Некорректный email");
        }

        int duplicate = 0;
        List<Contact> contactList = NoteBook.getContactList();
        for (Contact contact : contactList) {
            if (contact.getEmail().equals(stringToArray[2].trim())) {
                duplicate += 1;
            }
        }
        if (duplicate != 0) {
            System.out.println("Контакт с таким email уже существует");
        } else {
            Contact contact = new Contact()
                    .setFullName(stringToArray[0].trim())
                    .setPhoneNumber(stringToArray[1].trim())
                    .setEmail(stringToArray[2].trim());

            contactList.add(contact);
            NoteBook.setContactList(contactList);

            System.out.println("Новый контакт успешно добавлен");
        }
    }


    public void removeContact() {
        System.out.println("Введите email контакта, который необходимо удатить");

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine().trim();

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            System.out.println("Некорректный email");
        } else {
            List<Contact> contactList = NoteBook.getContactList();
            int contactCount = NoteBook.getContactList().size();

            Iterator<Contact> iterator = contactList.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if (contact.getEmail().equals(email)) {
                    iterator.remove();
                }
            }

            if (contactCount == contactList.size()) {
                System.out.println("Контакт с таким email не найден");
            } else {
                NoteBook.setContactList(contactList);
                System.out.println("Контакт успешно удален");
            }
        }
    }


    @SneakyThrows
    public void saveContactsToFile() {
        PrintWriter printWriter = new PrintWriter(saveFile); //TODO если через Value, то не сохраняет в файл
        for (Contact contact : NoteBook.getContactList()) {
            printWriter.write(contact.getFullName() + ";" +
                    contact.getPhoneNumber() + ";" +
                    contact.getEmail() + "\n");
        }

        printWriter.flush();
        printWriter.close();

        System.out.println("Список контактов сохранен в файл notebook.txt");
    }
}
