package example

import Utils.{withSpark, itemsAsDataframe}
import com.amazon.deequ.VerificationSuite
import com.amazon.deequ.checks.{Check, CheckLevel, CheckStatus}
import com.amazon.deequ.constraints.ConstraintStatus

object Hello extends App {
  withSpark { session =>
    val table = "demodata"
    val data = session.read
      .format("jdbc")
      .option("url", s"jdbc:postgresql://${System.getenv("POSTGRES_HOST")}/${System.getenv("POSTGRES_DB")}")
      .option("dbtable", s"public.$table")
      .option("user", System.getenv("POSTGRES_USER"))
      .load()
    
      val verificationResult = VerificationSuite()
      .onData(data)
      .addCheck(
        Check(CheckLevel.Error, "integrity checks")
          .hasSize(_ > 0)
          .isUnique("id") // unique
          .isComplete("id") // not_null
          .isContainedIn("country", Array("Netherlands", "Spain", "UK", "US")))
      .run()

    if (verificationResult.status == CheckStatus.Success) {
      println("The data passed the test, everything is fine!")
    } else {
      println("We found errors in the data, the following constraints were not satisfied:\n")

      val resultsForAllConstraints = verificationResult.checkResults
        .flatMap { case (_, checkResult) => checkResult.constraintResults }

      resultsForAllConstraints
        .filter { _.status != ConstraintStatus.Success }
        .foreach { result =>
          println(s"${result.constraint} failed: ${result.message.get}")
        }
    }
  }
}
