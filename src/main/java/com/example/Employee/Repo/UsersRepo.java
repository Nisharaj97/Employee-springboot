

package com.example.Employee.Repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee.Model.Users;



@Repository
public interface UsersRepo extends JpaRepository<com.example.Employee.Model.Users, Long>{

	Users findByUserName(String userName);

}
