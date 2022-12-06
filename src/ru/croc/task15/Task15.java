package ru.croc.task15;

import java.io.IOException;

public class Task15 {

    public static void main(String[] args) throws IOException {

        String filePath = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task15/departments.txt";
        DepartmentTree departmentTree = DepartmentTree.createDepartmentTreeFromFile(filePath);

        System.out.println(departmentTree.getTimeToCompleteApplication());

    }
}
