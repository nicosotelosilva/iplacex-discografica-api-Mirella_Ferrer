package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaRepository.save(artista);
        return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> handleGetArtistasRequest() {
        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/artista/{id}")
    public ResponseEntity<Artista> handleGetArtistaRequest(@PathVariable String id) {
        Optional<Artista> artistaOptional = artistaRepository.findById(id);
        if (artistaOptional.isPresent()) {
            return new ResponseEntity<>(artistaOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {
        if (!artistaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artista.setId(id);
        Artista artistaActualizado = artistaRepository.save(artista);
        return new ResponseEntity<>(artistaActualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/artista/{id}")
    public ResponseEntity<Void> handleDeleteArtistaRequest(@PathVariable String id) {
        if (!artistaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artistaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
