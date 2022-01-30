package com.linkinsense.tmsorder.domain.aggregate.client;

public interface ClientRepository {
    Client find(Long id);
    void save(Client client);
    void remove(Long id);
}
