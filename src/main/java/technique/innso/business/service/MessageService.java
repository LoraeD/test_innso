package technique.innso.business.service;

import technique.innso.business.domain.Message;

public interface MessageService {

    Long createMessage(Message messageToCreate);

    Message getMessageById(Long idMessage);

    Message getMessageCustomerById(Long idCustomer, Long idMessage);

}
