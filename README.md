# cybuf-java
a java package for marshal &amp; unmarshal cybuf format data

### usage

* cybuf.toCybufString(Object)
  * input:a java object ;
  * output:a cybuf format String;
* cybuf.parseObject(String)
  * input:a cybuf format String;
  * output:a cybufObject;
* cybuf.parseObject(String,javaBean.class)
  * input:a cybuf format String ,object class type;
  * output:a javaBean instance;
  * <font color='red'>ps:your class must implement setters and getters;</font>

### example

```java
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
    /*
    setters and getters
    */
}
```

```java
String result = Cybuf.toCybufString(school,false,true, SerializerConfig.NEWLINE);
System.out.println(result);
//you will get result
/*
    {
        area: 1000.34
        nil: nil
        teachers: [
            {
                name: "tcg"
                ok: true
                age: 100
                height: 173.5
            }
            {
                name: "tcg"
                ok: true
                age: 100
                height: 173.5
            }
        ]
        name: "ECNU"
        header: {
            name: "tcg"
            ok: true
            age: 100
            height: 173.5
        }
        students: [
            {
                name: "tcg"
                ok: true
                age: 100
                height: 173.5
            }
            {
                name: "tcg"
                ok: true
                age: 100
                height: 173.5
            }
        ]
        age: 100
        open: true
    }
*/
```

```java
School s1 = Cybuf.parseObject(result,School.class);
//you will get a School instance s1
```

