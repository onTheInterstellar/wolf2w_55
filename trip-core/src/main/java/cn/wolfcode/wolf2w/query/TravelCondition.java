package cn.wolfcode.wolf2w.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class TravelCondition {

    public static final Map<Integer, TravelCondition> DAY_MAP = new HashMap();
    public static final Map<Integer, TravelCondition> CONSUME_MAP = new HashMap();
    public static final Map<Integer, TravelCondition> TRAVEL_TIME_MAP = new HashMap();

    static {
        DAY_MAP.put(1, new TravelCondition(0,3));
        DAY_MAP.put(2, new TravelCondition(4,7));
        DAY_MAP.put(3, new TravelCondition(8,14));
        DAY_MAP.put(4, new TravelCondition(15,Integer.MAX_VALUE));

        CONSUME_MAP.put(1, new TravelCondition(1,999));
        CONSUME_MAP.put(2, new TravelCondition(1000,6000));
        CONSUME_MAP.put(3, new TravelCondition(6000,20000));
        CONSUME_MAP.put(4, new TravelCondition(20000,Integer.MAX_VALUE));

        TRAVEL_TIME_MAP.put(1, new TravelCondition(1,2));
        TRAVEL_TIME_MAP.put(2, new TravelCondition(3,4));
        TRAVEL_TIME_MAP.put(3, new TravelCondition(5,6));
        TRAVEL_TIME_MAP.put(4, new TravelCondition(7,8));
        TRAVEL_TIME_MAP.put(5, new TravelCondition(9,10));
        TRAVEL_TIME_MAP.put(6, new TravelCondition(11,12));

    }

    private Integer min;
    private Integer max;

    public TravelCondition(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }
}
