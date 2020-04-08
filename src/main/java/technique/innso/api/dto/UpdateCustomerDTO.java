package technique.innso.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class UpdateCustomerDTO {

    @ApiModelProperty(value = "the customer's technical identifier", example = "1", required = true)
    private Long idCustomer;

    @ApiModelProperty(value = "the customer's name", example = "Jérémie Durand")
    private String name;

    @ApiModelProperty(value = "the customer's reference", example = "KA-18B6")
    private String reference;

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
