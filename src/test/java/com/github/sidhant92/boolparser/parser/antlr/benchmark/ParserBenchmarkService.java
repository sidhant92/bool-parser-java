package com.github.sidhant92.boolparser.parser.antlr.benchmark;

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
import com.github.sidhant92.boolparser.parser.antlr.BoolParser;
import com.github.sidhant92.boolparser.provider.BoolParserDataProvider;
import lombok.SneakyThrows;

/**
 * @author Het Shah
 * @since 09/02/2023
 */
@State (Scope.Benchmark)
@OutputTimeUnit (TimeUnit.MICROSECONDS)
public class ParserBenchmarkService {
    private static Map<String, Object> data;

    private static BoolParser parser;


    public static void main(String[] args) throws RunnerException, IOException {
        Options opt = new OptionsBuilder().include(ParserBenchmarkService.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

    @SneakyThrows
    @Setup
    public void setup() {
        data = BoolParserDataProvider.getData();
        parser = new BoolParser();
    }

    @Benchmark
    @Warmup (iterations = 2)
    @BenchmarkMode (Mode.SampleTime) //change here to check for specific mode
    public void benchmarkEvaluation() {
        final String rule = "b>0 AND z IN ('c1', 'c2')";
        parser.parseExpression(rule);
    }
}
