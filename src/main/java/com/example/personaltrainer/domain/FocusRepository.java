package com.example.personaltrainer.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface FocusRepository extends CrudRepository <Focus, Long> {
	List<Focus> findByName(String name);
}
