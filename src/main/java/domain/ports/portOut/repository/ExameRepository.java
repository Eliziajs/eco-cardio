package domain.ports.portOut.repository;


import domain.model.Exame;


import java.util.List;
import java.util.Optional;

/**
 * ExameRepository interface is a CDI bean with application scope.
 * It defines the contract for data access operations related to Exame entities.
 * The interface provides methods to perform CRUD (Create, Read, Update, Delete) operations.
 */

public interface ExameRepository {

  /**
   * Retrieves all Exame entities and returns them wrapped in Optional containers.
   *
   * @return List of Optional containing Exame entities. Each Optional may or may not contain an Exame.
   */
  List<Optional<Exame>>findAll1 ();

  /**
   * Finds a specific Exame entity by its unique identifier.
   *
   * @param id The unique identifier of the Exame entity to find
   * @return Optional containing the Exame if found, or empty Optional if not found
   */
  Optional<Exame> findById(Long id);
  /**
   * Saves a new Exame entity to the persistent storage.
   *
   * @param exame The Exame entity to be saved
   * @return The saved Exame entity, potentially with updated fields (like generated IDs)
   */
  Exame save(Exame exame);
  /**
   * Updates an existing Exame entity in the persistent storage.
   *
   * @param exame The Exame entity to be updated
   * @return The updated Exame entity
   */
  Exame update(Exame exame);
  /**
   * Deletes an Exame entity from the persistent storage.
   *
   * @param exame The Exame entity to be deleted
   */
  void delete(Exame exame);


}
