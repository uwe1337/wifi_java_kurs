package at.wifi.swdev.kostenrechner.service;

import at.wifi.swdev.kostenrechner.data.Author;
import at.wifi.swdev.kostenrechner.data.Kostenrechner;
import at.wifi.swdev.kostenrechner.repository.KostenrechnerRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KostenrechnerService {

    @Autowired
    private KostenrechnerRepository krRepository;

    public List<Kostenrechner> findAll() {
        return krRepository.findAll();
    }

    public double getTotalAmount() {
        double totalAmount = 0.0;
        long count = 0;
        for (Kostenrechner item : krRepository.findAll()) {
            totalAmount += item.getAmount();
            count++;
        }
        if (count > 1) {
            return totalAmount;
        } else {
            return 0.0;
        }
    }
    
    public ArrayList<String> getTotalCategorys() {
        ArrayList<String> list = new ArrayList<>();
        for (Kostenrechner item : krRepository.findAll()) {
            if(!list.contains(item.getCategory())) {
                list.add(item.getCategory());
            }
        }
        return list;
    }

    public void addCost(Kostenrechner kostenrechner) {
        krRepository.save(kostenrechner);
    }

    public boolean removeCost(long id) {
        krRepository.deleteById(id);
        return true;
    }

    public boolean editCost(long editID,
            double editAmount,
            LocalDate editDate,
            String editCategory,
            String editDescription) {

        boolean foundID = false;
        for (Kostenrechner item : krRepository.findAll()) {
            if (item.getId() == editID) {
                foundID = true;
                item.setAmount(editAmount);
                item.setDate(editDate);
                item.setCategory(editCategory);
                item.setDescription(editDescription);
                krRepository.save(item);
                break;
            }
        }
        return foundID;
    }

    public HashMap<String, Double> getHMForCategoryAndAmount() {
        HashMap<String, Double> hashmap = new HashMap<>();

        for (Kostenrechner item : krRepository.findAll()) {
            if (hashmap.containsKey(item.getCategory())) {
                double amount = hashmap.get(item.getCategory());
                amount += item.getAmount();
                hashmap.put(item.getCategory(), amount);
            } else {
                hashmap.put(item.getCategory(), item.getAmount());
            }
        }
        return hashmap;
    }

    public ArrayList<Double> getArrayValues(HashMap<String, Double> hashmap) {
        ArrayList<Double> arrayList = new ArrayList<>();

        for (HashMap.Entry<String, Double> item : hashmap.entrySet()) {
            arrayList.add(item.getValue());
        }
        return arrayList;
    }

    public ArrayList<String> getArrayCategorys(HashMap<String, Double> hashmap) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (HashMap.Entry<String, Double> item : hashmap.entrySet()) {
            arrayList.add(item.getKey());
        }
        return arrayList;
    }

    public String getAuthor(Author author) {
        return author.getAuthor();
    }

}
