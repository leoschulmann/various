package com.example.demo.components;

import com.example.demo.domain.Duration;
import com.example.demo.domain.Paste;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteController {

    @Value("${domain.url}")
    private String domain;

    private final PasteService pasteService;

    @PostMapping("/")
    public ResponseEntity<String> uploadPaste(@RequestParam String paste,
                                              @RequestParam Duration duration, @RequestParam Boolean access) {
        String pasteHash = pasteService.addNewPaste(paste, duration, access, LocalDateTime.now());

        return new ResponseEntity<>(domain + "/" + pasteHash, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Paste>> getTenLatestPastes() {
        List<Paste> pastes = pasteService.get10pastes();
        return new ResponseEntity<>(pastes, HttpStatus.OK);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Paste> getPasteByHash(@PathVariable String hash) {
        Paste paste = pasteService.getPaste(hash);
        if (paste == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(paste, HttpStatus.OK);
    }
}
