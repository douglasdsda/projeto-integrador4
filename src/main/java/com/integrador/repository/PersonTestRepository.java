package com.integrador.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.PersonTest;

public interface PersonTestRepository extends JpaRepository<PersonTest, BigInteger> {

}
