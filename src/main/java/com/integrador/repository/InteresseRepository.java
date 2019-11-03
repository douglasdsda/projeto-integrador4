package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Interesse;
import com.integrador.entities.pk.InteressePK;

public interface InteresseRepository extends JpaRepository<Interesse, InteressePK> {

}
