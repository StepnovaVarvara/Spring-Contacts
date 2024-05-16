package org.example.env;

import lombok.SneakyThrows;
import org.example.dto.Contact;
import org.example.dto.NoteBook;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InitEnvWorker implements EnvWorker {
    @Value("${app.filepath}")
    private String filePath;
    @Value("${app.env}")
    private String env;

    @SneakyThrows
    @Override
    public void initContactList() {
        File file = new File(filePath);

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
}
