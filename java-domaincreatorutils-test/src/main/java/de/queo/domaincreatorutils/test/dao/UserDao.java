package de.queo.domaincreatorutils.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.queo.domaincreatorutils.test.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
