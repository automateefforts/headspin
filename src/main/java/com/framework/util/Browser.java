package com.framework.util;

public enum Browser {
	
		CHROME("Chrome"),
		INTERNET_EXPLORER("IE"), 
		FIREFOX("Firefox");
		
		private String browser;
		
		Browser(String browser){
			this.browser = browser;
		}
		
		public String getBrowser() {
			return this.browser;
		}

}
