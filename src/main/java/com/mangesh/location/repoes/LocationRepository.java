package com.mangesh.location.repoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.mangesh.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
