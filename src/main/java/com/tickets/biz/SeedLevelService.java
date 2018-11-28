package com.tickets.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.data.SeedLevelDAO;
import com.tickets.domains.SeedLevel;

@Service
public class SeedLevelService {

	@Autowired
	SeedLevelDAO levelDAO;

	public List<SeedLevel> getAllSeedLevels() {
		List<SeedLevel> list = null;
		list = levelDAO.retrieveAllSeedLevels();
		return list;
	}
}
