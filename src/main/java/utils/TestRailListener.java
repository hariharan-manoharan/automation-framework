package main.java.utils;

import java.util.List;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;

public class TestRailListener extends Utility {

	public TestRail testRail;
	public Run run;
	private int projectId;
	
	public TestRailListener(int projectId) {		
		this.projectId = projectId;
	}

	public void initialize() {

		
			testRail = TestRail.builder(properties.getProperty("testRail.url"),
										properties.getProperty("testRail.username"),
										properties.getProperty("testRail.password")).applicationName("Automation").build();
		
		
	}

	public void addTestRun() {
		run = testRail.runs().add(projectId, new Run().setSuiteId(Integer.parseInt(properties.getProperty("testRail.suiteId"))).setName(properties.getProperty("testRail.testRunName"))).execute();
	}

	public void addTestResult(int testCaseId, int statusId) {
		List<ResultField> customResultFields = testRail.resultFields().list().execute();
		testRail.results().addForCase(Integer.parseInt(properties.getProperty("testRail.runId")), testCaseId, new Result().setStatusId(statusId), customResultFields).execute();
	}

	public void closeTestRun() {
		testRail.runs().close(run.getId()).execute();
	}

}
