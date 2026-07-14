

import java.util.HashMap;

public class GradeTracker {
    public static void main(String[] args) {

    HashMap<String, Integer> grades = new HashMap<>();

    grades.put("Kenny", 50);
    grades.put("Carls", 80);
    grades.put("Kristie", 92);

    System.out.println(grades);

    System.out.println("Kristie's grade: " + grades.get("Kristie"));


    grades.put("Carls", 21);
    System.out.println("Carls's grade: " + grades.get("Carls"));

    if(grades.containsKey("Carls")){
    System.out.println("Carls is a student in this class");
    } else {
        System.out.println("Carls is not a student in this class");
    }

    grades.remove("Carls");

    System.out.println(grades);
}
    
}
