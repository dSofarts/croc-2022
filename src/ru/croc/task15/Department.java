package ru.croc.task15;

public class Department {

    private String name;
    private Department parent = null;
    private int applicationProcessingTime;

    public Department(String name, Department parent, int applicationProcessingTime) {
        this.name = name;
        this.applicationProcessingTime = applicationProcessingTime;
        if (parent != null) {
            this.parent = parent;
        }
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
}
