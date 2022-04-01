package it.polito.wa2.group09.lab2.server

import com.codahale.usl4j.Measurement
import com.codahale.usl4j.Model
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.text.DecimalFormat


class USL {
    companion object {
        @JvmStatic fun main(args : Array<String>){
            fun buildModel() {
                val writer = Files.newBufferedWriter(Paths.get("server/src/main/resources/usl.csv"))
                val csvPrinter = CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';')
                    .withHeader("Concurrency Level","Req/Sec"))

//              Lab2/Point 5 --> Req/Sec per Concurrency Level:
//                val points =
//                    arrayOf(doubleArrayOf(1.0, 156.0), doubleArrayOf(2.0, 313.0), doubleArrayOf(4.0, 510.0), doubleArrayOf(8.0, 831.0), doubleArrayOf(16.0, 1115.0), doubleArrayOf(32.0, 1100.0))

//              Lab2/Point 6 --> Req/Sec per Concurrency Level:
//                val points =
//                    arrayOf(doubleArrayOf(1.0, 162.0), doubleArrayOf(2.0, 263.0), doubleArrayOf(4.0, 386.0), doubleArrayOf(8.0, 627.0), doubleArrayOf(16.0, 992.0), doubleArrayOf(32.0, 1014.0))

//              Lab2/Point 7 (Keep Alive) --> Req/Sec per Concurrency Level:
//                val points =
//                    arrayOf(doubleArrayOf(1.0, 254.0), doubleArrayOf(2.0, 486.0), doubleArrayOf(4.0, 1598.0), doubleArrayOf(8.0, 2039.0), doubleArrayOf(16.0, 2207.0), doubleArrayOf(32.0, 2196.0))

//              Lab2/Point 7 (Time Limit)--> Req/Sec per Concurrency Level:
                val points =
                    arrayOf(doubleArrayOf(1.0, 181.0), doubleArrayOf(2.0, 325.0), doubleArrayOf(4.0, 449.0), doubleArrayOf(8.0, 769.0), doubleArrayOf(16.0, 1019.0), doubleArrayOf(32.0, 1110.0))


                // Map the points to measurements of concurrency and throughput, then build a model from them.
                val model = Arrays.stream(points)
                    .map { point: DoubleArray? ->
                        Measurement.ofConcurrency().andThroughput(point)
                    }
                    .collect(Model.toModel())
                var i = 10

                while (i <= 10000) {
                    val res = model.throughputAtConcurrency(i.toDouble())
                    System.out.printf("At %d workers, expect %f req/sec\n", i, res)
                    csvPrinter.printRecord(i, DecimalFormat("#.##").format(res))
                    i += 10
                }
                csvPrinter.flush()
                csvPrinter.close()
            }

            buildModel()

        }
    }
}