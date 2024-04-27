package ada.tech.eleventwentysix.checkout.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class VendaRequest {

    @JsonProperty("carrinho-id")
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002")
    private String compraId;

    private ClienteRequest cliente;
}
