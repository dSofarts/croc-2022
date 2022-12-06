package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentTree {

    private Map<String, Department> departmentTree;
    private int hours;

    public DepartmentTree(Map<String, Department> departmentTree) {
        this.departmentTree = departmentTree;
        hours = 0;
    }

    public static DepartmentTree createDepartmentTreeFromFile(String filePath) throws IOException {
        Map<String, Department> tree = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String department;
            while ((department = reader.readLine()) != null) {
                String[] departmentArray = department.split(",");
                tree.put(departmentArray[0],
                        new Department(departmentArray[0], tree.get(departmentArray[1]),
                                Integer.parseInt(departmentArray[2])));
                if (!departmentArray[1].equals("-")) {
                    tree.get(departmentArray[1]).addDaughter(tree.get(departmentArray[0]));
                }
            }
        }
        return new DepartmentTree(tree);
    }

    public int getTimeToCompleteApplication() {
        Department firstDepartment = null;
        for (Map.Entry<String, Department> entry : departmentTree.entrySet()) {
            Department nowDepartment = entry.getValue();
            if (nowDepartment.getParent() == null) {
                firstDepartment = nowDepartment;
                break;
            }
        }
        return getTimeFromDepartment(firstDepartment);
    }

    private int getTimeFromDepartment(Department department) {
        int x = 0;
        x += department.getApplicationProcessingTime();
        if (department.haveDaughter()) {
            List<Integer> times = new ArrayList<>();
            for (Department department1 : department.getDaughters()) {
                times.add(getTimeFromDepartment(department1));
            }
            x += Collections.max(times);
        }
        return x;
    }
}