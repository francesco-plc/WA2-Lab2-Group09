package it.polito.wa2.group09.lab2.server

import com.codahale.usl4j.Measurement
import com.codahale.usl4j.Model
import java.util.*


class USL {
    fun buildModel() {
        val points =
            arrayOf(doubleArrayOf(1.0, 156.0), doubleArrayOf(2.0, 313.0), doubleArrayOf(4.0, 510.0), doubleArrayOf(8.0, 831.0), doubleArrayOf(16.0, 1115.0), doubleArrayOf(32.0, 1100.0))

        // Map the points to measurements of concurrency and throughput, then build a model from them.
        val model = Arrays.stream(points)
            .map { point: DoubleArray? ->
                Measurement.ofConcurrency().andThroughput(point)
            }
            .collect(Model.toModel())
        var i = 50
        while (i < 200) {
            System.out.printf("At %d workers, expect %f req/sec\n", i, model.throughputAtConcurrency(i.toDouble()))
            i += 10
        }
    }
}
