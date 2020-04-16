package technique.innso.business.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import technique.innso.api.dto.CreateMessageDTO;
import technique.innso.api.dto.GetMessageDTO;
import technique.innso.business.domain.Message;
import technique.innso.dao.entity.MessageEntity;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageEntity toEntity(Message domain);

    @Mapping(target = "customer.messages", ignore = true)
    Message toDomain(MessageEntity entity);

    @Mapping(target = "authorName", source = "name")
    Message toDomain(CreateMessageDTO dto);

    GetMessageDTO toDTO(Message domain);

}