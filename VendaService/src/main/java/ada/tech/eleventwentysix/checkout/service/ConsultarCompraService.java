package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.mapping.ConvertCompraToVendaResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.VendaResponse;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarCompraService {

    private final CompraRepository compraRepository;

    public VendaResponse execute(String compraID){
        return ConvertCompraToVendaResponse.convertToVendaResponse(compraRepository.findByIdentificador(compraID));
    }
}
