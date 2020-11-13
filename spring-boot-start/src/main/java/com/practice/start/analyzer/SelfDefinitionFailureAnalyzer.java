package com.practice.start.analyzer;

import com.practice.start.exception.SelfAnalyzerException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * 自定义项目启动失败的异常分析
 */
public class SelfDefinitionFailureAnalyzer extends AbstractFailureAnalyzer<SelfAnalyzerException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, SelfAnalyzerException cause) {
        String action = "***************************\n" +
                "The message about ACTION\n" +
                "***************************";
        String message = "********************************************\n" +
                "Throw SelfAnalyzerException with cause:\n" +
                cause.getMessage() +
                "\n********************************************\n";
        return new FailureAnalysis(message, action, cause);
    }
}
