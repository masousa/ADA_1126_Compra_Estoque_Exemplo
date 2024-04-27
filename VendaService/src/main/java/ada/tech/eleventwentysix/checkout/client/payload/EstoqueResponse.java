package ada.tech.eleventwentysix.checkout.client.payload;

import lombok.Data;

import java.util.List;

@Data
public class EstoqueResponse {
    private String idRemocao;
    List<String> itemsRemovidos;
}
