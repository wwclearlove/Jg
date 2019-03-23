package cdictv.test.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ClczBeen")
public class ClczBeen  {
    @DatabaseField(generatedId =true,columnName = "bh", dataType = DataType.INTEGER, canBeNull = false)
    private  int bh;
    @DatabaseField(columnName = "ye")
    private  int ye;
    @DatabaseField(columnName = "xz")
    private boolean xz;
    public int getBh() {
        return bh;
    }
    public  ClczBeen(){

    }
    public void setBh(int bh) {
        this.bh = bh;
    }

    public int getYe() {
        return ye;
    }

    public void setYe(int ye) {
        this.ye = ye;
    }

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }


    @Override
    public String toString() {
        return "ClczBeen{" +
                "bh=" + bh +
                ", ye=" + ye +
                ", xz=" + xz +
                '}';
    }
}
