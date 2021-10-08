package cn.wolfcode.wolf2w.mapper;


import cn.wolfcode.wolf2w.domain.Good;
import cn.wolfcode.wolf2w.domain.SecKillGood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 商品持久层接口
*/
public interface GoodMapper extends BaseMapper<Good>{

    @Select("SELECT t2.id, good_id, seckill_price, stock_count, start_date, end_date, good_price, good_name, good_img, good_detail\n" +
            "FROM t_goods t1, t_seckill_goods t2\n" +
            "where t1.id = t2.good_id\n")
    List<SecKillGood> getSecKillGoods();


    @Select("SELECT t2.id, good_id, seckill_price, stock_count, start_date, end_date, good_price, good_name, good_img, good_detail\n" +
            "FROM t_goods t1, t_seckill_goods t2\n" +
            "where t1.id = t2.good_id\n" +
            "and good_id = #{id}")
    List<SecKillGood> getSecKillDetail(Long id);
}
