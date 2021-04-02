package com.example.todo.app.repository.jpa;

import com.example.todo.app.projection.jpa.OmOdDtlView;
import com.example.todo.app.table.jpa.OmOdDtl;
import com.example.todo.app.table.jpa.key.OmOdDtlKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmOdDtlJpaRepository extends JpaRepository<OmOdDtl, OmOdDtlKeys> {

    List<OmOdDtlView> findAllBy();
}
