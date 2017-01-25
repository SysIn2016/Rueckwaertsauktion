package Sysint2016.Rueckwaertsauktion;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProduktRepository extends
  CrudRepository<Produkt, String> {
     
    List<Produkt> findById(String id);
}