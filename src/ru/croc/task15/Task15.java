package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task15 {

    public static void main(String[] args) throws IOException {

        String path = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task15/departments.txt";
        List<String> departmentsText = departmentsText(path);
        Map<String, Department> tree = new HashMap<>();


        // Создаем отделы из конфигурации
        for (String text : departmentsText) {
            String[] department = text.split(",");
            tree.put(department[0], new Department(department[0], tree.get(department[1]), Integer.parseInt(department[2])));
        }

        System.out.println("Необходимо времени: " + getHours(tree) + " часов");

    }

    /**
     * Вычислить время, необходимое для обработки заявки в заданной конфигурации
     * @param tree дерево конфигураций департаментов
     * @return время, необходимое для обработки заявки
     */
    public static int getHours(Map<String, Department> tree) {
        int hours = 0;
        int headDepartmentHours = 0;

        for (Map.Entry<String, Department> entry : tree.entrySet()) {

            Department nowDepartment = entry.getValue();
            int max = 0;

            /*
            Проходимся от конкретного отдела вверх по иерархии и узнаем
            сколько времени потратиться на этот путь.
            Так как обработка заявки всеми отделами это максимальное время,
            которое отделы затрат на ее обработку независимо друг от друга
             */
            while (nowDepartment.getParent() != null) {
                max += nowDepartment.getApplicationProcessingTime();
                nowDepartment = nowDepartment.getParent();
            }

            // Если время данного пути больше чем путей раньше присваиваем его переменной hours
            if (max > hours) {
                hours = max;
            }

            // Узнаем время выполнения главного отдела
            if (nowDepartment.getParent() == null) {
                headDepartmentHours = nowDepartment.getApplicationProcessingTime();
            }
        }
        return hours + headDepartmentHours; // так как в цикле while не учитывается главный отдел
    }

    /**
     * Получить List информацию об отделах
     * @param path путь к файлу
     * @return List с данными об отделах
     */
    private static List<String> departmentsText(String path) throws IOException {
        List<String> departments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String department;
            while ((department = reader.readLine()) != null) {
                departments.add(department);
            }
        }
        return departments;
    }

}
