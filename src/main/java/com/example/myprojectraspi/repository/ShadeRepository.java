package com.example.myprojectraspi.repository;

import com.example.myprojectraspi.model.ShadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface ShadeRepository extends JpaRepository<ShadeEntity, Long> {
    void deleteShadeEntityById(Long id);
    void findId(String id1);
    void findName(String name);
    void findAddress(Integer address);
    void
}
