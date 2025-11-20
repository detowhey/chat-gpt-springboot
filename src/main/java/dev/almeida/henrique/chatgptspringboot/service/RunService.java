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
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RunService {

    private static final String CREATE_LOG_MESSAGE = "Crate run with Thread id {}";
    private static final String SEARCH_RUNS_LOG_MESSAGE = "Search run(s) by Thread id {}";
    private static final String NOT_FOUND_THREAD_LOG_MESSAGE = "Thread not found by ID {}";

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Run postCreateRun(String threadId) {
        log.info(CREATE_LOG_MESSAGE, threadId);
        return aiService.createRun(threadId, RunCreateRequest.builder().assistantId(Constant.ASSISTANT_ID).build());
    }

    public Run getRunById(String threadId, String runId) {
        return Try.of(() -> {
                    log.info(SEARCH_RUNS_LOG_MESSAGE, threadId);
                    return aiService.retrieveRun(threadId, runId);
                })
                .onFailure(exception -> log.error(NOT_FOUND_THREAD_LOG_MESSAGE, threadId))
                .getOrElseThrow(exception -> new RunNotFoundException(runId));
    }

    public List<Run> getAllRuns(String threadId) {
        return Try.of(() -> {
                    log.info(SEARCH_RUNS_LOG_MESSAGE, threadId);
                    return aiService.listRuns(threadId, ListSearchParameters.builder().build()).data;
                })
                .onFailure(exception -> log.error(NOT_FOUND_THREAD_LOG_MESSAGE, threadId))
                .getOrElseThrow(exception -> new ThreadBotNotFoundException(threadId));
    }

    public Run postCancelRun(String threadId, String runId) {
        return aiService.cancelRun(threadId, runId);
    }


    public Run postCreateThreadAndRun() {
        return aiService.createThreadAndRun(
                CreateThreadAndRunRequest
                        .builder()
                        .assistantId(Constant.ASSISTANT_ID)
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
