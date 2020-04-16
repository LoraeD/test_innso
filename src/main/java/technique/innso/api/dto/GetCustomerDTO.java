package technique.innso.api.dto;

import io.swagger.annotations.ApiModelProperty;
import technique.innso.enumeration.Channel;

import java.time.ZonedDateTime;
import java.util.List;

public class GetCustomerDTO {

    @ApiModelProperty(value = "the customer's technical identifier", example = "1")
    private Long idCustomer;

    @ApiModelProperty(value = "the customer's name", example = "Jérémie Durand")
    private String customerName;

    @ApiModelProperty(value = "the customer's opening date", example = "2020-04-07T19:10:45.220131+02:00")
    private ZonedDateTime openingDate;

    @ApiModelProperty(value = "the customer's reference", example = "KA-18B6")
    private String reference;

    @ApiModelProperty(value = "the customer's messages")
    private List<GetAllCustomerDTOMessage> customerMessages;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public List<GetAllCustomerDTOMessage> getCustomerMessages() {
        return customerMessages;
    }

    public void setCustomerMessages(List<GetAllCustomerDTOMessage> customerMessages) {
        this.customerMessages = customerMessages;
    }

    public static class GetAllCustomerDTOMessage {

        @ApiModelProperty(value = "the message's technical identifier", example = "1")
        private Long idMessage;

        @ApiModelProperty(value = "the message's created date", example = "2020-04-07T19:15:18.824726+02:00")
        private ZonedDateTime date;

        @ApiModelProperty(value = "the message's author name", example = "Jérémie Durand")
        private String authorName;

        @ApiModelProperty(value = "the message's content", example = "Bonjour, j'ai un problème avec mon nouveau téléphone")
        private String content;

        @ApiModelProperty(value = "the channel where the message was post", example = "'MAIL'", allowableValues = "MAIL, SMS, FACEBOOK, TWITTER")
        private Channel channel;

        public Long getIdMessage() {
            return idMessage;
        }

        public void setIdMessage(Long idMessage) {
            this.idMessage = idMessage;
        }

        public ZonedDateTime getDate() {
            return date;
        }

        public void setDate(ZonedDateTime date) {
            this.date = date;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }
    }
}
