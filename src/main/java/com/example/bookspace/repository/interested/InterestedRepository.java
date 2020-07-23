package com.example.bookspace.repository.interested;

import com.example.bookspace.model.interested.Interested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedRepository extends JpaRepository<Interested, Long> {
}
