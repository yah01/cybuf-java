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
                "}\n\t" +
                "skill:[\n\t\t" +
                "\"C\"\n\t\t" +
                "\"java\"\n\t\t" +
                "]\n" +
                "\tok:true\n" +
                "}");
        String text1 = new String("[\n" +
                "\t{\n" +
                "\t\tname:\"tcg\"\n" +
                "\t\tage:21\n" +
                "\t}\n" +
                "\t{\n" +
                "\t\tname:\"bqx\"\n" +
                "\t\tage:100\n" +
                "\t}\n" +
                "\t\"hahah\"\n" +
                "]");

        System.out.println(text1);

        CybufObject object = Cybuf.parseObject(text);

        CybufArray array = Cybuf.parseArray(text1);

        System.out.println(array);

        System.out.println(text);

        System.out.println(object);
    }
}
