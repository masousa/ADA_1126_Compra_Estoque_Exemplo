package ada.tech.eleventwentysix.checkout.client.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemEstoqueRequest implements Serializable {
    private String skuId;
    private int quantidade;
    private String compraId;
}
