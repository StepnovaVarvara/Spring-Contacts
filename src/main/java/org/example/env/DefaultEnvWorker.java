package org.example.env;

import org.example.dto.NoteBook;

import java.util.ArrayList;

public class DefaultEnvWorker implements EnvWorker {

    @Override
    public void initContactList() {
        NoteBook.setContactList(new ArrayList<>());
    }
}
