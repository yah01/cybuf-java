package object;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTest
{
    Integer[][] a;

    public Integer[][] getA()
    {
        return a;
    }

    public void setA(Integer[][] a)
    {
        this.a = a;
    }

    public String toString()
    {
        String ret = new String();
        ret += "[";
        for(int i=0;i<a.length;++i)
        {
            ret += "[";
            for(int j=0;j<a[i].length;++j)
            {
                ret += a[i][j] + " ";
            }
            ret += "]";
        }
        ret += "]";
        return "{a: " + ret + "}";
    }
}
