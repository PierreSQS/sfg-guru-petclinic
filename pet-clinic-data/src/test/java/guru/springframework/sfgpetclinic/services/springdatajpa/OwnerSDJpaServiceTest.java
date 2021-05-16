package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OwnerSDJpaServiceTest {

    private final static String FIRST_NAME1 = "OwnerMock 1";
    private final static String FIRST_NAME2 = "OwnerMock 2";
    private static final String LAST_NAME1 = "Smith";

    private Owner ownerMock1;
    private Owner ownerMock2;
    private Owner ownerMock3;


    @Mock
    OwnerRepository ownerRepoMock;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaServ;

    @BeforeEach
    void setUp() {
        ownerMock1 = new Owner();
        ownerMock1.setFirstName(FIRST_NAME1);
        ownerMock1.setLastName(LAST_NAME1);

        ownerMock2 = new Owner();
        ownerMock2.setFirstName(FIRST_NAME2);

        ownerMock3 = new Owner();
        ownerMock3.setFirstName(FIRST_NAME1);
        ownerMock3.setLastName(LAST_NAME1);

        List<Owner> ownerMocks = new ArrayList<>(Arrays.asList(ownerMock1, ownerMock2));
        when(ownerRepoMock.findAll()).thenReturn(ownerMocks);

        List<Owner> ownerMocksByLastName = new ArrayList<>(Arrays.asList(ownerMock1, ownerMock3));
        when(ownerRepoMock.findByLastName(FIRST_NAME1)).thenReturn(ownerMocksByLastName);

        when(ownerRepoMock.save(ownerMock1)).thenReturn(ownerMock1);

    }

    @DisplayName("find by Existing ID")
    @Test
    void findByExistingId() {
        when(ownerRepoMock.findById(anyLong())).thenReturn(Optional.of(ownerMock1));
        Owner ownerById = ownerSDJpaServ.findById(1L);
        assertThat(ownerById.getFirstName()).isEqualTo(FIRST_NAME1);
    }

    @DisplayName("find by NOT Existing ID")
    @Test
    void findByNotExistingId() {
        Owner ownerById = ownerSDJpaServ.findById(1L);
        assertThat(ownerById).isNull();
    }

    @Test
    void findAll() {
        List<Owner> foundOwners = ownerSDJpaServ.findAll();
        assertThat(foundOwners).contains(ownerMock1,ownerMock2);
    }


    @Test
    void save() {
        Owner savedOwnerMock = ownerSDJpaServ.save(ownerMock1);
        assertThat(savedOwnerMock.getFirstName()).isEqualTo(FIRST_NAME1);
    }

    @Test
    void deleteById() {
        ownerSDJpaServ.deleteById(1L);
        verify(ownerRepoMock).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerSDJpaServ.delete(ownerMock1);
        verify(ownerRepoMock).delete(any(Owner.class));
    }

    @Test
    void findByLastName() {
        List<Owner> ownersByLastName = ownerSDJpaServ.findByLastName(FIRST_NAME1);
        assertThat(ownersByLastName).contains(ownerMock1,ownerMock3);
    }
}