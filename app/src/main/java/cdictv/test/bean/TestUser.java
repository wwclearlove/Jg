package cdictv.test.bean;

public class TestUser {
    private int shichang;
    private String time;
    private String name;
    public int getShichang() {
        return shichang;
    }

    public void setShichang(int shichang) {
        this.shichang = shichang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "shichang=" + shichang +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
