package com.framework.util;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException{
	
	static Logger logger = Logger.getLogger(TestNGBase.class);
	public String errorName = "Error";
	
	public FrameworkException(String errorDescription)  {
		super(errorDescription);
		//throw new RuntimeException();
		logger.error(errorDescription);
	}

	
	public FrameworkException(String errorName, String errorDescription) {
		super(errorDescription);
		this.errorName=errorName;
		//throw new RuntimeException();
		logger.error(errorName+"\t"+errorDescription);
	}
	
	public String getErrorName() {
		return errorName;
	}
}
