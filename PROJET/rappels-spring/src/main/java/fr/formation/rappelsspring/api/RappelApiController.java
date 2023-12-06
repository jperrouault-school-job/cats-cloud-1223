package fr.formation.rappelsspring.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.rappelsspring.composant.Dependance1;
import fr.formation.rappelsspring.exception.NotFoundException;
import fr.formation.rappelsspring.model.Rappel;
import fr.formation.rappelsspring.repository.RappelRepository;
import fr.formation.rappelsspring.request.CreateRappelRequest;
import fr.formation.rappelsspring.response.RappelResponse;
import lombok.RequiredArgsConstructor;


// @Component
// @Controller
// @ResponseBody
@RestController
@RequestMapping("/api/demo") // On préfix toutes les ressources de cette classe
@RequiredArgsConstructor
public class RappelApiController {
    // @Autowired // Demande à Spring d'injecter l'instance du même type
    private final Dependance1 dependance1;// = new Dependance1();
    private final RappelRepository rappelRepository;

    // public RappelApiController(Dependance1 dependance1) {
    //     System.out.println("CREATION CTRL");
    //     this.dependance1 = dependance1;
    // }

    // @RequestMapping(value = "/api/demo", method = RequestMethod.GET)
    @GetMapping
    public String demo() {
        // return "demo";
        dependance1.demo();
        return dependance1.toString();
    }


    @GetMapping("/list")
    public List<RappelResponse> findAll() {
        return this.rappelRepository.findAll().stream()
            .map(r -> RappelResponse.builder()
                .id(r.getId())
                .name(r.getName())
                .build()
            ).toList()
        ;
    }

    @GetMapping("/{id}")
    public RappelResponse findById(@PathVariable String id) {
        Rappel rappel = this.rappelRepository.findById(id).orElseThrow(NotFoundException::new);
        RappelResponse resp = new RappelResponse();

        BeanUtils.copyProperties(rappel, resp);

        return resp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateRappelRequest request) {
        Rappel rappel = new Rappel();

        BeanUtils.copyProperties(request, rappel);

        return this.rappelRepository.save(rappel).getId();
    }
}
