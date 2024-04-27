package ada.tech.eleventwentysix.checkout.payloads.request;

import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ItemRequest extends ItemResponse {
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002", defaultValue = "")
    @JsonProperty("id")
    private String idCompra;

}
