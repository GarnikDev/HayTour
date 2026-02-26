package com.sevitours.demo.bill_item.repo;

import com.sevitours.demo.bill_item.model.Bill_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Bill_itemRepository extends JpaRepository<Bill_item, UUID> {
}
