package cdictv.test.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "CzliBeen")
public class CzliBeen {
    @DatabaseField(generatedId =true,columnName = "bh", dataType = DataType.INTEGER, canBeNull = false)
    private int bh;
    @DatabaseField(columnName = "je")
    private String je;
    @DatabaseField(columnName = "ye")
    private String ye;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "date")
    private String date;
    public  CzliBeen(){

    }
    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getYe() {
        return ye;
    }

    public void setYe(String ye) {
        this.ye = ye;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CzliBeen{" +
                "bh=" + bh +
                ", je='" + je + '\'' +
                ", ye='" + ye + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
