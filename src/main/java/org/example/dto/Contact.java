package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Getter
@Setter
@Accessors(chain = true)
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return fullName + " | " + phoneNumber + " | " + email;
    }


//
//    @SneakyThrows
//    public void saveToFile() {
//        PrintWriter printWriter = new PrintWriter("src/main/resources/contact.txt");
//        for (Contact contact : contactList) {
//            printWriter.write(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail() + "\n");
//        }
//        printWriter.flush();
//        printWriter.close();
//        System.out.println("Список контактов сохранен в файл contact.txt");
//    }
}
