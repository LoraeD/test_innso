package technique.innso.business.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import technique.innso.business.domain.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerMapperTest {

    private CustomerMapper mapperToTest;

    @Before
    public void init() {
        mapperToTest = Mappers.getMapper(CustomerMapper.class);
    }

    @Test
    public void mapMessageTest() {
        // Given
        List<Long> listIdMessage = Arrays.asList(1L, 2L, 3L);

        // When
        List<Message> result =  mapperToTest.mapMessage(listIdMessage);

        // Then
        Assert.assertNotNull(result);
        Assert.assertEquals(listIdMessage.size(), result.size());

        Assert.assertEquals(listIdMessage.get(0), result.get(0).getIdMessage());
        Assert.assertEquals(listIdMessage.get(1), result.get(1).getIdMessage());
        Assert.assertEquals(listIdMessage.get(2), result.get(2).getIdMessage());
    }

    @Test
    public void mapMessageTestNull() {
        // When
        List<Message> result =  mapperToTest.mapMessage(null);

        // Then
        Assert.assertNull(result);
    }

    @Test
    public void mapMessageTestEmpty() {
        // When
        List<Message> result =  mapperToTest.mapMessage(new ArrayList<>());

        // Then
        Assert.assertNull(result);
    }
 }
