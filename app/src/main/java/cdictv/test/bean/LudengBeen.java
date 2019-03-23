package cdictv.test.bean;

import java.util.List;

public class LudengBeen {
    /**
     * code : 1
     * data : [{"id":1,"red":13,"green":16,"yellow":11},{"id":2,"red":8,"green":6,"yellow":10},{"id":3,"red":11,"green":4,"yellow":7},{"id":4,"red":15,"green":4,"yellow":20},{"id":5,"red":3,"green":12,"yellow":8}]
     */

    public int code;
    public List<DataBean> data;

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", red=" + red +
                    ", green=" + green +
                    ", yellow=" + yellow +
                    '}';
        }

        /**
         * id : 1
         * red : 13
         * green : 16
         * yellow : 11
         */

        public int id;
        public int red;
        public int green;
        public int yellow;
    }
}
