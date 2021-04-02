package com.example.todo.app.repository.jpa;

import com.example.todo.app.table.jpa.OmOd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmOdJpaRepository extends JpaRepository<OmOd, String> {
}
