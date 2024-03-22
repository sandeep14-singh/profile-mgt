package com.org.profile.repo;

import com.org.profile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
