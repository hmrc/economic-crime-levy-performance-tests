/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.ecl

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait Configuration extends ServicesConfiguration {

  val registrationUrl: String = baseUrlFor("economic-crime-levy-registration-frontend")
  val returnsUrl: String      = baseUrlFor("economic-crime-levy-returns-frontend")
  val authWizardUrl: String   = baseUrlFor("auth-login-stub") + "/auth-login-stub/gg-sign-in"

  private val csrfTokenPattern: String    = """<input type="hidden" name="csrfToken"\s+value="([^"]+)""""
  private val grsJourneyIdPattern: String = """.*journeyId=(.*)"""

  def saveCsrfToken: HttpCheck =
    regex(_ => csrfTokenPattern).saveAs("csrfToken")

  def saveGrsJourneyId: HttpCheck =
    headerRegex("location", grsJourneyIdPattern).saveAs("grsJourneyId")

}
