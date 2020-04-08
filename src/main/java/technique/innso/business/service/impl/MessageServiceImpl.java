package technique.innso.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technique.innso.business.domain.Message;
import technique.innso.business.mapper.MessageMapper;
import technique.innso.business.service.MessageService;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.repository.MessageRepository;

import java.time.ZonedDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageMapper mapper;

    @Override
    public Long createMessage(Message messageToCreate) {

        messageToCreate.setDate(ZonedDateTime.now());
        return repository.save(mapper.toEntity(messageToCreate)).getIdMessage();
    }

    @Override
    public Message getMessageById(Long idMessage) {
        return mapper.toDomain(repository.findById(idMessage).get());
    }

    @Override
    public Message getMessageCustomerById(Long idCustomer, Long idMessage) {
        CustomerEntity customer = new CustomerEntity();
        customer.setIdCustomer(idCustomer);
        return mapper.toDomain(repository.findByIdMessageAndCustomer(idMessage, customer));
    }
}
