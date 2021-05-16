package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerMapServiceTest {

    public static final Long FIRST_ID = 1L;
    private static final String LAST_NAME = "Onana";
    public static final String FIRST_NAME1 = "Owner1";
    public static final String FIRST_NAME2 = "Owner2";
    public static final String DOUALA = "Douala";
    public static final String YDE = "Yaoundé";

    private Owner owner1;
    private Owner owner2;

    PetTypeService petTypeServMock;

    PetService petServMock;

    OwnerMapService ownerMapServ;

    @BeforeEach
    void setUp() {
        ownerMapServ = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        owner1 = new Owner();
        owner1.setFirstName(FIRST_NAME1);
        owner1.setLastName(LAST_NAME);
        owner1.setCity(DOUALA);

        owner2 = new Owner();
        owner2.setFirstName(FIRST_NAME2);
        owner2.setLastName(LAST_NAME);
        owner2.setCity(YDE);

        ownerMapServ.save(owner1);
        ownerMapServ.save(owner2);
    }

    @Test
    void findById() {
        Owner ownerById = ownerMapServ.findById(FIRST_ID);
        assertThat(ownerById.getId()).isEqualTo(FIRST_ID);
    }

    @Test
    void findAll() {
        List<Owner> allOwner = ownerMapServ.findAll();
        assertThat(allOwner).contains(owner1,owner2);
    }

    @Test
    void delete() {
        ownerMapServ.delete(owner1);
        assertThat(ownerMapServ.findAll()).hasSize(1);
    }

    @Test
    void deleteById() {
        ownerMapServ.deleteById(FIRST_ID);
        assertThat(ownerMapServ.findAll()).hasSize(1);
    }

    @Test
    void save() {
        Owner owner = new Owner();
        owner.setFirstName("Owner3");
        Owner savedOwner = ownerMapServ.save(owner);
        assertThat(ownerMapServ.findAll()).hasSize(3);
    }

    @Test
    void findByLastName() {
        List<Owner> ownersByLastName = ownerMapServ.findByLastName(LAST_NAME);
        assertThat(ownersByLastName).contains(owner1,owner2);
    }
}