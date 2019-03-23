package cdictv.test.bean;

import java.util.List;

public class GongjiaoBean {

    /**
     * code : 1
     * data : [{"id":1,"zhantai":"一号站台","bus":[{"name":"1号公交车","distance":624},{"name":"2号公交车","distance":1303},{"name":"3号公交车","distance":323}]},{"id":2,"zhantai":"二号站台","bus":[{"name":"1号公交车","distance":596},{"name":"2号公交车","distance":1170},{"name":"3号公交车","distance":850}]},{"id":3,"zhantai":"三号站台","bus":[{"name":"1号公交车","distance":610},{"name":"2号公交车","distance":1358},{"name":"3号公交车","distance":878}]}]
     */

    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * zhantai : 一号站台
         * bus : [{"name":"1号公交车","distance":624},{"name":"2号公交车","distance":1303},{"name":"3号公交车","distance":323}]
         */

        public int id;
        public String zhantai;
        public List<BusBean> bus;

        public static class BusBean {
            /**
             * name : 1号公交车
             * distance : 624
             */

            public String name;
            public int distance;
        }
    }
}
