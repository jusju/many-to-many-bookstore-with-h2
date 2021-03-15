package k2020.manytomany.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReaderRepository extends CrudRepository <Reader, Long> {

	List<Reader> findByName(String name);

}
