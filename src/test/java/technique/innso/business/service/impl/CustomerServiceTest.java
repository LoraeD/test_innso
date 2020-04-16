package technique.innso.business.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import technique.innso.business.domain.Customer;
import technique.innso.business.domain.Message;
import technique.innso.business.mapper.CustomerMapper;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.entity.MessageEntity;
import technique.innso.dao.repository.CustomerRepository;
import technique.innso.dao.repository.MessageRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl serviceToTest;

    @Mock
    private CustomerRepository repositoryMock;

    @Mock
    private MessageRepository messageRepositoryMock;

    @Mock
    private CustomerMapper mapperMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(serviceToTest, "repository", repositoryMock);
        ReflectionTestUtils.setField(serviceToTest, "messageRepository", messageRepositoryMock);
        ReflectionTestUtils.setField(serviceToTest, "mapper", mapperMock);
    }

    @Test
    public void createCustomerWithoutMessageTest() {
        // Given
        Customer customerToCreate = new Customer();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(1L);

        Mockito.when(mapperMock.toEntity(customerToCreate)).thenReturn(customerEntity);
        Mockito.when(repositoryMock.save(customerEntity)).thenReturn(customerEntity);
        Mockito.when(messageRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new MessageEntity()));
        Mockito.when(messageRepositoryMock.saveAll(ArgumentMatchers.anyList())).thenReturn(new ArrayList<>());

        // When
        Long result = serviceToTest.createCustomer(customerToCreate);

        // Then
        Assert.assertNotNull(result);
        Assert.assertEquals(customerEntity.getIdCustomer(), result);

        Mockito.verify(mapperMock, Mockito.times(1)).toEntity(customerToCreate);
        Mockito.verify(repositoryMock, Mockito.times(1)).save(customerEntity);
        Mockito.verifyNoInteractions(messageRepositoryMock);
    }

    @Test
    public void createCustomerWithMessageTest() {
        // Given
        Customer customerToCreate = new Customer();

        Message message = new Message();
        message.setIdMessage(2L);
        customerToCreate.setMessages(Arrays.asList(message));

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(1L);

        Mockito.when(mapperMock.toEntity(customerToCreate)).thenReturn(customerEntity);
        Mockito.when(repositoryMock.save(customerEntity)).thenReturn(customerEntity);
        Mockito.when(messageRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new MessageEntity()));
        Mockito.when(messageRepositoryMock.saveAll(ArgumentMatchers.anyList())).thenReturn(new ArrayList<>());

        // When
        Long result = serviceToTest.createCustomer(customerToCreate);

        // Then
        Assert.assertNotNull(result);
        Assert.assertEquals(customerEntity.getIdCustomer(), result);

        Mockito.verify(mapperMock, Mockito.times(1)).toEntity(customerToCreate);
        Mockito.verify(repositoryMock, Mockito.times(1)).save(customerEntity);
        Mockito.verify(messageRepositoryMock, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(messageRepositoryMock, Mockito.times(1)).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    public void createCustomerWithIdMessageNoExistTest() {
        // Given
        Customer customerToCreate = new Customer();

        Message message = new Message();
        message.setIdMessage(2L);
        customerToCreate.setMessages(Arrays.asList(message));

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(1L);

        Mockito.when(mapperMock.toEntity(customerToCreate)).thenReturn(customerEntity);
        Mockito.when(repositoryMock.save(customerEntity)).thenReturn(customerEntity);
        Mockito.when(messageRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(messageRepositoryMock.saveAll(ArgumentMatchers.anyList())).thenReturn(new ArrayList<>());

        // When
        Long result = serviceToTest.createCustomer(customerToCreate);

        // Then
        Assert.assertNotNull(result);
        Assert.assertEquals(customerEntity.getIdCustomer(), result);

        Mockito.verify(mapperMock, Mockito.times(1)).toEntity(customerToCreate);
        Mockito.verify(repositoryMock, Mockito.times(1)).save(customerEntity);
        Mockito.verify(messageRepositoryMock, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(messageRepositoryMock, Mockito.times(0)).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    public void createCustomerWithNullTest() {
        // When
        Long result = serviceToTest.createCustomer(null);

        // Then
        Assert.assertNull(result);

        Mockito.verifyNoInteractions(mapperMock);
        Mockito.verifyNoInteractions(repositoryMock);
        Mockito.verifyNoInteractions(messageRepositoryMock);
    }

    @Test
    public void updateCustomerTest() {
        // Given
        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(1L);
        customerToUpdate.setName("Test");
        customerToUpdate.setReference("REF12");

        CustomerEntity entity = new CustomerEntity();

        Mockito.when(repositoryMock.findById(customerToUpdate.getIdCustomer())).thenReturn(Optional.of(entity));
        Mockito.when(repositoryMock.save(entity)).thenReturn(entity);

        // When
        serviceToTest.updateCustomer(customerToUpdate);

        // Then
        Mockito.verify(repositoryMock, Mockito.times(1)).findById(customerToUpdate.getIdCustomer());
        Mockito.verify(repositoryMock, Mockito.times(1)).save(entity);
    }

    @Test
    public void updateCustomerWithoutNoCustomerTest() {
        // Given
        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(1L);
        customerToUpdate.setName("Test");
        customerToUpdate.setReference("REF12");

        CustomerEntity entity = new CustomerEntity();

        Mockito.when(repositoryMock.findById(customerToUpdate.getIdCustomer())).thenReturn(Optional.empty());
        Mockito.when(repositoryMock.save(entity)).thenReturn(entity);

        // When
        serviceToTest.updateCustomer(customerToUpdate);

        // Then
        Mockito.verify(repositoryMock, Mockito.times(1)).findById(customerToUpdate.getIdCustomer());
        Mockito.verify(repositoryMock, Mockito.times(0)).save(ArgumentMatchers.any(CustomerEntity.class));
    }

    @Test
    public void updateCustomerWithNullTest() {
        // When
        serviceToTest.updateCustomer(null);

        // Then
        Mockito.verifyNoInteractions(repositoryMock);
    }

}
