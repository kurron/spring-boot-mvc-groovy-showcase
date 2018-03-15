package com.example.nonreactive.service.one

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.FunctionCounter
import io.micrometer.core.instrument.FunctionTimer
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.LongTaskTimer
import io.micrometer.core.instrument.Measurement
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.MockClock
import io.micrometer.core.instrument.Timer
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig
import io.micrometer.core.instrument.distribution.pause.PauseDetector
import io.micrometer.core.instrument.noop.NoopCounter
import io.micrometer.core.lang.Nullable
import java.util.concurrent.TimeUnit
import java.util.function.ToDoubleFunction
import java.util.function.ToLongFunction

/**
 * A fake version of the registry for use in auto-configured tests, such as WebMvcTest, where not every configuration
 * is enabled.
 */
class MockMeterRegistry extends MeterRegistry {

    MockMeterRegistry() {
        super( new MockClock() )
    }

    @Override
    protected <T> Gauge newGauge( Meter.Id id, @Nullable T obj, ToDoubleFunction<T> valueFunction ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected Counter newCounter( Meter.Id id ) {
        new NoopCounter()
    }

    @Override
    protected LongTaskTimer newLongTaskTimer( Meter.Id id ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected Timer newTimer( Meter.Id id, DistributionStatisticConfig distributionStatisticConfig, PauseDetector pauseDetector ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected DistributionSummary newDistributionSummary( Meter.Id id, DistributionStatisticConfig distributionStatisticConfig, double scale ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected Meter newMeter( Meter.Id id, Meter.Type type, Iterable<Measurement> measurements ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected <T> FunctionTimer newFunctionTimer( Meter.Id id, T obj, ToLongFunction<T> countFunction, ToDoubleFunction<T> totalTimeFunction, TimeUnit totalTimeFunctionUnits ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected <T> FunctionCounter newFunctionCounter( Meter.Id id, T obj, ToDoubleFunction<T> countFunction ) {
        throw new UnsupportedOperationException()
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        throw new UnsupportedOperationException()
    }

    @Override
    protected DistributionStatisticConfig defaultHistogramConfig() {
        throw new UnsupportedOperationException()
    }

}
