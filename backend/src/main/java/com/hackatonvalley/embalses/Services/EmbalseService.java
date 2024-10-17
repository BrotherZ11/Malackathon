import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmbalseService {

    @Autowired
    private EmbalseRepository embalseRepository;

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la tierra en km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Retorna la distancia en kilómetros
    }

    public List<EmbalseDTO> obtenerEmbalsesEnRadio(double latitud, double longitud, double radio) {
        List<EmbalseDTO> todosLosEmbalses = embalseRepository.obtenerEmbalses();
        return todosLosEmbalses.stream()
                .filter(embalse -> calcularDistancia(latitud, longitud, embalse.getCoordenadaX(), embalse.getCoordenadaY()) <= radio)
                .collect(Collectors.toList());
    }
}
