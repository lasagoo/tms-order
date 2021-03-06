package com.linkinsense.tmsorder.infrastructure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.infrastructure.db.converter.ClientConverter;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.ClientDO;
import com.linkinsense.tmsorder.infrastructure.db.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    public Client find(Long id) {
        ClientDO clientDO =clientMapper.select(id);
        Client client = clientConverter.deserialize(clientDO);
        return client;
    }

    @Override
    public void save(Client client) {

    }



    @Override
    public void remove(Long id) {

    }
}
