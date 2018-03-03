package samir.andrew.checknmaes.models;

/**
 * Created by andre on 03-Mar-18.
 */

public class ModelData
{
    public String name, absent, comeToBus, keyString;

    public ModelData()
    {
    }

    public ModelData(String name)
    {
        this.name = name;
        absent = "no";
        comeToBus = "no";
        keyString = "key";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbsent()
    {
        return absent;
    }

    public void setAbsent(String absent)
    {
        this.absent = absent;
    }

    public String getComeToBus()
    {
        return comeToBus;
    }

    public void setComeToBus(String comeToBus)
    {
        this.comeToBus = comeToBus;
    }

    public String getKeyString()
    {
        return keyString;
    }

    public void setKeyString(String keyString)
    {
        this.keyString = keyString;
    }
}
