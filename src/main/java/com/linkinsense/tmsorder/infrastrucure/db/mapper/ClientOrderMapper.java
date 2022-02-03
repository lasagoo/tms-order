package com.linkinsense.tmsorder.infrastrucure.db.mapper;

import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientOrderDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientOrderMapper {

    @Select("SELECT * FROM clien_order WHERE id = #{id}")
    ClientOrderDO select(@Param("id") Long id);

    @Select("SELECT * FROM client_order")
    List<ClientOrderDO> selectAll();

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO client_order(order_code,order_dir, client_id,status) " +
            "VALUES(#{orderCode},#{orderDir},#{clientId},#{status})")
    void insert(ClientOrderDO clientOrderDO);

    @Update("UPDATE client_order SET order_code=#{order_code},order_dir=#{orderDir},client_id=#{clientId},status=#{status}")
    void update(ClientOrderDO clientOrderDO);

    @Delete("DELETE FROM client_order WHERE id=#{id}")
    void delete(@Param("id") Long id);

}
