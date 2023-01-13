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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.ecl.requests.AuthRequests._
import uk.gov.hmrc.perftests.ecl.requests.RegistrationRequests._

class Simulation extends PerformanceTestRunner {

  setup("ecl-registration-login", "Login via Auth Wizard").withRequests(
    NavigateToRegisterAuthWizard,
    navigateToAuthWizardRedirectionUrl,
    submitAuthWizardForm(),
  )
    setup("ecl-registration-journey", "Register for ECL").withActions(
    navigateToSelectUkRevenue,
    submitSelectUkRevenue("true"),
    navigateToAmlSupervisor,
    submitAmlSupervisor("Hmrc"),
    navigateToEntityType,
    submitEntityType("UkLimitedCompany"),
    navigateToStubGrsJourneyData,
    submitStubGrsJourneyData("0","X00000000000001"),
    navigateToWhetherOrNotAmlActivityStartedInCurrentYear,
    submitWhetherOrNotAmlActivityStartedInCurrentYear("true"),
    navigateToAmlActivityStartDate,
    submitAmlActivityStartDate("10","12","2022"),
    navigateToWhatIsYourBusinessSector,
    submitWhatIsYourBusinessSector("CreditInstitution")
  )

  //  setup(
  //    "ecl-returns-login", "Log in via Auth Wizard for Returns"
  //  ).withRequests(returnsNavigateToAuthWizard)

  runSimulation()
}
