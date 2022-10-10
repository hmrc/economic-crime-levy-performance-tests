/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.ecl.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.Predef.{http, status}
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.ecl.Configuration


object AuthRequests extends Configuration{

  val authWizardUrl: String        = s"$authUrl/auth-login-stub//gg-sign-in?continue=http%3A%2F%2Flocalhost%3A14000%2Fregister-for-economic-crime-levy%2F"

  val navigateToAuthWizard: HttpRequestBuilder =
    http("Navigate to Home Page")
      .get(authWizardUrl)
      .check(status.is(200))
}
