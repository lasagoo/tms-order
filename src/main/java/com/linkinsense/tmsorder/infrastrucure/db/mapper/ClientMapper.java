package com.linkinsense.tmsorder.infrastrucure.db.mapper;

import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientDO select(@Param("id") Long id);
    List<ClientDO> selectAll();
    void save(ClientDO clientDO);
    void remove(@Param("id") Long id);
}
