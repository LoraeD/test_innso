package technique.innso.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import technique.innso.business.domain.Customer;
import technique.innso.business.domain.Message;
import technique.innso.business.mapper.CustomerMapper;
import technique.innso.business.service.CustomerService;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.entity.MessageEntity;
import technique.innso.dao.repository.CustomerRepository;
import technique.innso.dao.repository.MessageRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomerMapper mapper;

    public Long createCustomer(Customer customer) {
        if (customer != null) {
            CustomerEntity entity = mapper.toEntity(customer);

            entity.setOpeningDate(ZonedDateTime.now());
            CustomerEntity entitySaved = repository.save(entity);
            saveMessage(customer.getMessages(), entitySaved);

            return entitySaved.getIdCustomer();
        }
        return null;
    }

    private void saveMessage(List<Message> messages, CustomerEntity entitySaved) {
        if (!CollectionUtils.isEmpty(messages)) {
            List<MessageEntity> messageEntities = messages
                    .stream()
                    .map(message -> getMessageById(message.getIdMessage(), entitySaved))
                    .filter(messageEntity -> Objects.nonNull(messageEntity))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(messageEntities)) {
                messageRepository.saveAll(messageEntities);
            }
        }
    }

    private MessageEntity getMessageById(Long idMessage, CustomerEntity entitySaved) {
        Optional<MessageEntity> optionalMessage = messageRepository.findById(idMessage);
        if (optionalMessage.isPresent()) {
            MessageEntity messageEntity = optionalMessage.get();
            messageEntity.setCustomer(entitySaved);
            return messageEntity;
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customerToUpdate) {
        if (customerToUpdate != null) {
            Optional<CustomerEntity> optional = repository.findById(customerToUpdate.getIdCustomer());
            if (optional.isPresent()) {
                CustomerEntity entity = optional.get();
                if (StringUtils.isNotEmpty(customerToUpdate.getName())) {
                    entity.setName(customerToUpdate.getName());
                }
                if (StringUtils.isNotEmpty(customerToUpdate.getReference())) {
                    entity.setReference(customerToUpdate.getReference());
                }
                repository.save(entity);
            }
        }

    }

    @Override
    public List<Customer> getAllCustomers() {
        return mapper.toDomains((List) repository.findAll());
    }

    @Override
    public Customer getCustomer(Long idCustomer) {
        if (idCustomer != null) {
            return mapper.toDomain(repository.findById(idCustomer).get());
        }
        return null;
    }
}
