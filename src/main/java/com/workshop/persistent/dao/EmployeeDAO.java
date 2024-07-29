package com.workshop.persistent.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workshop.persistent.models.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeDAO 
{
	private final EntityManager entityManager;
	
	public List<Employee> getAll(String firstName, String lastName, String email)
	{
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery=builder.createQuery(Employee.class);
		
		// this below statement is equivalent to select * from employee
		Root<Employee> root=criteriaQuery.from(Employee.class);
		
		//preparing a where clause for first-name
		// select from employee where firstname like %ali% any name starting with
		Predicate firstnamePredicate=builder.like(root.get("firstName"), "%"+firstName+"%");
		
		// same for lastname
		Predicate lastnamePredicate=builder.like(root.get("lastName"), "%"+lastName+"%");
		
		//same for email
		Predicate emailPredicate=builder.like(root.get("email"), "%"+email+"%");
		
		Predicate orPredicate=builder.or(firstnamePredicate,lastnamePredicate);
		// final query=> select * from employee where firstname like "%ali%"
		// or lastname like "%mo%"
		
		var andEmailPredicate=builder.and(orPredicate, emailPredicate);
		criteriaQuery.where(andEmailPredicate);
		
		TypedQuery<Employee> query=entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
