package com.example.Community.Platform.Repository.Jamming_Repo;

import com.example.Community.Platform.Entity.Jamming_Entity.JammingParticipant;
import com.example.Community.Platform.Entity.Jamming_Entity.JammingSession;
import com.example.Community.Platform.Entity.Login_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JammingParticipantRepository extends JpaRepository<JammingParticipant, Long> {

    boolean existsBySessionAndUser(JammingSession session, Login_User user);

    List<JammingParticipant> findBySessionAndLeftAtIsNull(JammingSession session);

    Optional<JammingParticipant> findBySessionAndUserAndLeftAtIsNull(
            JammingSession session, Login_User user);
}

