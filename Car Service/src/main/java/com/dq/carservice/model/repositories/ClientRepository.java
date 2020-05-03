package com.dq.carservice.model.repositories;

import com.dq.carservice.model.EntityRepository;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.persistence.QuerySpec;
import com.dq.carservice.model.persistence.SearchCondition;

import java.util.List;

public class ClientRepository extends EntityRepository<Client> {

    public ClientRepository(PersistenceManager pm) {
        super(pm);
    }

    public List<Client> findAll() {
        return read(new QuerySpec(Client.class));
    }

    public Client findOrCreateNew(String firstName, String lastName, String contact) {
        Client client = readUnique(new QuerySpec(
                Client.class,
                new SearchCondition("getFirstName", firstName),
                new SearchCondition("getLastName", lastName),
                new SearchCondition("getContact", contact)
        ));

        return client != null ? client : create(new Client(firstName, lastName, contact));
    }

    public Client findByCredentials(String firstName, String lastName, String contact) {
        return readUnique(new QuerySpec(
                Client.class,
                new SearchCondition("getFirstName", firstName),
                new SearchCondition("getLastName", lastName),
                new SearchCondition("getContact", contact)
        ));
    }
}
