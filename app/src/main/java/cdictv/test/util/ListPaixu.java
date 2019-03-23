package cdictv.test.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.test.bean.LudengBeen;

public class ListPaixu {
    public static void luKouS(int i,List<LudengBeen.DataBean> list){
        Collections.sort(list, new Comparator<LudengBeen.DataBean>() {
            @Override
            public int compare(LudengBeen.DataBean o1, LudengBeen.DataBean o2) {
                switch (i){
                    case 1:
                        return o1.id-o2.id;
                    case 2:
                        return -(o1.id-o2.id);
                    case 3:
                        return (o1.red-o2.red);
                    case 4:
                        return -(o1.red-o2.red);
                    case 7:
                        return (o1.yellow-o2.yellow);
                    case 8:
                        return -(o1.yellow-o2.yellow);
                    case 5:
                        return (o1.green-o2.green);
                    case 6:
                        return -(o1.green-o2.green);
                }
            return 0;
            }
        });
    }
//wwwwwwww
}
