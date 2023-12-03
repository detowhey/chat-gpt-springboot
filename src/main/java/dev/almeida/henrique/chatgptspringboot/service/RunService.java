package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.ListSearchParameters;
import com.theokanning.openai.runs.CreateThreadAndRunRequest;
import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunCreateRequest;
import com.theokanning.openai.runs.RunStep;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.ThreadRequest;
import dev.almeida.henrique.chatgptspringboot.exception.RunNotFoundException;
import dev.almeida.henrique.chatgptspringboot.exception.ThreadBotNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RunService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Run postCreateRun(String threadId) {
        log.info(String.format("Crate run with Thread id %s", threadId));
        return aiService.createRun(threadId, RunCreateRequest.builder().assistantId(Constant.ASSISTANT_ID).build());
    }

    public Run getRunById(String threadId, String runId) {
        try {
            log.info(String.format("Search run by Thread id %s", threadId));
            return aiService.retrieveRun(threadId, runId);
        } catch (RuntimeException exception) {
            throw new RunNotFoundException(runId);
        } catch (Exception exception) {
            throw new ThreadBotNotFoundException(threadId);
        }
    }

    public List<Run> getAllRuns(String threadId) {
        try {
            log.info(String.format("Search runs by Thread ID %s", threadId));
            return aiService.listRuns(threadId, ListSearchParameters.builder().build()).data;
        } catch (RuntimeException exception) {
            log.error(String.format("Thread not found by ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        }
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

    public List<RunStep> geAllRunSteps(String threadId, String runId) {
        return aiService.listRunSteps(threadId, runId, ListSearchParameters.builder().build()).data;
    }

    public RunStep getRunStepById(String threadId, String runId, String stepId) {
        return aiService.retrieveRunStep(threadId, runId, stepId);
    }
}
