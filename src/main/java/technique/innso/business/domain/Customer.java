package technique.innso.business.domain;

import java.time.ZonedDateTime;
import java.util.List;

public class Customer {

    private Long idCustomer;

    private String name;

    private ZonedDateTime openingDate;

    private String reference;

    private List<Message> messages;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(ZonedDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


}
