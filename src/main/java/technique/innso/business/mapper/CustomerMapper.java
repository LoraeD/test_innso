package technique.innso.business.mapper;

import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import technique.innso.api.dto.CreateCustomerDTO;
import technique.innso.api.dto.GetCustomerDTO;
import technique.innso.api.dto.UpdateCustomerDTO;
import technique.innso.business.domain.Customer;
import technique.innso.business.domain.Message;
import technique.innso.dao.entity.CustomerEntity;
import technique.innso.dao.entity.MessageEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "messages", source = "idMessages", qualifiedByName = {"mapIdMessage"})
    Customer toDomain(CreateCustomerDTO dto);

    @Mapping(target = "customerName", source = "name")
    @Mapping(target = "customerMessages", source = "messages")
    GetCustomerDTO toDTO(Customer domain);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<GetCustomerDTO> toDTOs(List<Customer> domain);

    Customer toDomain(UpdateCustomerDTO dto);

    Customer toDomain(CustomerEntity entity);

    @Mapping(target = "messages", ignore = true)
    CustomerEntity toEntity(Customer domain);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<Customer> toDomains(List<CustomerEntity> entities);

    @Mapping(target = "customer", ignore = true)
    Message toDomain(MessageEntity entity);

    @Named(value = "mapIdMessage")
    default List<Message> mapMessage(List<Long> idMessage) {
        if(!CollectionUtils.isEmpty(idMessage)) {
            return idMessage.stream().map(id -> {
                Message message = new Message();
                message.setIdMessage(id);
                return message;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
