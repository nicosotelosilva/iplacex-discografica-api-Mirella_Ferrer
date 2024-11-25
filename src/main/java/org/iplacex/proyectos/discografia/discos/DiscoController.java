package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepository.existsById(disco.getIdArtista())) {
            return new ResponseEntity<>("El artista con ID " + disco.getIdArtista() + " no existe.",
                    HttpStatus.BAD_REQUEST);
        }

        try {
            Disco nuevoDisco = discoRepository.save(disco);
            return new ResponseEntity<>(nuevoDisco, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el disco: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/discos")
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping("/disco/{id}")
    public ResponseEntity<Disco> handleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> discoOptional = discoRepository.findById(id);
        if (discoOptional.isPresent()) {
            return new ResponseEntity<>(discoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/artista/{id}/discos")
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

}
