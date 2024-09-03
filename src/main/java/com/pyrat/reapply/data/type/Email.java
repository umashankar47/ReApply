package com.pyrat.reapply.data.type;

import lombok.Data;

@Data
public class Email {
	
	private String mailId;
	private String domain;
	
	
	
	
	@Override
	public String toString() {
		
		return this.mailId + "@" + domain;
	}
	
	
	

	

}
