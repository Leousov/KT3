package com.leo.repositories;

import com.leo.COrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRepositoryOrders extends JpaRepository<COrder, Long> {

    COrder findById(UUID id);

    List<COrder> findByGid(UUID Gid);

    List<COrder> findByUid(UUID uid);

    List<COrder> findByDate(LocalDate date);

    void deleteById(UUID id);
    @Transactional
    @Modifying
    @Query("update COrder g set g.id = ?1, g.uid = ?2, g.gid = ?3, g.date = ?4 where g.id = ?1")
    void updateOrder(UUID id, UUID uid, UUID gid, LocalDate date);
}
