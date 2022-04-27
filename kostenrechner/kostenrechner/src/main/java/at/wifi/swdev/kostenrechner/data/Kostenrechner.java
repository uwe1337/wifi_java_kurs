package at.wifi.swdev.kostenrechner.data;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Kostenrechner implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private double amount;
    
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    
    @Column(length = 512)
    private String category;
    
    @Column(length = 1024)
    private String description;

    public Kostenrechner() {
    }

    public Kostenrechner(double amount, LocalDate date, String category, String description) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kostenrechner other = (Kostenrechner) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "toString: Kostenrechner{" + "id=" + id + ", amount=" + amount + ", date=" + date + ", category=" + category + ", description=" + description + '}';
    }
    
}
