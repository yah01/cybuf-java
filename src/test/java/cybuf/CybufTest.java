package cybuf;

import object.Person;
import object.School;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CybufTest
{
    static Person person = new Person();
    static School school = new School();
    static ArrayList<Person> teachers = new ArrayList<>();
    static Person[] students = new Person[2];
    @BeforeClass
    public static void init()
    {
        person.setName("tcg");
        person.setAge(100);
        person.setHeight(173.5);
        person.setOk(true);

        school.setAge(100);
        school.setName("ECNU");
        school.setArea(1000.34);
        school.setHeader(person);
        school.setOpen(true);
        school.setNil(null);

        teachers.add(person);
        teachers.add(person);

        students[0] = person;
        students[1] = person;

        school.setStudents(students);
        school.setTeachers(teachers);
    }
    @Test
    public void serialize_cybufArray_to_cybuf_string()
    {
        CybufArray cybufArray = new CybufArray();
        cybufArray.add(person);
        cybufArray.add(person);

        String result = Cybuf.toCybufString(cybufArray);
        System.out.println(result);
    }

    @Test
    public void serialize_java_bean_to_cybuf_string()
    {
        String result = Cybuf.toCybufString(school);
        System.out.println(result);
    }

    @Test
    public void serialize_cybufObject_to_cybuf_string()
    {
        CybufObject cybufObject = new CybufObject();
        cybufObject.put("name","bqx");
        cybufObject.put("age",100);
        cybufObject.put("ok",true);

        //System.out.println(cybufObject.get("ok").getClass());
        String result = Cybuf.toCybufString(cybufObject);
        System.out.println(result);
    }

    @Test
    public void deserialize_invalid_char_in_key()
    {
        String text = new String("{\n" +
                "\tna me: \"mm\"\n" +
                "}");
        try
        {
            Cybuf.parseObject(text);
        }
        catch (CybufException e)
        {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(),"Invalid char in key");
        }
    }

    @Test
    public void deserialize_nil_value()
    {
        String text = new String("{\n" +
                "\tname: nil\n" +
                "}");
        CybufObject cybufObject = Cybuf.parseObject(text);
        assertNull(cybufObject.get("name"));
    }

    @Test
    public void deserialize_char_value()
    {
        String text = new String("{\n" +
                "\tname: 's'\n" +
                "}");
        assertEquals(Cybuf.parseObject(text).get("name"),'s');
    }
}