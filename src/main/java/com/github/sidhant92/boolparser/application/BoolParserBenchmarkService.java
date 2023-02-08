package com.github.sidhant92.boolparser.application;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.parser.antlr.BoolParser;
import lombok.SneakyThrows;

/**
 * @author Het Shah
 * @since 08/02/2023
 */
@State (Scope.Benchmark)
@OutputTimeUnit (TimeUnit.MICROSECONDS)
public class BoolParserBenchmarkService {
    private static Map<String, Object> data;

    private static String dataString;

    private static BoolParser parser;

    private static BooleanExpressionEvaluator evaluator;

    public static void main(String[] args) throws RunnerException, IOException {

        Options opt = new OptionsBuilder()
                .include(BoolParserBenchmarkService.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @SneakyThrows
    @Setup
    public void setup() {
        final ObjectMapper objectMapper = new ObjectMapper();
        dataString = "{\"b\":1,\"c\":{\"d\":[\"x\",\"y\"],\"e\":\"xyz\"},\"f\":{\"g\":[\"arr1\",\"arr2\"],\"h\":{\"a\":\"b\",\"c\":\"d\",\"e\":true},\"j\":24},\"x\":[\"a1\",\"a2\",\"a3\",\"a4\"],\"y\":24, \"z\": \"c1\"}";
        data = objectMapper.readValue(dataString, new TypeReference<Map<String, Object>>() {});
        parser = new BoolParser();
        evaluator = new BooleanExpressionEvaluator(parser);
    }

    @Benchmark
    @Warmup(iterations = 2)
    @BenchmarkMode (Mode.SampleTime) //change here to check for specific mode
    public void benchmarkEvaluation() {
        final String rule = "b>0 AND z IN ('c1', 'c2')";
        evaluator.evaluate(rule, data);
    }

    @Benchmark
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.SampleTime) // change here to check for specific mode
    public void benchmarkParsing() {
        final String rule = "b>0 AND z IN ('c1', 'c2')";
        parser.parseExpression(rule);
    }

}
