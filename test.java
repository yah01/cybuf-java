public class test
{
    public static void main(String[] args)
    {
        String text = new String("{\n\t" +
                "name : \"tcg\"\n\t" +
                "age:21.9\n\t" +
                "obj:{\n\t\t" +
                "name:\"bqx\"\n\t\t" +
                "age:21\n\t\t" +
                "}\n" +
                "}");
        System.out.println(text);

        CybufObject object = Cybuf.parseObject(text);

        System.out.println(object);
    }
}
