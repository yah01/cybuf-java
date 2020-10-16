package object;

public class Person
{
    String name;
    Integer age;
    Boolean ok;
    Double height;

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

    public Boolean getOk()
    {
        return ok;
    }

    public void setOk(Boolean ok)
    {
        this.ok = ok;
    }

    public Double getHeight()
    {
        return height;
    }

    public void setHeight(Double height)
    {
        this.height = height;
    }

    public String toString()
    {
        return "{name: " + name + ",age: " + age + ",ok: " + ok + ",height: " + height + "}";
    }
}
