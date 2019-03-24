package cdictv.test.bean;

public class LukuangBean {

    /**
     * data : {"getsense":{"temperature":30,"humidity":48,"co2":186,"LightIntensity":734,"pm2_5":142,"address":3,"address1":1,"address2":4}}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * getsense : {"temperature":30,"humidity":48,"co2":186,"LightIntensity":734,"pm2_5":142,"address":3,"address1":1,"address2":4}
         */

        public GetsenseBean getsense;

        public static class GetsenseBean {
            /**
             * temperature : 30
             * humidity : 48
             * co2 : 186
             * LightIntensity : 734
             * pm2_5 : 142
             * address : 3
             * address1 : 1
             * address2 : 4
             */

            public int temperature;
            public int humidity;
            public int co2;
            public int LightIntensity;
            public int pm2_5;
            public int address;
            public int address1;
            public int address2;
        }
    }
}
