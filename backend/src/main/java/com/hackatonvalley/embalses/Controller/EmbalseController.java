import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class EmbalseController {

    private final EmbalseService embalseService;

    public EmbalseController(EmbalseService embalseService) {
        this.embalseService = embalseService;
    }

    @GetMapping("/embalses")
    public List<EmbalseDTO> obtenerEmbalsesEnRadio(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {
        return embalseService.obtenerEmbalsesEnRadio(latitud, longitud, radio);
    }
}
