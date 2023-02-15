package de.queo.domaincreatorutils.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.queo.domaincreatorutils.test.domain.Building;

@Repository
public interface BuildingDao extends JpaRepository<Building, Long> {

}
