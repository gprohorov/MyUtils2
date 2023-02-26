/*
  @author   george
  @project   MyUtils2
  @class  MyUtils
  @version  1.0.0 
  @since 22.02.23 - 21.40
*/


import java.math.BigDecimal;
import java.util.*;

import java.util.stream.Collectors;

abstract class Shape{
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public Shape() {
    }

    public String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public abstract double getArea();
}
class Circle extends Shape{
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        if (student.getName() == null && student.getId() == getId()) return true;
        return getId() == student.getId() && getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        if (name == null) {
            return getId();
        }
        return getId() +  getName().hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}



class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public BigDecimal getBasePayment() {
        return basePayment;
    }

    public BigDecimal getPayment() {
        return this.getBasePayment();
    }

    public void setBasePayment(BigDecimal basePayment) {
        this.basePayment = basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getExperience() == employee.getExperience() &&
                getName().equals(employee.getName()) &&
                getBasePayment().equals(employee.getBasePayment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExperience(), getBasePayment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", basePayment=" + basePayment +
               " Payment=" + getPayment() +
                '}';
    }
}

class Manager extends Employee {

    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        BigDecimal basePayment = getBasePayment();
        return basePayment.multiply(BigDecimal.valueOf(this.getCoefficient()));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        Manager manager  = (Manager) o;
        return getExperience() == manager.getExperience() &&
                getName().equals(manager.getName()) &&
                getPayment().equals(manager.getPayment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExperience(), getPayment());
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + super.getName() + '\'' +
                ", experience=" + super.getExperience() +
                ", basePayment=" + super.getBasePayment() +
                ", coefficient=" + coefficient +
                ", payment=" + getPayment() +

                '}';
    }
}

class MyUtils {

    public static List<Shape> maxAreas(List<Shape> shapes) {
        return shapes.stream()
                .sorted(Comparator.comparing(Shape::getArea, Comparator.reverseOrder()))
                .limit(3).collect(Collectors.toList());
    }

    public static Map<String, List<String>> createNotebook(Map<String, String> phones) {

        Map<String, List<String>> map = new HashMap<>();
        List<String> names = phones.values().stream().distinct().collect(Collectors.toList());
        for (String name:names){
            String key = name;
            List<String> value = new ArrayList<>();
            for (Map.Entry<String, String> entry:phones.entrySet()) {
                if ( entry.getValue() == null || entry.getValue().equals(name)){
                    value.add(entry.getKey());
                }
            }
            map.put(key, value);
        }

        return map;
    }

    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        Set<Student> set = new HashSet<>();
        for (Student student:list1) {
            if (list2.contains(student)) {
                set.add(student);
            }
        }
        return set;
    }


    public static List<Employee> largestEmployees(List<Employee> workers) {
        return workers.stream()
                .filter(employee -> employee != null)
                .distinct()
                .sorted(Comparator.comparing(Employee::getExperience, Comparator.reverseOrder())
             .thenComparing(Employee::getPayment, Comparator.reverseOrder()))
                 .limit(3)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        List<Employee> workers = List.of(
                new Employee("Ivan", 10, new BigDecimal(3000.00)),
                new Employee("Stepan", 8, new BigDecimal(3900.00)),
                new Employee("Andriy", 7, new BigDecimal(3500.00)),
                new Manager("Petro", 9, new BigDecimal(3000.00), 1.5),
                new Manager("Vasyl", 8, new BigDecimal(2000.00), 2.0),
                new Employee("Ihor", 5, new BigDecimal(4500.00))
        );
        largestEmployees(workers).forEach(System.out::println);

        /*
         *  [Employee [name=Ivan, experience=10, basePayment=3000.00], //
         * Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],//
         * Employee [name=Stepan, experience=8, basePayment=4000.00],
         * Employee [name=Andriy, experience=7, basePayment=3500.00],
         * Employee [name=Ihor, experience=5, basePayment=4500.00],//
         * Manager [name=Vasyl, experience=8, basePayment=2000.00, coefficient=2.0]]
         *
         *
         * */



        List<Shape> list = List.of(new Circle(2.0)
                // new Rectangle(2.0, 3.0),
         //     new Circle(1.0), new Rectangle(3.0, 2.0),
        //     new Circle(0.5), new Rectangle(1.0, 2.0)
        );

  //  System.out.println(maxAreas(list));

        Map<String, String> phones = new HashMap<>();
        phones.put("123", "John");
        phones.put("345", "Paul");
        phones.put("678", "Ringo");
        phones.put("900", "John");
        phones.put("911", "Brian");
        phones.put("912", "Paul");
        phones.put("915", "");
        phones.put("913", null);
   //     phones.put(null, null);
   //     phones.put(null, "John");
      //  phones.put("314", "");


 //   System.out.println(createNotebook(phones));




    }



}
