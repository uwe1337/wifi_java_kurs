package at.wifi.swdev.kostenrechner.repository;

import at.wifi.swdev.kostenrechner.data.Kostenrechner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KostenrechnerRepository extends JpaRepository<Kostenrechner, Long> {
    
}
