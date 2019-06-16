package org.dmnl.dmsngr.repositories;

import java.util.List;
import org.jvnet.hk2.annotations.Contract;
import org.dmnl.dmsngr.models.Message;

@Contract
public interface MessageDao {

    public List<Message> findAll();

    public Message findOne(Long id);

    public void save(Message message);

    public void delete(Long id);

}
