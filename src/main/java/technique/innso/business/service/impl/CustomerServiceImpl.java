package technique.innso.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import technique.innso.business.domain.Customer;
import technique.innso.business.mapper.CustomerMapper;
import technique.innso.business.service.CustomerService;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.entity.MessageEntity;
import technique.innso.dao.repository.CustomerRepository;
import technique.innso.dao.repository.MessageRepository;

import java.time.ZonedDateTime;
import java.util.List;
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
        CustomerEntity entity = mapper.toEntity(customer);

        entity.setOpeningDate(ZonedDateTime.now());
        CustomerEntity entitySaved = repository.save(entity);

        if(!CollectionUtils.isEmpty(customer.getMessages())) {
            List<MessageEntity> messageEntities = customer.getMessages()
                    .stream()
                    .map(message -> {
                        MessageEntity messageEntity = messageRepository.findById(message.getIdMessage()).get();
                        messageEntity.setCustomer(entitySaved);
                        return messageEntity;
                    })
                    .collect(Collectors.toList());
            messageRepository.saveAll(entity.getMessages());
        }
        return entitySaved.getIdCustomer();
    }

    @Override
    public void updateCustomer(Customer customerToUpdate) {
        CustomerEntity entity = repository.findById(customerToUpdate.getIdCustomer()).get();
        if(StringUtils.isNotEmpty(customerToUpdate.getName())) {
            entity.setName(customerToUpdate.getName());
        }
        if(StringUtils.isNotEmpty(customerToUpdate.getReference())) {
            entity.setReference(customerToUpdate.getReference());
        }
        repository.save(entity);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return mapper.toDomains((List) repository.findAll());
    }

    @Override
    public Customer getCustomer(Long idCustomer) {
        return mapper.toDomain(repository.findById(idCustomer).get());
    }
}
