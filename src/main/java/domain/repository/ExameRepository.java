package domain.repository;


import domain.model.Exame;


import java.util.List;
import java.util.Optional;

public interface ExameRepository {

  List<Optional<Exame>>findAll1 ();

  Optional<Exame> findById(Long id);
  Exame save(Exame exame);
  Exame update(Exame exame);
  void delete(Exame exame);


}
