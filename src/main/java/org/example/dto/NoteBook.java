package org.example.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteBook {
    private static List<Contact> CONTACT_LIST = new ArrayList<>();

    public static List<Contact> getContactList() {
        return CONTACT_LIST;
    }

    public static void setContactList(List<Contact> contactList) {
        CONTACT_LIST = contactList;
    }
}
