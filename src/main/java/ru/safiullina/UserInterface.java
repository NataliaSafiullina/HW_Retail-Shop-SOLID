package ru.safiullina;

import java.util.List;

public interface UserInterface <T> {

    void showItems(List<T> list, String listTitle);
    int enterInt(String message);

}
