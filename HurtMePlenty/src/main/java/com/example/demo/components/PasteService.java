package com.example.demo.components;

import com.example.demo.domain.Duration;
import com.example.demo.domain.Paste;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasteService {
    private final PasteRepository pasteRepository;

    public String addNewPaste(String pasteText, Duration duration, Boolean access, LocalDateTime created) {
        LocalDateTime expiration = calculateExpirationDate(created, duration);
        String hash = UUID.randomUUID().toString();

        Paste paste = new Paste(pasteText, expiration, access, hash, created);

        pasteRepository.save(paste);
        return hash;
    }

    public List<Paste> get10pastes() {
        Pageable topTen = PageRequest.of(0, 10);
        return pasteRepository.findOrdered(topTen);
    }

    public Paste getPaste(String hash) {
        return pasteRepository.getByHash(hash);
    }

    @Scheduled(fixedRate = 60000)
    public void scheduledCleaning() {
        pasteRepository.clean(LocalDateTime.now());
    }

    private LocalDateTime calculateExpirationDate(LocalDateTime created, Duration duration) {
        switch (duration) {
            case TEN_MINUTES:
                return created.plus(10, ChronoUnit.MINUTES);

            case ONE_HOUR:
                return created.plus(1, ChronoUnit.HOURS);

            case THREE_HOURS:
                return created.plus(3, ChronoUnit.HOURS);

            case ONE_DAY:
                return created.plus(1, ChronoUnit.DAYS);

            case ONE_WEEK:
                return created.plus(1, ChronoUnit.WEEKS);

            case ONE_MONTH:
                return created.plus(1, ChronoUnit.MONTHS);

            case INDEFINITE:
                return LocalDateTime.MAX;

            default:
                return created.plus(1, ChronoUnit.HOURS);
        }
    }
}
