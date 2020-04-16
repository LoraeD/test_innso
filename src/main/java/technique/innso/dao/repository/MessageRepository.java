package technique.innso.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.entity.MessageEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

    MessageEntity findByIdMessageAndCustomer(Long idMessage, CustomerEntity customer);
}
