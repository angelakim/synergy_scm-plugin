package hudon.plugins.synergy.test.log;

import hudson.plugins.synergy.impl.TaskCompleted;
import hudson.plugins.synergy.impl.TaskInfoCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReadTaskInfoLogTest extends AbstractLogTest {
	/**
	 * Tests the task info log 1.
	 */
	public void testLog1() throws IOException {
		// Test a Synergy 6.5 task info log.
		TaskInfoCommand taskCommand = new TaskInfoCommand(new ArrayList<String>());
		String log = readLog("logs/tasks/task1.log.txt");
		taskCommand.parseResult(log);
		List<TaskCompleted> tasks = taskCommand.getInformations();
		
		// Parsing of the log should not be null.
		assertNotNull(tasks);
		
		// There are 1 task in this log.
		assertEquals(1,tasks.size());
		
		// Check the task elements.
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 2010);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 16);
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.SECOND, 32);
		assertEquals("10827", tasks.get(0).getId());
		assertEquals("dhet", tasks.get(0).getResolver());
		assertEquals("Ajout LNG_CODE vue interface uti", tasks.get(0).getSynopsis());
		assertEquals(cal.getTime(), tasks.get(0).getDateCompleted());
	}
}