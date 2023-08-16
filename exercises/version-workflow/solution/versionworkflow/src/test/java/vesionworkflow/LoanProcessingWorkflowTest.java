package versionworkflow;

import versionworkflow.LoanProcessingWorkflowImpl;
import versionworkflow.LoanProcessingWorkflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;
import io.temporal.testing.WorkflowReplayer;

public class LoanProcessingWorkflowTest {

    @RegisterExtension
    public static final TestWorkflowExtension testWorkflowExtension = TestWorkflowExtension.newBuilder()
            .setWorkflowTypes(LoanProcessingWorkflowImpl.class)
            .setDoNotStart(true)
            .build();

    @Test
    public void testSuccessfulReplay(TestWorkflowEnvironment testEnv, Worker worker,
            LoanProcessingWorkflow workflow) throws Exception {

        File eventHistoryFile = new File("history_for_original_execution.json");

        assertDoesNotThrow(
                () -> WorkflowReplayer.replayWorkflowExecution(eventHistoryFile,
                        LoanProcessingWorkflowImpl.class));

    }

}
