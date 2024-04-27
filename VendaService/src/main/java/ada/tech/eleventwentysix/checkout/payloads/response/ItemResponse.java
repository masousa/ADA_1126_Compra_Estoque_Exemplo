package ada.tech.eleventwentysix.checkout.payloads.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ItemResponse {
    @Schema(example = "9877")
    private String skuId;
    @Schema(example = "5,79")
    private double valorUnitario;
    @Schema(example = "10")
    private int quantidade;
}
