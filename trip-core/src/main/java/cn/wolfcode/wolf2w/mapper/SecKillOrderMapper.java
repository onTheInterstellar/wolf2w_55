package cn.wolfcode.wolf2w.mapper;


import cn.wolfcode.wolf2w.domain.SecKillOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* 商品持久层接口
*/
public interface SecKillOrderMapper extends BaseMapper<SecKillOrder>{


    @Select("SELECT  good_id, good_img, good_name,  good_price, seckill_price\n" +
            "FROM t_goods t1, t_seckill_goods t2\n" +
            "where t1.id = t2.good_id\n" +
            "and good_id = #{goodId}")
    SecKillOrder getGoodInfo(Long goodId);


    @Select("select stock_count from t_seckill_goods where good_id = #{goodId}")
    int getCount(Long goodId);


    @Select("update t_goods set good_stock = (good_stock -1) where id = #{goodId}")
    void reduceOrder(Long goodId);

    @Select("update t_seckill_goods set stock_count = (stock_count -1) where good_id = #{goodId}")
    void reduceSecKillOrder(Long goodId);

}
