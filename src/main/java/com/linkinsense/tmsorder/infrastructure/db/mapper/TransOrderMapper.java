package com.linkinsense.tmsorder.infrastructure.db.mapper;

import com.linkinsense.tmsorder.infrastructure.db.dataobject.ClientOrderDO;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.TransOrderDO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TransOrderMapper {
    @Select("SELECT * FROM trans_order WHERE id = #{id}")
    TransOrderDO select(@Param("id") Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO trans_order(code,driver_id, status) " +
            "VALUES(#{code},#{driverId},#{status})")
    void insert(TransOrderDO transOrderDO);
}
