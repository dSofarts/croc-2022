package ru.croc.task15;

import java.util.HashSet;
import java.util.Set;

public class Department {

    private String name;
    private Department parent = null;
    private int applicationProcessingTime;

    private Set<Department> daughters;

    public Department(String name, Department parent, int applicationProcessingTime) {
        this.name = name;
        this.applicationProcessingTime = applicationProcessingTime;
        if (parent != null) {
            this.parent = parent;
        }
        daughters = new HashSet<>();
    }

    /**
     * Получить имя отдела
     * @return имя отдела
     */
    public String getName() {
        return name;
    }

    /**
     * Получить родительский отдел
     * @return родительский отдел
     */
    public Department getParent() {
        return parent;
    }

    /**
     * Получить количество часов, которые отдел затрачивает на обработку заявки
     * @return количество часов, которые отдел затрачивает на обработку заявки
     */
    public int getApplicationProcessingTime() {
        return applicationProcessingTime;
    }

    public void addDaughter(Department department) {
        daughters.add(department);
    }

    public boolean haveDaughter() {
        return !daughters.isEmpty();
    }

    public Set<Department> getDaughters() {
        return daughters;
    }
}
