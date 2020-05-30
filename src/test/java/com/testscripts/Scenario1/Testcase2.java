package com.testscripts.Scenario1;

import org.testng.annotations.Test;

import com.framework.util.TestNGBase;

public class Testcase2 extends TestNGBase {
	@Test(dataProvider="dataProvider")
	public void testscript(int runCount){
		String scenario = testUtil.getScenario();
		String testcase = testUtil.getTestcaseID();
		int iteration = testUtil.getCurrentIteration();
		executeKeywords(scenario, testcase, iteration);
	}
}
