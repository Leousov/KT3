package com.leo.repositories;

import com.leo.CGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRepositoryGoods extends JpaRepository<CGood, Long> {
    CGood findById(UUID id);

    List<CGood> findByName(String name);

    List<CGood> findByCategory(String category);

    List<CGood> findByPrice(double price);
    @Query("SELECT g FROM CGood g WHERE g.id IN (SELECT o.gid FROM COrder o WHERE o.uid IN (SELECT u.id FROM CUser u WHERE u.gender = true))")
    List<CGood> report();
    @Transactional
    @Modifying
    @Query("update CGood g set g.id = ?1, g.name = ?2, g.price = ?3, g.category = ?4 where g.id = ?1")
    void updateGood(UUID id, String name, double price, String category);
    }
