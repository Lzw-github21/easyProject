package ecay.project.note.Stream流;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Integer age;
    private Integer sex;
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Person(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
