package com.testscripts.Scenario1;

import org.testng.annotations.Test;

import com.framework.util.TestNGBase;

public class Testcase1 extends TestNGBase {
	@Test(dataProvider="dataProvider")
	public void testscript(int runCount) throws InterruptedException{
		String scenario = testUtil.getScenario();
		String testcase = testUtil.getTestcaseID();
		int iteration = testUtil.getCurrentIteration();
		executeKeywords(scenario, testcase, iteration);
	}
}
