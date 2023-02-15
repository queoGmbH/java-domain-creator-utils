package de.queo.domaincreatorutils.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import de.queo.domaincreatorutils.test.dao.BuildingDao;
import de.queo.domaincreatorutils.test.dao.UserDao;
import de.queo.domaincreatorutils.test.domain.Building;

@SpringBootTest
@Transactional
public class DomainTest {

    @Autowired
    private DomainCreatorUtil.Persistent domainCreatorUtil;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testPersistent() {
        Building building = this.domainCreatorUtil.getBuildingBuilder().build();

        assertThat(this.buildingDao.findAll()).containsExactly(building);
        assertThat(this.userDao.findAll()).containsExactly(building.getArchitect());
    }
    
}
