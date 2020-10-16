package cybuf;

import com.alibaba.fastjson.JSON;
import cybuf.serializer.SerializerConfig;
import object.ArrayListTest;
import object.ArrayTest;
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
        String result = Cybuf.toCybufString(school,false,true, SerializerConfig.NEWLINE);
        System.out.println(result);

        School s1 = Cybuf.parseObject(result,School.class);
        System.out.println(s1);
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
    public void serialize_char()
    {
        CybufObject cybufObject = new CybufObject();
        cybufObject.put("name",'s');
        //System.out.println(cybufObject.get("name").getClass());
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

    @Test
    public void deserialize_array_object_to_cybufArray()
    {
        String text = new String("[[nil 2 3][1 2 3]]");
        CybufArray array = Cybuf.parseArray(text);
        System.out.println(array);
    }

    @Test
    public void type_test()
    {
        char s = 's';

        System.out.println(Character.TYPE);
    }

    @Test
    public void deserialize_to_javabean()
    {
        String text = new String("{\n" +
                "\tname: \"tcg\"\n" +
                "\tage: 10\n" +
                "\tok: true\n" +
                "\theight: 180\n" +
                "}");
        Person person = Cybuf.parseObject(text,Person.class);
        System.out.println(person);
    }

    @Test
    public void deserialize_to_array()
    {
        ArrayTest arrayTest = new ArrayTest();
        Integer[][] a = new Integer[4][4];
        for(int i=0;i<a.length;++i)
        {
            for(int j=0;j<a[i].length;++j)
            {
                a[i][j] = i;
            }

        }
        arrayTest.setA(a);
        String result = Cybuf.toCybufString(arrayTest,true,true,SerializerConfig.SPACE);
        System.out.println(result);

        arrayTest = Cybuf.parseObject(result,ArrayTest.class);
        System.out.println(arrayTest);
    }
    @Test
    public void deserialize_to_arraylist()
    {
        ArrayListTest arrayListTest = new ArrayListTest();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayListTest.setA (arrayList);

        String result = Cybuf.toCybufString(arrayListTest);
        System.out.println(result);

        ArrayListTest a = Cybuf.parseObject(result,ArrayListTest.class);
        System.out.println(a);
    }

    @Test
    public void JSON_test()
    {
//        ArrayListTest arrayListTest = new ArrayListTest();
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayListTest.a = (arrayList);
//        String result = JSON.toJSONString(arrayListTest);
//        System.out.println(result);

        String result = "{\"a\":[1,2]}";
        ArrayListTest school1 = JSON.parseObject(result,ArrayListTest.class);
        System.out.println(school1);
    }
}