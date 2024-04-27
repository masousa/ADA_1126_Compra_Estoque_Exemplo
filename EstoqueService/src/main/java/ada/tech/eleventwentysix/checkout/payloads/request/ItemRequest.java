package ada.tech.eleventwentysix.checkout.payloads.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class ItemRequest implements Serializable {
    private String skuId;
    private int quantidade;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String compraId;

}
