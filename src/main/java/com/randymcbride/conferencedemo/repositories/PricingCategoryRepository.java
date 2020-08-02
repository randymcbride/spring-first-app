package com.randymcbride.conferencedemo.repositories;

import com.randymcbride.conferencedemo.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingCategoryRepository extends JpaRepository<PricingCategory, String> {
}
