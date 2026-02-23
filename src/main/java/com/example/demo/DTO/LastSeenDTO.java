package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LastSeenDTO {

	private List<LocalDateTime> lastSennUser = new ArrayList<>();

	public List<LocalDateTime> getLastSennUser() {
		return lastSennUser;
	}

	public void setLastSennUser(List<LocalDateTime> lastSennUser) {
		this.lastSennUser = lastSennUser;
	}

	
}
