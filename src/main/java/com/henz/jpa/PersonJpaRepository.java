package com.henz.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.henz.entity.Person;


@Repository
@Transactional
@NamedQuery(name="find_all_persons", query ="select p from Person p") //not using db columns!
public class PersonJpaRepository {

	//@Autowired --> not autowire, use following...
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person findById(int id) {
		return this.entityManager.find(Person.class, id);
	}
	
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	public Person insert(Person person) {
		return entityManager.merge(person);
	}
	
	public void delete(int id) {
		Person person = this.findById(id);
		this.entityManager.remove(person);
	}
	
	//using jpql named query
	public List<Person> findAll(){
		//see namedQuery class annotation
		TypedQuery<Person> query =  this.entityManager.createNamedQuery("find_all_persons", Person.class);
		return query.getResultList();
	}
}
