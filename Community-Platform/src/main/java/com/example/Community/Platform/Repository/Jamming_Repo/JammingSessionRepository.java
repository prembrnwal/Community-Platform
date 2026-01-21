package com.example.Community.Platform.Repository.Jamming_Repo;


import com.example.Community.Platform.Entity.Jamming_Entity.JammingSession;
import com.example.Community.Platform.Enum.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JammingSessionRepository extends JpaRepository<JammingSession, Long> {

    List<JammingSession> findByGroupId(Long groupId);

    List<JammingSession> findByStatus(SessionStatus status);
}

