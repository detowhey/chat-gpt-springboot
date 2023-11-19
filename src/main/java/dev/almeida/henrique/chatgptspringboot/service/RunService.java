package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.ListSearchParameters;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.runs.CreateThreadAndRunRequest;
import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunCreateRequest;
import com.theokanning.openai.runs.RunStep;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.ThreadRequest;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

@Service
public class RunService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Run postCreateRun(String threadId) {
        return aiService.createRun(threadId, RunCreateRequest.builder().assistantId(Constant.ASSISTANT_ID).build());
    }

    public Run getRunById(String threadId, String runId) {
        return aiService.retrieveRun(threadId, runId);
    }

    public OpenAiResponse<Run> getAllRuns(String threadId) {
        return aiService.listRuns(threadId, ListSearchParameters.builder().build());
    }

    public Run postCancelRun(String threadId, String runId) {
        return aiService.cancelRun(threadId, runId);
    }


    public Run postCreateThreadAndRun() {
        return aiService.createThreadAndRun(
                CreateThreadAndRunRequest.builder().assistantId(Constant.ASSISTANT_ID)
                        .thread(ThreadRequest.builder().build()).build()
        );
    }

    public OpenAiResponse<RunStep> geAllRunSteps(String threadId, String runId) {
        return aiService.listRunSteps(threadId, runId, ListSearchParameters.builder().build());
    }

    public RunStep getRunStepById(String threadId, String runId, String stepId) {
        return aiService.retrieveRunStep(threadId, runId, stepId);
    }
}
