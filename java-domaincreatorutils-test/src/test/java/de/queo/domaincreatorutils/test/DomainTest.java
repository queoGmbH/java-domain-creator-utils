package de.queo.domaincreatorutils.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import de.queo.domaincreatorutils.test.dao.BuildingDao;
import de.queo.domaincreatorutils.test.dao.UserDao;
import de.queo.domaincreatorutils.test.domain.Building;
import de.queo.domaincreatorutils.test.domain.User;

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

    @Test
    void testModifyInBuild() {
        Building building = this.domainCreatorUtil.getBuildingBuilder().build(b -> {
            b.name.setValue("Eifelturm");
        });

        assertThat(building.getName()).isEqualTo("Eifelturm");
    }

    void testOptionalField() {
        User unmodifiedUser = this.domainCreatorUtil.getUserBuilder().build();
        User modifiedWithoutOptional = this.domainCreatorUtil.getUserBuilder().build(user -> {
            user.heightInCm.setValue(183);
        });
        User modifiedWithOptional = this.domainCreatorUtil.getUserBuilder().build(user -> {
            user.heightInCm.setValue(Optional.of(183));
        });

        assertThat(unmodifiedUser.getHeightInCm()).isEmpty();
        assertThat(modifiedWithoutOptional.getHeightInCm()).hasValue(183);
        assertThat(modifiedWithOptional.getHeightInCm()).hasValue(183);
    }

    @Test
    void testOptionalEntityField_allowsModificationOfDefaultlyUndefinedEntityField() {
        Building unmodifiedBuilding = this.domainCreatorUtil.getBuildingBuilder().build();
        Building modifiedBuilding = this.domainCreatorUtil.getBuildingBuilder().build(b -> {
            b.reviewer.modify(reviewer -> reviewer.fullName.setValue("Kamado Tanjiro"));
        });

        assertThat(unmodifiedBuilding.getReviewer()).isEmpty();

        assertThat(modifiedBuilding.getReviewer()).isPresent();
        assertThat(modifiedBuilding.getReviewer().get().getFullName()).isEqualTo("Kamado Tanjiro");
    }

}
