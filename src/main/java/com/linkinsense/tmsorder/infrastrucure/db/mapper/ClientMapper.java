package com.linkinsense.tmsorder.infrastrucure.db.mapper;

import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientMapper {

    @Select("SELECT * FROM clients WHERE id = #{id}")
    ClientDO select(@Param("id") Long id);

    @Select("SELECT * FROM clients")
    List<ClientDO> selectAll();

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO clients(code,name,client_id,province,city,county,street,longitude,latitude) " +
            "VALUES(#{code},#{name},#{clientId},#{province},#{city},#{county},#{street},#{longitude},#{latitude})")
    void insert(ClientDO clientDO);

    @Delete("DELETE FROM clients WHERE id=#{id}")
    void delete(@Param("id") Long id);
}
