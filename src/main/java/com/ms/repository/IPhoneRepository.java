package com.ms.repository;

import com.ms.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<PhoneEntity,Long> {

}
