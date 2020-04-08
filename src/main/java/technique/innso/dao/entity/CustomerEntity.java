package technique.innso.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer")
@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_seq", allocationSize = 1, initialValue = 1)
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = -3641927528766291809L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long idCustomer;

    @Column(name = "name")
    private String name;

    @Column(name = "opening_date")
    private ZonedDateTime openingDate;

    @Column(name = "reference")
    private String reference;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<MessageEntity> messages = new ArrayList<>();

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

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }
}
