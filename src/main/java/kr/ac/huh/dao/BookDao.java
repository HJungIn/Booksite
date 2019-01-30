package kr.ac.huh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.huh.model.Book;

@Repository
public class BookDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Book> getBooks(){
		
		String sqlStatement = "select * from book";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Book>() {

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setCategory(rs.getString("category"));
				book.setManufacturer(rs.getString("manufacturer"));
				book.setDescription(rs.getString("description"));
				book.setWriter(rs.getString("writer"));
				
				
				return book;
			}

			
		} );
	}
}
