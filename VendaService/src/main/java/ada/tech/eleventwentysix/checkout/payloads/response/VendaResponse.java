package ada.tech.eleventwentysix.checkout.payloads.response;

import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
@Schema
public class VendaResponse {
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002")
    private String id;
    private List<ItemResponse> itens;
    private String status;


}
