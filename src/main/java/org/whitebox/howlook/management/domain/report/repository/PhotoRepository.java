package org.whitebox.howlook.management.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whitebox.howlook.management.domain.report.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {}
