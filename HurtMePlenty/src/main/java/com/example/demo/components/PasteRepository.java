package com.example.demo.components;

import com.example.demo.domain.Paste;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {

    @Query("select p from Paste p where p.hash = :hash")
    Paste getByHash(String hash);

    @Query("select p from Paste p where p.access = true order by p.dateTime desc")
    List<Paste> findOrdered(Pageable p);

    @Transactional
    @Modifying
    @Query("delete from Paste p where p.expirationDateTime < :now")
    void clean(LocalDateTime now);
}
