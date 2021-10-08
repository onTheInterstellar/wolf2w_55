package cn.wolfcode.wolf2w.util;

import java.util.HashMap;

public class ParamsMapUtil extends HashMap<String, Object> {

    @Override
    public ParamsMapUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public static ParamsMapUtil newInstance() {
        return new ParamsMapUtil();
    }
}
