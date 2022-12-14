package com.example.spring.boot.jdbc.h2.crud.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.spring.boot.jdbc.h2.crud.entities.Person;

@Repository
public class PersonRepository {
	
	private final static String SELECT_ALL_SQL = "SELECT id, name, telephone, birth_date, salary FROM Person ORDER BY name";
	private final static String SELECT_BY_ID_SQL = "SELECT id, name, telephone, birth_date, salary FROM Person WHERE id = ?";
	private final static String INSERT_SQL = "INSERT INTO Person(name, telephone, birth_date, salary) VALUES(?,?,?,?)";
	private final static String UPDATE_SQL = "UPDATE Person SET name = ?, telephone = ?, birth_date = ?, salary = ? WHERE id = ?";
	private final static String DELETE_SQL = "DELETE FROM Person WHERE Id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Person> selectAll() {
		return jdbcTemplate.query(SELECT_ALL_SQL, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Person(
					rs.getLong("id"),
					rs.getString("name"),
					rs.getString("telephone"),
					rs.getObject("birth_date", LocalDate.class),
					rs.getBigDecimal("salary"));
			}
		});
	}

	public Person selectById(Long id) {
		return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Person(
					rs.getLong("id"),
					rs.getString("name"),
					rs.getString("telephone"),
					rs.getObject("birth_date", LocalDate.class),
					rs.getBigDecimal("salary"));
			}
		}, id);
	}

	
	public Person insert(Person person) {

		person.setName(person.getName().toUpperCase());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
	    
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1, person.getName());
				ps.setObject(2, person.getTelephone());
				ps.setObject(3, person.getBirthDate());
				ps.setObject(4, person.getSalary());
				return ps;
		}, keyHolder);

	    person.setId(keyHolder.getKey().longValue());
	    return person;		
	}
	
	public Person update(Person person) {
		person.setName(person.getName().toUpperCase());
		Object[] args = new Object[] {
			person.getName(),
			person.getTelephone(),
			person.getBirthDate(),
			person.getSalary(),
			person.getId()
		};
		jdbcTemplate.update(UPDATE_SQL, args);
		return person;
	}
	
	public void delete(Long id) {
		jdbcTemplate.update(DELETE_SQL, new Object[]{ id });
	}

}
