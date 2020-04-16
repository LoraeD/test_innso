package technique.innso.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CreateCustomerDTO {

    @ApiModelProperty(value = "the customer's name", required = true, example = "'Jérémie Durand'")
    private String name;

    @ApiModelProperty(value = "the list of technical identifiers of customer's messages", example = "[1,2]")
    private List<Long> idMessages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getIdMessages() {
        return idMessages;
    }

    public void setIdMessages(List<Long> idMessages) {
        this.idMessages = idMessages;
    }
}
