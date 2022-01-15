package com.leo.repositories;

import com.leo.CUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRepositoryUsers extends JpaRepository<CUser, Long>{
    CUser findById(UUID id);

    List<CUser> findByGender(boolean gender);

    List<CUser> findByName(String name);

    List<CUser> findByLogin(String login);
    @Query("SELECT u FROM CUser u WHERE u.gender = true")
    List<CUser> findMale();
    @Transactional
    @Modifying
    @Query("update CUser set id = ?1, login = ?2, name = ?3, gender = ?4, datebirth = ?5 where id = ?1")
    void updateUser(UUID id, String login, String name, boolean gender, LocalDate datebirth);
}