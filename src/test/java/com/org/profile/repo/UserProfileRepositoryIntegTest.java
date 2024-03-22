package com.org.profile.repo;

import com.org.profile.entity.UserProfile;
import com.org.profile.repo.UserProfileRepository;
import com.org.profile.service.PrimeChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserProfileRepositoryIntegTest {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    public void testCreateUser() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(new Random().nextLong(1000000000));
        userProfile.setAge(21);
        userProfile.setEmail("someEmail");
        userProfile.setName("Sandeep");
        UserProfile saved = userProfileRepository.save(userProfile);
        assertThat(saved.getName()).isEqualTo("Sandeep");
        UserProfile fetched = userProfileRepository.findById(saved.getId()).get();
        assertThat(fetched.getName()).isEqualTo("Sandeep");
    }
}
