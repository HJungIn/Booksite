package kr.ac.huh.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

	private int id;
	private String name;
	private String category;
	private String manufacturer;
	private String description;
	private String writer;
}
