package pl.atom.ekantor.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.atom.ekantor.model.Role;

/**
 * Created by Artur on 18.03.2017.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}
