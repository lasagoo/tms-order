package com.linkinsense.tmsorder.infrastrucure.db.mapper;

import com.linkinsense.tmsorder.infrastrucure.db.dataobject.LocationDO;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.TransTaskDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LocationMapper {

    @Select("SELECT * FROM location WHERE id=#{id}")
    LocationDO select(@Param("id") Long id);

    @Select("SELECT * FROM location")
    List<LocationDO> selectAll();

    @Insert("INSERT INTO location location(code,name,client_id,province,city,county,street,longitude,latitude) " +
            "VALUES(#{code},#{name},#{clientId},#{province},#{city},#{county},#{street},#{longitude},#{latitude})")
    void insert(LocationDO locationDO);

    @Delete("DELETE FROM location WHERE id = #{id}")
    void delete(@Param("id") Long id);

}
