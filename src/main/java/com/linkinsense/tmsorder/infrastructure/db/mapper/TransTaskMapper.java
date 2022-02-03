package com.linkinsense.tmsorder.infrastructure.db.mapper;

import com.linkinsense.tmsorder.infrastructure.db.dataobject.TransTaskDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransTaskMapper {

    @Select("SELECT * FROM trans_task where id = #{id}")
    TransTaskDO select(@Param("id") Long id);

    @Select("SELECT * FROM trans_task")
    List<TransTaskDO> selectAll();

    @Select("SELECT * FROM trans_task WHERE order_id = #{orderId}")
    List<TransTaskDO> selectByOrderId(@Param("orderId") Long orderId);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO trans_task(order_id,loading_location_id,unloading_location_id,loading_date,arrive_date,status " +
            "VALUES(#{orderId},#{loadingLocationId},#{unloadingLocationId},#{loadingDate},#{arriveDate},#{status})")
    void insert(TransTaskDO transTaskDO);

    @Delete("DELETE FROM trans_task WHERE id=#{id}")
    void delete(@Param("id") Long id);
}
