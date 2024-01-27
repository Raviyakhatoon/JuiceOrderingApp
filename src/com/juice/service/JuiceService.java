package com.juice.service;

 

import java.util.List;

import com.juice.entity.Juice;



public interface JuiceService {
	public boolean addJuice(Juice j);
	public boolean deleteJuice( String juiceId);
	public Boolean updatejuice(Juice j) ;
	public Juice getJuiceById(String juiceId);
	public List<Juice> showAllJuice();
	public List<Juice> getJuiceByCategory(String  category);
	

}
