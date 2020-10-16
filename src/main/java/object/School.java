package object;

import java.util.ArrayList;
import java.util.Arrays;

public class School
{
    String name;
    Integer age;
    Double area;
    Boolean open;
    ArrayList<Person> teachers;
    Person[] students;
    Person header;
    Object nil;

    public String toString()
    {
        return "{" +
                "name: " + name +
                ",age: " + age +
                ",area: " + area +
                ",open: " + open +
                ",teachers: " + teachers +
                ",students: " + Arrays.asList(students) +
                ",header: " + header +
                ",nil: nil" +
                "}";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Double getArea()
    {
        return area;
    }

    public void setArea(Double area)
    {
        this.area = area;
    }

    public Boolean getOpen()
    {
        return open;
    }

    public void setOpen(Boolean open)
    {
        this.open = open;
    }

    public ArrayList<Person> getTeachers()
    {
        return teachers;
    }

    public void setTeachers(ArrayList<Person> teachers)
    {
        this.teachers = teachers;
    }

    public Person[] getStudents()
    {
        return students;
    }

    public void setStudents(Person[] students)
    {
        this.students = students;
    }

    public Person getHeader()
    {
        return header;
    }

    public void setHeader(Person header)
    {
        this.header = header;
    }

    public Object getNil()
    {
        return nil;
    }

    public void setNil(Object nil)
    {
        this.nil = nil;
    }
}
