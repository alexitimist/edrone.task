package string.randomizer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import string.randomizer.Domain.MyString;


@Repository
public interface RandomizerRepository extends JpaRepository<MyString, Integer> {

}
