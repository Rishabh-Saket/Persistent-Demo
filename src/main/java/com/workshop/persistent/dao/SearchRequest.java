package com.workshop.persistent.dao;

import lombok.Data;

@Data
public class SearchRequest 
{
	private String firstName;
	private String lastName;
	private String email;
}
