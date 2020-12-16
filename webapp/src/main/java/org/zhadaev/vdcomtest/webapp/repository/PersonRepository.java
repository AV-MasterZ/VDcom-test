package org.zhadaev.vdcomtest.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.zhadaev.vdcomtest.webapp.model.Person;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {
}
