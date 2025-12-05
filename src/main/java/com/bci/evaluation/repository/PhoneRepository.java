package com.bci.evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Integer> {
}
